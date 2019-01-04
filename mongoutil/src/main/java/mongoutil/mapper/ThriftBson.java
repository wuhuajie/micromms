package mongoutil.mapper;

import static com.mongodb.client.model.Updates.*;

import org.bson.Document;
import org.bson.conversions.Bson;

import org.apache.thrift.*;
import org.apache.thrift.meta_data.*;
import mongoutil.helper.MapperHelper;

import java.lang.reflect.*;

import java.util.List;
import java.util.ArrayList;

/****
 * Store thrift object into Mongodb.
 * Created by wuhuajie on 2017.
 *
 */
public class ThriftBson {
  /***
   * Convert thrift to embedded bson.
   * 1. Combine a list of com.mongodb.client.model.Updates into a single update.
   * 2. every single com.mongodb.client.model.Updates with dot notation field.
   * 3. If a field is an array in json, it will update the entire data in single field.
   *
   * @param thrift
   * @param fn
   * @return
   */
  public static Bson thrift2bson(TBase thrift, String fn) {
    ArrayList<Bson> updates = new ArrayList<Bson>();
    thrift2bson(thrift, fn, updates, true);
    return combine(updates);
  }

  /**
   * Convert thrift to embedded bson.
   * 1. Combine a list of com.mongodb.client.model.Updates into a single update.
   * 2. every single com.mongodb.client.model.Updates with dot notation field.
   * 3. If a field is an array in json, it will update the entire data in single field.
   * 4. if skipId is true, it will skip the field  when the field equals to "id"
   *
   * @param thrift
   * @param fn
   * @param updates
   * @param skipId
   */
  public static void thrift2bson(TBase thrift, String fn, ArrayList<Bson> updates, boolean skipId)  {
    java.util.Map<? extends TFieldIdEnum,FieldMetaData> mdm = org.apache.thrift.meta_data.FieldMetaData.getStructMetaDataMap(
      thrift.getClass()
    );

    if(fn.trim().length() > 0) {
      fn = fn + ".";
    }

    for(TFieldIdEnum key: mdm.keySet()) {
      if(mdm.get(key).valueMetaData.isStruct()) {
        if(thrift.getFieldValue(key) != null)
          thrift2bson((TBase) thrift.getFieldValue(key), fn + key.getFieldName(), updates, false);
      } else if(mdm.get(key).valueMetaData.isContainer()) {
        List list = (List)thrift.getFieldValue(key);
        ListMetaData listMetaData = (ListMetaData) mdm.get(key).valueMetaData;

        if(listMetaData.elemMetaData.isStruct()
          || listMetaData.elemMetaData.isContainer()
          ) {
          ArrayList<Bson> dbList = new ArrayList<Bson>();

          for(Object item: list) {
            Document document = thrift2document((TBase) item,  false);
            dbList.add(document);
          }
          updates.add(new Document("$set", new Document(fn + key.getFieldName(), dbList)));
        }
        else {
          ArrayList<Object> dbList = new ArrayList<Object>();
          for (Object item : list) {
            dbList.add(item);
          }
          updates.add(new Document("$set", new Document(fn + key.getFieldName(), dbList)));
        }
      } else {
        Object fieldValue = thrift.getFieldValue(key);

        if(thrift.isSet(key) && (
            (key.getFieldName().equals("id") && !skipId)
            || !key.getFieldName().equals("id")
          )) {
          if(key.getFieldName().equals("updateTime"))
            updates.add(set(fn + key.getFieldName(), System.currentTimeMillis()));
          else
            updates.add(set(fn + key.getFieldName(), fieldValue));
        }
      }
    }
    return;
  }


  /***
   * convert document to thrift.
   * 1. Convert document to thrift.
   * 2.
   *
   * @param sClass
   * @return
   * @throws InstantiationException
   * @throws IllegalAccessException
   * @throws NoSuchMethodException
   * @throws InvocationTargetException
   */
  public static Object document2thrift(Class sClass) throws InstantiationException, IllegalAccessException,NoSuchMethodException,InvocationTargetException {
      return document2thrift(sClass, new Document());
  }

