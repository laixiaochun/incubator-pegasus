/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * <p>DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *
 * @generated
 */
package org.apache.pegasus.apps;

import org.apache.pegasus.base.blob;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(
    value = "Autogenerated by Thrift Compiler (0.11.0)",
    date = "2020-04-13")
public class incr_request
    implements org.apache.thrift.TBase<incr_request, incr_request._Fields>,
        java.io.Serializable,
        Cloneable,
        Comparable<incr_request> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC =
      new org.apache.thrift.protocol.TStruct("incr_request");

  private static final org.apache.thrift.protocol.TField KEY_FIELD_DESC =
      new org.apache.thrift.protocol.TField(
          "key", org.apache.thrift.protocol.TType.STRUCT, (short) 1);
  private static final org.apache.thrift.protocol.TField INCREMENT_FIELD_DESC =
      new org.apache.thrift.protocol.TField(
          "increment", org.apache.thrift.protocol.TType.I64, (short) 2);
  private static final org.apache.thrift.protocol.TField EXPIRE_TS_SECONDS_FIELD_DESC =
      new org.apache.thrift.protocol.TField(
          "expire_ts_seconds", org.apache.thrift.protocol.TType.I32, (short) 3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY =
      new incr_requestStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY =
      new incr_requestTupleSchemeFactory();

  public blob key; // required
  public long increment; // required
  public int expire_ts_seconds; // required

  /**
   * The set of fields this struct contains, along with convenience methods for finding and
   * manipulating them.
   */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    KEY((short) 1, "key"),
    INCREMENT((short) 2, "increment"),
    EXPIRE_TS_SECONDS((short) 3, "expire_ts_seconds");

    private static final java.util.Map<java.lang.String, _Fields> byName =
        new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /** Find the _Fields constant that matches fieldId, or null if its not found. */
    public static _Fields findByThriftId(int fieldId) {
      switch (fieldId) {
        case 1: // KEY
          return KEY;
        case 2: // INCREMENT
          return INCREMENT;
        case 3: // EXPIRE_TS_SECONDS
          return EXPIRE_TS_SECONDS;
        default:
          return null;
      }
    }

    /** Find the _Fields constant that matches fieldId, throwing an exception if it is not found. */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null)
        throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /** Find the _Fields constant that matches name, or null if its not found. */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __INCREMENT_ISSET_ID = 0;
  private static final int __EXPIRE_TS_SECONDS_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;

  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap =
        new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(
        _Fields.KEY,
        new org.apache.thrift.meta_data.FieldMetaData(
            "key",
            org.apache.thrift.TFieldRequirementType.DEFAULT,
            new org.apache.thrift.meta_data.StructMetaData(
                org.apache.thrift.protocol.TType.STRUCT, blob.class)));
    tmpMap.put(
        _Fields.INCREMENT,
        new org.apache.thrift.meta_data.FieldMetaData(
            "increment",
            org.apache.thrift.TFieldRequirementType.DEFAULT,
            new org.apache.thrift.meta_data.FieldValueMetaData(
                org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(
        _Fields.EXPIRE_TS_SECONDS,
        new org.apache.thrift.meta_data.FieldMetaData(
            "expire_ts_seconds",
            org.apache.thrift.TFieldRequirementType.DEFAULT,
            new org.apache.thrift.meta_data.FieldValueMetaData(
                org.apache.thrift.protocol.TType.I32)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(incr_request.class, metaDataMap);
  }

  public incr_request() {}

  public incr_request(blob key, long increment, int expire_ts_seconds) {
    this();
    this.key = key;
    this.increment = increment;
    setIncrementIsSet(true);
    this.expire_ts_seconds = expire_ts_seconds;
    setExpire_ts_secondsIsSet(true);
  }

  /** Performs a deep copy on <i>other</i>. */
  public incr_request(incr_request other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetKey()) {
      this.key = new blob(other.key);
    }
    this.increment = other.increment;
    this.expire_ts_seconds = other.expire_ts_seconds;
  }

  public incr_request deepCopy() {
    return new incr_request(this);
  }

  @Override
  public void clear() {
    this.key = null;
    setIncrementIsSet(false);
    this.increment = 0;
    setExpire_ts_secondsIsSet(false);
    this.expire_ts_seconds = 0;
  }

  public blob getKey() {
    return this.key;
  }

  public incr_request setKey(blob key) {
    this.key = key;
    return this;
  }

  public void unsetKey() {
    this.key = null;
  }

  /** Returns true if field key is set (has been assigned a value) and false otherwise */
  public boolean isSetKey() {
    return this.key != null;
  }

  public void setKeyIsSet(boolean value) {
    if (!value) {
      this.key = null;
    }
  }

  public long getIncrement() {
    return this.increment;
  }

  public incr_request setIncrement(long increment) {
    this.increment = increment;
    setIncrementIsSet(true);
    return this;
  }

  public void unsetIncrement() {
    __isset_bitfield =
        org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __INCREMENT_ISSET_ID);
  }

  /** Returns true if field increment is set (has been assigned a value) and false otherwise */
  public boolean isSetIncrement() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __INCREMENT_ISSET_ID);
  }

  public void setIncrementIsSet(boolean value) {
    __isset_bitfield =
        org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __INCREMENT_ISSET_ID, value);
  }

  public int getExpire_ts_seconds() {
    return this.expire_ts_seconds;
  }

  public incr_request setExpire_ts_seconds(int expire_ts_seconds) {
    this.expire_ts_seconds = expire_ts_seconds;
    setExpire_ts_secondsIsSet(true);
    return this;
  }

  public void unsetExpire_ts_seconds() {
    __isset_bitfield =
        org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __EXPIRE_TS_SECONDS_ISSET_ID);
  }

  /**
   * Returns true if field expire_ts_seconds is set (has been assigned a value) and false otherwise
   */
  public boolean isSetExpire_ts_seconds() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __EXPIRE_TS_SECONDS_ISSET_ID);
  }

  public void setExpire_ts_secondsIsSet(boolean value) {
    __isset_bitfield =
        org.apache.thrift.EncodingUtils.setBit(
            __isset_bitfield, __EXPIRE_TS_SECONDS_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
      case KEY:
        if (value == null) {
          unsetKey();
        } else {
          setKey((blob) value);
        }
        break;

      case INCREMENT:
        if (value == null) {
          unsetIncrement();
        } else {
          setIncrement((java.lang.Long) value);
        }
        break;

      case EXPIRE_TS_SECONDS:
        if (value == null) {
          unsetExpire_ts_seconds();
        } else {
          setExpire_ts_seconds((java.lang.Integer) value);
        }
        break;
    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
      case KEY:
        return getKey();

      case INCREMENT:
        return getIncrement();

      case EXPIRE_TS_SECONDS:
        return getExpire_ts_seconds();
    }
    throw new java.lang.IllegalStateException();
  }

  /**
   * Returns true if field corresponding to fieldID is set (has been assigned a value) and false
   * otherwise
   */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
      case KEY:
        return isSetKey();
      case INCREMENT:
        return isSetIncrement();
      case EXPIRE_TS_SECONDS:
        return isSetExpire_ts_seconds();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null) return false;
    if (that instanceof incr_request) return this.equals((incr_request) that);
    return false;
  }

  public boolean equals(incr_request that) {
    if (that == null) return false;
    if (this == that) return true;

    boolean this_present_key = true && this.isSetKey();
    boolean that_present_key = true && that.isSetKey();
    if (this_present_key || that_present_key) {
      if (!(this_present_key && that_present_key)) return false;
      if (!this.key.equals(that.key)) return false;
    }

    boolean this_present_increment = true;
    boolean that_present_increment = true;
    if (this_present_increment || that_present_increment) {
      if (!(this_present_increment && that_present_increment)) return false;
      if (this.increment != that.increment) return false;
    }

    boolean this_present_expire_ts_seconds = true;
    boolean that_present_expire_ts_seconds = true;
    if (this_present_expire_ts_seconds || that_present_expire_ts_seconds) {
      if (!(this_present_expire_ts_seconds && that_present_expire_ts_seconds)) return false;
      if (this.expire_ts_seconds != that.expire_ts_seconds) return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetKey()) ? 131071 : 524287);
    if (isSetKey()) hashCode = hashCode * 8191 + key.hashCode();

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(increment);

    hashCode = hashCode * 8191 + expire_ts_seconds;

    return hashCode;
  }

  @Override
  public int compareTo(incr_request other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetKey()).compareTo(other.isSetKey());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetKey()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.key, other.key);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetIncrement()).compareTo(other.isSetIncrement());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIncrement()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.increment, other.increment);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison =
        java.lang.Boolean.valueOf(isSetExpire_ts_seconds())
            .compareTo(other.isSetExpire_ts_seconds());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetExpire_ts_seconds()) {
      lastComparison =
          org.apache.thrift.TBaseHelper.compareTo(this.expire_ts_seconds, other.expire_ts_seconds);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot)
      throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("incr_request(");
    boolean first = true;

    sb.append("key:");
    if (this.key == null) {
      sb.append("null");
    } else {
      sb.append(this.key);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("increment:");
    sb.append(this.increment);
    first = false;
    if (!first) sb.append(", ");
    sb.append("expire_ts_seconds:");
    sb.append(this.expire_ts_seconds);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (key != null) {
      key.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(
          new org.apache.thrift.protocol.TCompactProtocol(
              new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in)
      throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and
      // doesn't call the default constructor.
      __isset_bitfield = 0;
      read(
          new org.apache.thrift.protocol.TCompactProtocol(
              new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class incr_requestStandardSchemeFactory
      implements org.apache.thrift.scheme.SchemeFactory {
    public incr_requestStandardScheme getScheme() {
      return new incr_requestStandardScheme();
    }
  }

  private static class incr_requestStandardScheme
      extends org.apache.thrift.scheme.StandardScheme<incr_request> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, incr_request struct)
        throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true) {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) {
          break;
        }
        switch (schemeField.id) {
          case 1: // KEY
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.key = new blob();
              struct.key.read(iprot);
              struct.setKeyIsSet(true);
            } else {
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // INCREMENT
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.increment = iprot.readI64();
              struct.setIncrementIsSet(true);
            } else {
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // EXPIRE_TS_SECONDS
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.expire_ts_seconds = iprot.readI32();
              struct.setExpire_ts_secondsIsSet(true);
            } else {
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, incr_request struct)
        throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.key != null) {
        oprot.writeFieldBegin(KEY_FIELD_DESC);
        struct.key.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(INCREMENT_FIELD_DESC);
      oprot.writeI64(struct.increment);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(EXPIRE_TS_SECONDS_FIELD_DESC);
      oprot.writeI32(struct.expire_ts_seconds);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }
  }

  private static class incr_requestTupleSchemeFactory
      implements org.apache.thrift.scheme.SchemeFactory {
    public incr_requestTupleScheme getScheme() {
      return new incr_requestTupleScheme();
    }
  }

  private static class incr_requestTupleScheme
      extends org.apache.thrift.scheme.TupleScheme<incr_request> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, incr_request struct)
        throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot =
          (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetKey()) {
        optionals.set(0);
      }
      if (struct.isSetIncrement()) {
        optionals.set(1);
      }
      if (struct.isSetExpire_ts_seconds()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetKey()) {
        struct.key.write(oprot);
      }
      if (struct.isSetIncrement()) {
        oprot.writeI64(struct.increment);
      }
      if (struct.isSetExpire_ts_seconds()) {
        oprot.writeI32(struct.expire_ts_seconds);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, incr_request struct)
        throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot =
          (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.key = new blob();
        struct.key.read(iprot);
        struct.setKeyIsSet(true);
      }
      if (incoming.get(1)) {
        struct.increment = iprot.readI64();
        struct.setIncrementIsSet(true);
      }
      if (incoming.get(2)) {
        struct.expire_ts_seconds = iprot.readI32();
        struct.setExpire_ts_secondsIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(
      org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme())
            ? STANDARD_SCHEME_FACTORY
            : TUPLE_SCHEME_FACTORY)
        .getScheme();
  }
}
