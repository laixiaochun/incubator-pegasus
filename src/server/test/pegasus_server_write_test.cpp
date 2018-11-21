// Copyright (c) 2017, Xiaomi, Inc.  All rights reserved.
// This source code is licensed under the Apache License Version 2.0, which
// can be found in the LICENSE file in the root directory of this source tree.

#include "pegasus_server_test_base.h"
#include "message_utils.h"
#include "server/pegasus_server_write.h"
#include "server/pegasus_write_service_impl.h"
#include "base/pegasus_key_schema.h"

#include <dsn/utility/fail_point.h>
#include <dsn/utility/defer.h>

namespace pegasus {
namespace server {

class pegasus_server_write_test : public pegasus_server_test_base
{
    std::unique_ptr<pegasus_server_write> _server_write;

public:
    pegasus_server_write_test() : pegasus_server_test_base()
    {
        _server_write = dsn::make_unique<pegasus_server_write>(_server.get(), true);
    }

    void test_batch_writes()
    {
        dsn::fail::setup();

        dsn::fail::cfg("db_write_batch_put", "10%return()");
        dsn::fail::cfg("db_write_batch_remove", "10%return()");
        dsn::fail::cfg("db_write", "10%return()");

        for (int decree = 1; decree <= 1000; decree++) {
            RPC_MOCKING(put_rpc) RPC_MOCKING(remove_rpc)
            {
                dsn::blob key;
                pegasus_generate_key(key, std::string("hash"), std::string("sort"));
                dsn::apps::update_request req;
                req.key = key;
                req.value.assign("value", 0, 5);

                int put_rpc_cnt = dsn::rand::next_u32(1, 10);
                int remove_rpc_cnt = dsn::rand::next_u32(1, 10);
                int total_rpc_cnt = put_rpc_cnt + remove_rpc_cnt;
                auto writes = new dsn::message_ex *[total_rpc_cnt];
                for (int i = 0; i < put_rpc_cnt; i++) {
                    writes[i] = pegasus::create_put_request(req);
                }
                for (int i = put_rpc_cnt; i < total_rpc_cnt; i++) {
                    writes[i] = pegasus::create_remove_request(key);
                }
                auto cleanup = dsn::defer([=]() { delete[] writes; });

                int err =
                    _server_write->on_batched_write_requests(writes, total_rpc_cnt, decree, 0);
                switch (err) {
                case FAIL_DB_WRITE_BATCH_PUT:
                case FAIL_DB_WRITE_BATCH_DELETE:
                case FAIL_DB_WRITE:
                case 0:
                    break;
                default:
                    ASSERT_TRUE(false) << "unacceptable error: " << err;
                }

                // make sure everything is cleanup after batch write.
                ASSERT_TRUE(_server_write->_put_rpc_batch.empty());
                ASSERT_TRUE(_server_write->_remove_rpc_batch.empty());
                ASSERT_TRUE(_server_write->_write_svc->_batch_qps_perfcounters.empty());
                ASSERT_TRUE(_server_write->_write_svc->_batch_latency_perfcounters.empty());
                ASSERT_EQ(_server_write->_write_svc->_batch_start_time, 0);
                ASSERT_EQ(_server_write->_write_svc->_impl->_batch.Count(), 0);
                ASSERT_EQ(_server_write->_write_svc->_impl->_update_responses.size(), 0);

                ASSERT_EQ(put_rpc::mail_box().size(), put_rpc_cnt);
                ASSERT_EQ(remove_rpc::mail_box().size(), remove_rpc_cnt);
                for (auto &rpc : put_rpc::mail_box()) {
                    verify_response(rpc.response(), err, decree);
                }
                for (auto &rpc : remove_rpc::mail_box()) {
                    verify_response(rpc.response(), err, decree);
                }
            }
        }

        dsn::fail::teardown();
    }

    void test_duplicate_not_batched()
    {
        std::string hash_key = "hash_key";
        constexpr int kv_num = 100;
        std::string sort_key[kv_num];
        std::string value[kv_num];

        for (int i = 0; i < 100; i++) {
            sort_key[i] = "sort_key_" + std::to_string(i);
            value[i] = "value_" + std::to_string(i);
        }

        dsn::apps::duplicate_request duplicate;
        duplicate.timetag = 1000;
        dsn::apps::duplicate_response resp;

        {
            dsn::apps::multi_put_request mput;
            for (int i = 0; i < 100; i++) {
                mput.kvs.emplace_back();
                mput.kvs.back().key.assign(sort_key[i].data(), 0, sort_key[i].size());
                mput.kvs.back().value.assign(value[i].data(), 0, value[i].size());
            }
            dsn::message_ex* mput_msg = pegasus::create_multi_put_request(mput);

            duplicate.task_code = dsn::apps::RPC_RRDB_RRDB_MULTI_PUT;
            duplicate.raw_message = dsn::move_message_to_blob(mput_msg);

            _server_write->on_duplicate(duplicate, resp);
            ASSERT_EQ(resp.error, 0);
        }

        {
            dsn::apps::multi_remove_request mremove;
            for (int i = 0; i < 100; i++) {
                mremove.sort_keys.emplace_back();
                mremove.sort_keys.back().assign(sort_key[i].data(), 0, sort_key[i].size());
            }
            dsn::message_ex* mremove_msg = pegasus::create_multi_remove_request(mremove);

            duplicate.task_code = dsn::apps::RPC_RRDB_RRDB_MULTI_REMOVE;
            duplicate.raw_message = dsn::move_message_to_blob(mremove_msg);

            _server_write->on_duplicate(duplicate, resp);
            ASSERT_EQ(resp.error, 0);
        }
    }

    void test_duplicate_batched()
    {
        std::string hash_key = "hash_key";
        constexpr int kv_num = 100;
        std::string sort_key[kv_num];
        std::string value[kv_num];

        for (int i = 0; i < 100; i++) {
            sort_key[i] = "sort_key_" + std::to_string(i);
            value[i] = "value_" + std::to_string(i);
        }

        {
            dsn::apps::duplicate_request duplicate;
            duplicate.timetag = 1000;
            dsn::apps::duplicate_response resp;

            for (int i = 0; i < kv_num; i++) {
                dsn::apps::update_request request;
                pegasus::pegasus_generate_key(request.key, hash_key, sort_key[i]);
                request.value.assign(value[i].data(), 0, value[i].size());

                duplicate.raw_message =
                    dsn::move_message_to_blob(pegasus::create_put_request(request));
                duplicate.task_code = dsn::apps::RPC_RRDB_RRDB_PUT;
                _server_write->on_duplicate(duplicate, resp);
                ASSERT_EQ(resp.error, 0);
            }
        }
    }

    void verify_response(const dsn::apps::update_response &response, int err, int64_t decree)
    {
        ASSERT_EQ(response.error, err);
        ASSERT_EQ(response.app_id, _gpid.get_app_id());
        ASSERT_EQ(response.partition_index, _gpid.get_partition_index());
        ASSERT_EQ(response.decree, decree);
        ASSERT_EQ(response.server, _server_write->_write_svc->_impl->_primary_address);
    }
};

TEST_F(pegasus_server_write_test, batch_writes) { test_batch_writes(); }

TEST_F(pegasus_server_write_test, duplicate_not_batched) { test_duplicate_not_batched(); }

TEST_F(pegasus_server_write_test, duplicate_batched) { test_duplicate_batched(); }

} // namespace server
} // namespace pegasus
