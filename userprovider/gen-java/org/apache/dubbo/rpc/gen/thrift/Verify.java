/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.apache.dubbo.rpc.gen.thrift;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2019-01-04")
public class Verify implements org.apache.thrift.TBase<Verify, Verify._Fields>, java.io.Serializable, Cloneable, Comparable<Verify> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Verify");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("code", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField MOBILE_FIELD_DESC = new org.apache.thrift.protocol.TField("mobile", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField EMAIL_FIELD_DESC = new org.apache.thrift.protocol.TField("email", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField VALID_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("validTime", org.apache.thrift.protocol.TType.I64, (short)5);
  private static final org.apache.thrift.protocol.TField EMAIL_SIGN_FIELD_DESC = new org.apache.thrift.protocol.TField("emailSign", org.apache.thrift.protocol.TType.STRING, (short)6);
  private static final org.apache.thrift.protocol.TField UID_FIELD_DESC = new org.apache.thrift.protocol.TField("uid", org.apache.thrift.protocol.TType.STRING, (short)7);
  private static final org.apache.thrift.protocol.TField ITU_FIELD_DESC = new org.apache.thrift.protocol.TField("itu", org.apache.thrift.protocol.TType.STRING, (short)8);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new VerifyStandardSchemeFactory());
    schemes.put(TupleScheme.class, new VerifyTupleSchemeFactory());
  }

  public String id; // optional
  public String code; // required
  public String mobile; // optional
  public String email; // optional
  public long validTime; // required
  public String emailSign; // optional
  public String uid; // optional
  public String itu; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    CODE((short)2, "code"),
    MOBILE((short)3, "mobile"),
    EMAIL((short)4, "email"),
    VALID_TIME((short)5, "validTime"),
    EMAIL_SIGN((short)6, "emailSign"),
    UID((short)7, "uid"),
    ITU((short)8, "itu");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // ID
          return ID;
        case 2: // CODE
          return CODE;
        case 3: // MOBILE
          return MOBILE;
        case 4: // EMAIL
          return EMAIL;
        case 5: // VALID_TIME
          return VALID_TIME;
        case 6: // EMAIL_SIGN
          return EMAIL_SIGN;
        case 7: // UID
          return UID;
        case 8: // ITU
          return ITU;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __VALIDTIME_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.ID,_Fields.MOBILE,_Fields.EMAIL,_Fields.EMAIL_SIGN,_Fields.UID,_Fields.ITU};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CODE, new org.apache.thrift.meta_data.FieldMetaData("code", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.MOBILE, new org.apache.thrift.meta_data.FieldMetaData("mobile", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.EMAIL, new org.apache.thrift.meta_data.FieldMetaData("email", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.VALID_TIME, new org.apache.thrift.meta_data.FieldMetaData("validTime", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.EMAIL_SIGN, new org.apache.thrift.meta_data.FieldMetaData("emailSign", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.UID, new org.apache.thrift.meta_data.FieldMetaData("uid", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ITU, new org.apache.thrift.meta_data.FieldMetaData("itu", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Verify.class, metaDataMap);
  }

  public Verify() {
  }

  public Verify(
    String code,
    long validTime)
  {
    this();
    this.code = code;
    this.validTime = validTime;
    setValidTimeIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Verify(Verify other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetId()) {
      this.id = other.id;
    }
    if (other.isSetCode()) {
      this.code = other.code;
    }
    if (other.isSetMobile()) {
      this.mobile = other.mobile;
    }
    if (other.isSetEmail()) {
      this.email = other.email;
    }
    this.validTime = other.validTime;
    if (other.isSetEmailSign()) {
      this.emailSign = other.emailSign;
    }
    if (other.isSetUid()) {
      this.uid = other.uid;
    }
    if (other.isSetItu()) {
      this.itu = other.itu;
    }
  }

  public Verify deepCopy() {
    return new Verify(this);
  }

  @Override
  public void clear() {
    this.id = null;
    this.code = null;
    this.mobile = null;
    this.email = null;
    setValidTimeIsSet(false);
    this.validTime = 0;
    this.emailSign = null;
    this.uid = null;
    this.itu = null;
  }

  public String getId() {
    return this.id;
  }

  public Verify setId(String id) {
    this.id = id;
    return this;
  }

  public void unsetId() {
    this.id = null;
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return this.id != null;
  }

  public void setIdIsSet(boolean value) {
    if (!value) {
      this.id = null;
    }
  }

  public String getCode() {
    return this.code;
  }

  public Verify setCode(String code) {
    this.code = code;
    return this;
  }

  public void unsetCode() {
    this.code = null;
  }

  /** Returns true if field code is set (has been assigned a value) and false otherwise */
  public boolean isSetCode() {
    return this.code != null;
  }

  public void setCodeIsSet(boolean value) {
    if (!value) {
      this.code = null;
    }
  }

  public String getMobile() {
    return this.mobile;
  }

  public Verify setMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public void unsetMobile() {
    this.mobile = null;
  }

  /** Returns true if field mobile is set (has been assigned a value) and false otherwise */
  public boolean isSetMobile() {
    return this.mobile != null;
  }

  public void setMobileIsSet(boolean value) {
    if (!value) {
      this.mobile = null;
    }
  }

  public String getEmail() {
    return this.email;
  }

  public Verify setEmail(String email) {
    this.email = email;
    return this;
  }

  public void unsetEmail() {
    this.email = null;
  }

  /** Returns true if field email is set (has been assigned a value) and false otherwise */
  public boolean isSetEmail() {
    return this.email != null;
  }

  public void setEmailIsSet(boolean value) {
    if (!value) {
      this.email = null;
    }
  }

  public long getValidTime() {
    return this.validTime;
  }

  public Verify setValidTime(long validTime) {
    this.validTime = validTime;
    setValidTimeIsSet(true);
    return this;
  }

  public void unsetValidTime() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __VALIDTIME_ISSET_ID);
  }

  /** Returns true if field validTime is set (has been assigned a value) and false otherwise */
  public boolean isSetValidTime() {
    return EncodingUtils.testBit(__isset_bitfield, __VALIDTIME_ISSET_ID);
  }

  public void setValidTimeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __VALIDTIME_ISSET_ID, value);
  }

  public String getEmailSign() {
    return this.emailSign;
  }

  public Verify setEmailSign(String emailSign) {
    this.emailSign = emailSign;
    return this;
  }

  public void unsetEmailSign() {
    this.emailSign = null;
  }

  /** Returns true if field emailSign is set (has been assigned a value) and false otherwise */
  public boolean isSetEmailSign() {
    return this.emailSign != null;
  }

  public void setEmailSignIsSet(boolean value) {
    if (!value) {
      this.emailSign = null;
    }
  }

  public String getUid() {
    return this.uid;
  }

  public Verify setUid(String uid) {
    this.uid = uid;
    return this;
  }

  public void unsetUid() {
    this.uid = null;
  }

  /** Returns true if field uid is set (has been assigned a value) and false otherwise */
  public boolean isSetUid() {
    return this.uid != null;
  }

  public void setUidIsSet(boolean value) {
    if (!value) {
      this.uid = null;
    }
  }

  public String getItu() {
    return this.itu;
  }

  public Verify setItu(String itu) {
    this.itu = itu;
    return this;
  }

  public void unsetItu() {
    this.itu = null;
  }

  /** Returns true if field itu is set (has been assigned a value) and false otherwise */
  public boolean isSetItu() {
    return this.itu != null;
  }

  public void setItuIsSet(boolean value) {
    if (!value) {
      this.itu = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((String)value);
      }
      break;

    case CODE:
      if (value == null) {
        unsetCode();
      } else {
        setCode((String)value);
      }
      break;

    case MOBILE:
      if (value == null) {
        unsetMobile();
      } else {
        setMobile((String)value);
      }
      break;

    case EMAIL:
      if (value == null) {
        unsetEmail();
      } else {
        setEmail((String)value);
      }
      break;

    case VALID_TIME:
      if (value == null) {
        unsetValidTime();
      } else {
        setValidTime((Long)value);
      }
      break;

    case EMAIL_SIGN:
      if (value == null) {
        unsetEmailSign();
      } else {
        setEmailSign((String)value);
      }
      break;

    case UID:
      if (value == null) {
        unsetUid();
      } else {
        setUid((String)value);
      }
      break;

    case ITU:
      if (value == null) {
        unsetItu();
      } else {
        setItu((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return getId();

    case CODE:
      return getCode();

    case MOBILE:
      return getMobile();

    case EMAIL:
      return getEmail();

    case VALID_TIME:
      return getValidTime();

    case EMAIL_SIGN:
      return getEmailSign();

    case UID:
      return getUid();

    case ITU:
      return getItu();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return isSetId();
    case CODE:
      return isSetCode();
    case MOBILE:
      return isSetMobile();
    case EMAIL:
      return isSetEmail();
    case VALID_TIME:
      return isSetValidTime();
    case EMAIL_SIGN:
      return isSetEmailSign();
    case UID:
      return isSetUid();
    case ITU:
      return isSetItu();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof Verify)
      return this.equals((Verify)that);
    return false;
  }

  public boolean equals(Verify that) {
    if (that == null)
      return false;

    boolean this_present_id = true && this.isSetId();
    boolean that_present_id = true && that.isSetId();
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (!this.id.equals(that.id))
        return false;
    }

    boolean this_present_code = true && this.isSetCode();
    boolean that_present_code = true && that.isSetCode();
    if (this_present_code || that_present_code) {
      if (!(this_present_code && that_present_code))
        return false;
      if (!this.code.equals(that.code))
        return false;
    }

    boolean this_present_mobile = true && this.isSetMobile();
    boolean that_present_mobile = true && that.isSetMobile();
    if (this_present_mobile || that_present_mobile) {
      if (!(this_present_mobile && that_present_mobile))
        return false;
      if (!this.mobile.equals(that.mobile))
        return false;
    }

    boolean this_present_email = true && this.isSetEmail();
    boolean that_present_email = true && that.isSetEmail();
    if (this_present_email || that_present_email) {
      if (!(this_present_email && that_present_email))
        return false;
      if (!this.email.equals(that.email))
        return false;
    }

    boolean this_present_validTime = true;
    boolean that_present_validTime = true;
    if (this_present_validTime || that_present_validTime) {
      if (!(this_present_validTime && that_present_validTime))
        return false;
      if (this.validTime != that.validTime)
        return false;
    }

    boolean this_present_emailSign = true && this.isSetEmailSign();
    boolean that_present_emailSign = true && that.isSetEmailSign();
    if (this_present_emailSign || that_present_emailSign) {
      if (!(this_present_emailSign && that_present_emailSign))
        return false;
      if (!this.emailSign.equals(that.emailSign))
        return false;
    }

    boolean this_present_uid = true && this.isSetUid();
    boolean that_present_uid = true && that.isSetUid();
    if (this_present_uid || that_present_uid) {
      if (!(this_present_uid && that_present_uid))
        return false;
      if (!this.uid.equals(that.uid))
        return false;
    }

    boolean this_present_itu = true && this.isSetItu();
    boolean that_present_itu = true && that.isSetItu();
    if (this_present_itu || that_present_itu) {
      if (!(this_present_itu && that_present_itu))
        return false;
      if (!this.itu.equals(that.itu))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_id = true && (isSetId());
    list.add(present_id);
    if (present_id)
      list.add(id);

    boolean present_code = true && (isSetCode());
    list.add(present_code);
    if (present_code)
      list.add(code);

    boolean present_mobile = true && (isSetMobile());
    list.add(present_mobile);
    if (present_mobile)
      list.add(mobile);

    boolean present_email = true && (isSetEmail());
    list.add(present_email);
    if (present_email)
      list.add(email);

    boolean present_validTime = true;
    list.add(present_validTime);
    if (present_validTime)
      list.add(validTime);

    boolean present_emailSign = true && (isSetEmailSign());
    list.add(present_emailSign);
    if (present_emailSign)
      list.add(emailSign);

    boolean present_uid = true && (isSetUid());
    list.add(present_uid);
    if (present_uid)
      list.add(uid);

    boolean present_itu = true && (isSetItu());
    list.add(present_itu);
    if (present_itu)
      list.add(itu);

    return list.hashCode();
  }

  @Override
  public int compareTo(Verify other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetId()).compareTo(other.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCode()).compareTo(other.isSetCode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.code, other.code);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetMobile()).compareTo(other.isSetMobile());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMobile()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.mobile, other.mobile);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetEmail()).compareTo(other.isSetEmail());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEmail()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.email, other.email);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetValidTime()).compareTo(other.isSetValidTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetValidTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.validTime, other.validTime);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetEmailSign()).compareTo(other.isSetEmailSign());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEmailSign()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.emailSign, other.emailSign);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetUid()).compareTo(other.isSetUid());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUid()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.uid, other.uid);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetItu()).compareTo(other.isSetItu());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetItu()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.itu, other.itu);
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
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Verify(");
    boolean first = true;

    if (isSetId()) {
      sb.append("id:");
      if (this.id == null) {
        sb.append("null");
      } else {
        sb.append(this.id);
      }
      first = false;
    }
    if (!first) sb.append(", ");
    sb.append("code:");
    if (this.code == null) {
      sb.append("null");
    } else {
      sb.append(this.code);
    }
    first = false;
    if (isSetMobile()) {
      if (!first) sb.append(", ");
      sb.append("mobile:");
      if (this.mobile == null) {
        sb.append("null");
      } else {
        sb.append(this.mobile);
      }
      first = false;
    }
    if (isSetEmail()) {
      if (!first) sb.append(", ");
      sb.append("email:");
      if (this.email == null) {
        sb.append("null");
      } else {
        sb.append(this.email);
      }
      first = false;
    }
    if (!first) sb.append(", ");
    sb.append("validTime:");
    sb.append(this.validTime);
    first = false;
    if (isSetEmailSign()) {
      if (!first) sb.append(", ");
      sb.append("emailSign:");
      if (this.emailSign == null) {
        sb.append("null");
      } else {
        sb.append(this.emailSign);
      }
      first = false;
    }
    if (isSetUid()) {
      if (!first) sb.append(", ");
      sb.append("uid:");
      if (this.uid == null) {
        sb.append("null");
      } else {
        sb.append(this.uid);
      }
      first = false;
    }
    if (isSetItu()) {
      if (!first) sb.append(", ");
      sb.append("itu:");
      if (this.itu == null) {
        sb.append("null");
      } else {
        sb.append(this.itu);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (code == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'code' was not present! Struct: " + toString());
    }
    // alas, we cannot check 'validTime' because it's a primitive and you chose the non-beans generator.
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class VerifyStandardSchemeFactory implements SchemeFactory {
    public VerifyStandardScheme getScheme() {
      return new VerifyStandardScheme();
    }
  }

  private static class VerifyStandardScheme extends StandardScheme<Verify> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Verify struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.id = iprot.readString();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // CODE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.code = iprot.readString();
              struct.setCodeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // MOBILE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.mobile = iprot.readString();
              struct.setMobileIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // EMAIL
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.email = iprot.readString();
              struct.setEmailIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // VALID_TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.validTime = iprot.readI64();
              struct.setValidTimeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // EMAIL_SIGN
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.emailSign = iprot.readString();
              struct.setEmailSignIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // UID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.uid = iprot.readString();
              struct.setUidIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 8: // ITU
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.itu = iprot.readString();
              struct.setItuIsSet(true);
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
      if (!struct.isSetValidTime()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'validTime' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, Verify struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.id != null) {
        if (struct.isSetId()) {
          oprot.writeFieldBegin(ID_FIELD_DESC);
          oprot.writeString(struct.id);
          oprot.writeFieldEnd();
        }
      }
      if (struct.code != null) {
        oprot.writeFieldBegin(CODE_FIELD_DESC);
        oprot.writeString(struct.code);
        oprot.writeFieldEnd();
      }
      if (struct.mobile != null) {
        if (struct.isSetMobile()) {
          oprot.writeFieldBegin(MOBILE_FIELD_DESC);
          oprot.writeString(struct.mobile);
          oprot.writeFieldEnd();
        }
      }
      if (struct.email != null) {
        if (struct.isSetEmail()) {
          oprot.writeFieldBegin(EMAIL_FIELD_DESC);
          oprot.writeString(struct.email);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldBegin(VALID_TIME_FIELD_DESC);
      oprot.writeI64(struct.validTime);
      oprot.writeFieldEnd();
      if (struct.emailSign != null) {
        if (struct.isSetEmailSign()) {
          oprot.writeFieldBegin(EMAIL_SIGN_FIELD_DESC);
          oprot.writeString(struct.emailSign);
          oprot.writeFieldEnd();
        }
      }
      if (struct.uid != null) {
        if (struct.isSetUid()) {
          oprot.writeFieldBegin(UID_FIELD_DESC);
          oprot.writeString(struct.uid);
          oprot.writeFieldEnd();
        }
      }
      if (struct.itu != null) {
        if (struct.isSetItu()) {
          oprot.writeFieldBegin(ITU_FIELD_DESC);
          oprot.writeString(struct.itu);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class VerifyTupleSchemeFactory implements SchemeFactory {
    public VerifyTupleScheme getScheme() {
      return new VerifyTupleScheme();
    }
  }

  private static class VerifyTupleScheme extends TupleScheme<Verify> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Verify struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeString(struct.code);
      oprot.writeI64(struct.validTime);
      BitSet optionals = new BitSet();
      if (struct.isSetId()) {
        optionals.set(0);
      }
      if (struct.isSetMobile()) {
        optionals.set(1);
      }
      if (struct.isSetEmail()) {
        optionals.set(2);
      }
      if (struct.isSetEmailSign()) {
        optionals.set(3);
      }
      if (struct.isSetUid()) {
        optionals.set(4);
      }
      if (struct.isSetItu()) {
        optionals.set(5);
      }
      oprot.writeBitSet(optionals, 6);
      if (struct.isSetId()) {
        oprot.writeString(struct.id);
      }
      if (struct.isSetMobile()) {
        oprot.writeString(struct.mobile);
      }
      if (struct.isSetEmail()) {
        oprot.writeString(struct.email);
      }
      if (struct.isSetEmailSign()) {
        oprot.writeString(struct.emailSign);
      }
      if (struct.isSetUid()) {
        oprot.writeString(struct.uid);
      }
      if (struct.isSetItu()) {
        oprot.writeString(struct.itu);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Verify struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.code = iprot.readString();
      struct.setCodeIsSet(true);
      struct.validTime = iprot.readI64();
      struct.setValidTimeIsSet(true);
      BitSet incoming = iprot.readBitSet(6);
      if (incoming.get(0)) {
        struct.id = iprot.readString();
        struct.setIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.mobile = iprot.readString();
        struct.setMobileIsSet(true);
      }
      if (incoming.get(2)) {
        struct.email = iprot.readString();
        struct.setEmailIsSet(true);
      }
      if (incoming.get(3)) {
        struct.emailSign = iprot.readString();
        struct.setEmailSignIsSet(true);
      }
      if (incoming.get(4)) {
        struct.uid = iprot.readString();
        struct.setUidIsSet(true);
      }
      if (incoming.get(5)) {
        struct.itu = iprot.readString();
        struct.setItuIsSet(true);
      }
    }
  }

}