  public static Object document2thrift(Class sClass, Document d) throws InstantiationException, IllegalAccessException,NoSuchMethodException,InvocationTargetException {
      if(d == null)
          d = new Document();

      Object obj = sClass.newInstance();
      java.util.Map<? extends TFieldIdEnum,FieldMetaData> mdm = FieldMetaData.getStructMetaDataMap(
          sClass
      );
      for(TFieldIdEnum key: mdm.keySet()) {
          if(mdm.get(key).valueMetaData.isStruct()) {
              StructMetaData structMetaData = (StructMetaData)mdm.get(key).valueMetaData;
              Document subD =  new Document();

              if(d.containsKey(mdm.get(key).fieldName)) {
                subD = d.get(mdm.get(key).fieldName, Document.class);
              }

              Object o = document2thrift(structMetaData.structClass, subD);
              Class[] params = {
                  TFieldIdEnum.class
                  , Object.class
              };
              Object[] paramsObjs = {
                  key, o
              };
              Method m = sClass.getMethod("setFieldValue", params);
              m.invoke(obj, paramsObjs);
          } else if(mdm.get(key).valueMetaData.isContainer()) {
              ListMetaData listMetaData = (ListMetaData) mdm.get(key).valueMetaData;
              ArrayList dblist = d.get(mdm.get(key).fieldName, ArrayList.class);

              List oo = new ArrayList();
              if (dblist != null) {
                  for (Object bdb : dblist) {
                      if (listMetaData.elemMetaData.type != org.apache.thrift.protocol.TType.STRUCT) {
                          oo.add(bdb);
                      } else {
                          StructMetaData structMetaData = (StructMetaData) listMetaData.elemMetaData;
                          Object o = document2thrift(structMetaData.structClass, (Document) bdb);
                          oo.add(o);
                      }
                  }
              }

              Class[] params = {
                  TFieldIdEnum.class
                  , Object.class
              };
              Object[] paramsObjs = {
                  key, oo
              };
              Method m = sClass.getMethod("setFieldValue", params);
              m.invoke(obj, paramsObjs);

          } else {
              Object v = MapperHelper.getBsonValue(mdm.get(key).valueMetaData.type
                  , key.getFieldName()
                  , d
              );
              Class[] params = {
                  TFieldIdEnum.class
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


  /**
   *
   * convert thrift to embedded document.
   * 1. Convert json into org.bson.Document.
   * 2. Which will replace entire data structure.
   *
   * @param thrift
   * @return
   */
  public static Document thrift2document(TBase thrift) {
        return thrift2document(thrift, true);
  }

  /**
   *
   * convert List[thrift] to embedded document.
   * 1. Convert json into org.bson.Document.
   * 2. Which will replace entire data structure.
   *
   * @param thrifts
   * @return
   */
  public static ArrayList<Bson> thrifts2document(List thrifts) {
    ArrayList<Bson> dbList = new ArrayList<Bson>();

    for(Object thrift: thrifts) {

      Document document = ThriftBson.thrift2document((TBase)thrift,  false);
      dbList.add(document);
    }

    return dbList;
  }


  /**
   * convert List[thrift] to embedded document.
   * 1. Convert json into org.bson.Document.
   * 2. Which will replace entire data structure.
   * 3. If skipId is true, it will skip the field  when the field equals to "id"
   *
   * @param thrift
   * @param skipId
   * @return
   */
  public static Document thrift2document(TBase thrift, boolean skipId)  {
        java.util.Map<? extends TFieldIdEnum,FieldMetaData> mdm = FieldMetaData.getStructMetaDataMap(
            thrift.getClass()
        );
        Document dbObject =  new Document();

        for(TFieldIdEnum key: mdm.keySet()) {
            if(mdm.get(key).valueMetaData.isStruct()) {
                if(thrift.getFieldValue(key) != null)
                  dbObject.append(
                    key.getFieldName()
                    , thrift2document((TBase) thrift.getFieldValue(key), false)
                  );
            } else if(mdm.get(key).valueMetaData.isContainer()) {
                List list = (List)thrift.getFieldValue(key);
                ListMetaData listMetaData = (ListMetaData) mdm.get(key).valueMetaData;
                if(listMetaData.elemMetaData.isStruct()
                    || listMetaData.elemMetaData.isContainer()
                    ) {
                    ArrayList<Document> dbList = new ArrayList<Document>();
                    for(Object item: list) {
//                        dbList.add(thrift2document((TBase) item,  new Document(), false));
                        dbList.add(thrift2document((TBase) item, false));
                    }
                    dbObject.append(
                        key.getFieldName()
                        , dbList
                    );
                }
                else {
                    ArrayList<Object> dbList = new ArrayList<Object>();
                    for (Object item : list) {
                        dbList.add(item);
                    }
                    dbObject.append(
                      key.getFieldName()
                        , dbList
                    );
                }
            } else {
              Object fieldValue = thrift.getFieldValue(key);

              if(thrift.isSet(key) && (
                (key.getFieldName().equals("id") && !skipId)
                  || !key.getFieldName().equals("id")
              )) {
                    if(key.getFieldName().equals("updateTime"))
                        dbObject.append(
                          key.getFieldName()
                            , System.currentTimeMillis()
                        );
                    else
                        dbObject.append(
                          key.getFieldName()
                            , fieldValue
                        );
                }
            }
        }
        return dbObject;
    }

}



