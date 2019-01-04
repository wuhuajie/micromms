
package mongoutil.helper;

import com.fasterxml.jackson.databind.JsonNode;
import org.bson.*;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Dance Dto Convertor.
 *
 */
public class MapperHelper {
  public static Object getJsonValue(Byte t, JsonNode d) {
    Object ret = "";
    switch (t) {
      case org.apache.thrift.protocol.TType.STRING:
        if(d != null)
          ret = d.asText();
        else
          ret =  "";

        break;

      case org.apache.thrift.protocol.TType.I64:
      //        d.getAsOrElse[Long](fieldName, 0L)
        if(d != null)
          ret =  d.asLong();
        else
          ret =  0L;
        break;

      case org.apache.thrift.protocol.TType.I32:
      //        d.getAsOrElse[Int](fieldName, 0)
        if(d != null)
          ret =  d.asInt();
        else
          ret =  -1;
        break;
      case org.apache.thrift.protocol.TType.BOOL:
      //        d.getAsOrElse[Boolean](fieldName, false)
      //        d.findPath(fieldName).asBoolean(false)
        if(d != null)
          ret =  d.asBoolean();
        else
          ret =  false;
        break;
      case org.apache.thrift.protocol.TType.DOUBLE:
      //        d.getAsOrElse[Double](fieldName, 0.0)
      //        d.findPath(fieldName).asDouble(0.0)
        if(d != null)
          ret =  d.asDouble();
        else
          ret =  0.0;
        break;
      default:
        ret =  "";
        break;
    }
    return ret;
  }

  public static Object getJsonValue(Byte t, String fieldName, JsonNode d) {
    Object ret = "";

    switch (t) {
      case org.apache.thrift.protocol.TType.STRING:
        if(d != null && d.has(fieldName))
          ret = d.get(fieldName).asText();
        else
          ret = "";
        break;
      case org.apache.thrift.protocol.TType.I64:
        if(d != null && d.has(fieldName))
          ret = d.get(fieldName).asLong();
        else
          ret = 0L;
        break;
      case org.apache.thrift.protocol.TType.I32:
      //        d.getAsOrElse[Int](fieldName, 0)
        if(d != null && d.has(fieldName))
          ret = d.get(fieldName).asInt();
        else
          ret = -1;
        break;
      case org.apache.thrift.protocol.TType.BOOL:
      //        d.getAsOrElse[Boolean](fieldName, false)
      //        d.findPath(fieldName).asBoolean(false)
        if(d != null && d.has(fieldName))
          ret = d.get(fieldName).asBoolean();
        else
          ret = false;
        break;
      case org.apache.thrift.protocol.TType.DOUBLE:
        if(d != null && d.has(fieldName))
          ret = d.get(fieldName).asDouble();
        else
          ret = 0.0;
        break;
      default:
        ret = "";
        break;
      }
    return ret;
  }


  public static Object getBsonValue(Byte t , String field, Document dd) {
    Object ret = "";
    String fieldName = field;
    if(field.equalsIgnoreCase("id") )
      fieldName = "_id";

    Document d = dd;
    if(d == null)
      d = new Document();

    switch (t) {
      case org.apache.thrift.protocol.TType.STRING:
        if(fieldName.equalsIgnoreCase("_id")) {
          if(d != null && d.containsKey(fieldName))
            ret = d.getObjectId(fieldName).toString();
          else {
            String str = d.getString("id");
            if (str != null)
              ret = str;
            else
              ret = "";
          }
        } else {
          String str = d.getString(fieldName);
          if (str != null)
            ret = str;
          else
            ret = "";
        }
        break;
      case org.apache.thrift.protocol.TType.I64:
        ret = getLong(d, fieldName, 0L);
        break;

      case org.apache.thrift.protocol.TType.I32:
        ret = d.getInteger(fieldName, 0);
        break;
      case org.apache.thrift.protocol.TType.BOOL:
        ret = false;
        try {
          ret = d.getBoolean(fieldName);
        } catch(Exception e) {

        }
        if(ret != null)
          ;
        else
          ret = false;
        break;
      case org.apache.thrift.protocol.TType.DOUBLE:
        ret = getDouble(d, fieldName, 0.0);
        break;
      case org.apache.thrift.protocol.TType.LIST:
        ret = (List<Object>)d.values();
        break;
      default:
        break;
    }
    return ret;
  }

  public static double getDouble(Document document, String key, double defaultValue) {
    double value = defaultValue;
    try {
      value =  document.getDouble(key);
    } catch (Exception e) {
      try {
        value =  document.getInteger(key, 0);
      } catch (Exception ee) {
        e.printStackTrace();
        ee.printStackTrace();
      }
    }
    return value;
  }

  public static long getLong(Document document, String key, long defaultValue) {
    long value = defaultValue;
    try {
      value =  document.getLong(key);
    } catch (Exception e) {


      try {
        value =  document.getInteger(key, 0);
      } catch (Exception ee) {

      }
    }
    return value;
  }

  public static Bson objectId(String uid) {
    Bson bson = eq("_id", "notchancetogetdata");
    if(isEmpty(uid)) {
      bson = eq("_id", new ObjectId());
    } else {
      try {
        bson = eq("_id", new ObjectId(uid));
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        return bson;
      }
    }
    return bson;
  }

  public static Boolean isEmpty(String s) {
    return s == null || s.trim() == "";
  }
}
