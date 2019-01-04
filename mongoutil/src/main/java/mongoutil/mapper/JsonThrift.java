package mongoutil.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.ListMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import mongoutil.helper.MapperHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuhuajie on 2017/10/27.
 * Used on Controller layer.
 * Convert json from client to thrift object.
 */
public class JsonThrift {
  /***
   * Convert json（nodetype is array）to List[thrift].
   * 1. It will parse by the field defined in thrift.
   * 2. Field in json should equals to field in thrift.
   *
   * @param sClass class name in list
   * @param json ArrayJson.
   * @return
   * @throws InstantiationException
   * @throws IllegalAccessException
   * @throws NoSuchMethodException
   * @throws InvocationTargetException
   */
  public static ArrayList json2thriftlist(Class sClass, JsonNode json) throws InstantiationException, IllegalAccessException,NoSuchMethodException,InvocationTargetException {
    ArrayList oo = new ArrayList();
    java.util.Iterator<JsonNode> it = json.elements();
    while(it.hasNext()) {
      JsonNode node = it.next();
      oo.add(json2thrift(sClass, node));
    }
    return oo;
  }

  /***
   * Convert json（nodetype is object）to thrift.
   * 1. It will parse by the field defined in thrift.
   * 2. Field in json should equals to field in thrift.
   *
   * @param sClass class name in list
   * @param json ObjectJson.
   * @return
   * @throws InstantiationException
   * @throws IllegalAccessException
   * @throws NoSuchMethodException
   * @throws InvocationTargetException
   */
  public static Object json2thrift(Class sClass, JsonNode json) throws InstantiationException, IllegalAccessException,NoSuchMethodException,InvocationTargetException {
    Object obj = sClass.newInstance();
    java.util.Map<? extends TFieldIdEnum,FieldMetaData> mdm = org.apache.thrift.meta_data.FieldMetaData.getStructMetaDataMap(
      sClass
    );
    for(TFieldIdEnum key: mdm.keySet()) {
      if(mdm.get(key).valueMetaData.isStruct()) {
        //It's object
        StructMetaData structMetaData = (StructMetaData)mdm.get(key).valueMetaData;

        JsonNode subD = json.get(mdm.get(key).fieldName);
        if(subD == null) {
          subD = JsonNodeFactory.instance.objectNode();
        }

        Object o = json2thrift(structMetaData.structClass, subD);
        Class[] params = {
          org.apache.thrift.TFieldIdEnum.class
          , Object.class
        };
        Object[] paramsObjs = {
          key, o
        };
        Method m = sClass.getMethod("setFieldValue", params);
        m.invoke(obj, paramsObjs);
      } else if(mdm.get(key).valueMetaData.isContainer()) {
        //It's list
        ListMetaData listMetaData = (ListMetaData)mdm.get(key).valueMetaData;

        List oo = new ArrayList();
        if(json.get(mdm.get(key).fieldName) != null) {
          java.util.Iterator<JsonNode> it = json.get(mdm.get(key).fieldName).elements();
          while (it.hasNext()) {
            JsonNode node = it.next();
            if(listMetaData.elemMetaData.type != org.apache.thrift.protocol.TType.STRUCT) {
              oo.add(MapperHelper.getJsonValue(listMetaData.elemMetaData.type
                , node));
            } else {
              StructMetaData structMetaData = (StructMetaData) listMetaData.elemMetaData;
              Object o = json2thrift(structMetaData.structClass, node);
              oo.add(o);
            }
          }
        }

        Class[] params = {
          org.apache.thrift.TFieldIdEnum.class
          , Object.class
        };
        Object[] paramsObjs = {
          key, oo
        };
        Method m = sClass.getMethod("setFieldValue", params);
        m.invoke(obj, paramsObjs);

      } else {
        //It's basic type.
        Object v = MapperHelper.getJsonValue(mdm.get(key).valueMetaData.type
          , key.getFieldName()
          , json
        );
        Class[] params = {
          org.apache.thrift.TFieldIdEnum.class
          , Object.class
        };
        Object[] objs = {
          key, v
        };
        Method m = sClass.getMethod("setFieldValue", params);
        m.invoke(obj, objs);
      }
    }
    return obj;
  }

}
