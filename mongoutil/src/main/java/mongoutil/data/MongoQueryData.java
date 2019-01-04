package mongoutil.data;

//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import mongoutil.mapper.ThriftBson;
import mongoutil.data.client.DbPool;
import org.bson.Document;
import org.bson.conversions.Bson;
//import util.mapper.JsonThrift;

import java.util.ArrayList;

/**
 * Created by wuhuajie on 2017/10/31.
 */
public class MongoQueryData {

//  /**
//   *
//   * @param dbName
//   * @param colName
//   * @param id
//   * @param sClass
//   * @return
//   * @throws Exception
//   */
//  public static Object getObject(String dbName, String colName,  String id, Class sClass) throws Exception {
//    Bson q = DocumentHelper.wrapperQuery(
//      DocumentHelper.objectId(id)
//    );
//    return getObject(dbName, colName
//      , DocumentHelper.wrapperQuery(
//        DocumentHelper.objectId(id)
//      ), sClass);
//  }

  /**
   *
    * @param dbName
   * @param colName
   * @param query
   * @param sClass
   * @return
   * @throws Exception
   */
  public static Object getObject(String seed, String dbName, String colName, Bson query, Class sClass) throws Exception {
    MongoCollection<Document> collection = DbPool.client(seed)
      .getDatabase(dbName).getCollection(colName);

    return ThriftBson.document2thrift(sClass
      , collection.find(query).first());
  }

//  /**
//   *
//   * @param dbName
//   * @param colName
//   * @param query
//   * @param bson
//   * @param sorts
//   * @param sort
//   * @param pn
//   * @param ps
//   * @param sClass
//   * @return
//   * @throws Exception
//   */
//  public static ArrayList getObjects(String dbName, String colName
//    , String query, Bson bson
//    , String sorts, Bson sort
//    , int pn, int ps, Class sClass) throws Exception {
//    return getObjects(dbName, colName
//      , parseKvs(query), bson
//      , parseKvs(sorts), sort
//      , pn
//      , ps
//      , sClass
//    );
//  }

//  /**
//   *
//   * @param dbName
//   * @param colName
//   * @param query
//   * @param bson
//   * @param sorts
//   * @param sort
//   * @param pn
//   * @param ps
//   * @param sClass
//   * @return
//   * @throws Exception
//   */
//  public static ArrayList getObjects(String dbName, String colName
//    , ArrayList<KV> query, Bson bson
//    , ArrayList<KV> sorts, Bson sort
//    , int pn, int ps, Class sClass) throws Exception {
//    return getObjects(dbName, colName
//      , DocumentHelper.wrapperQuery(DocumentHelper.bsonFilters(query, bson))
//      , DocumentHelper.bsonSorts(sorts, sort)
//      , pn
//      , ps
//      , sClass
//    );
//  }

  /**
   *
   * @param dbName
   * @param colName
   * @param query
   * @param sorts
   * @param pn
   * @param ps
   * @param sClass
   * @return
   * @throws Exception
   */
  public static ArrayList getObjects(String seed, String dbName, String colName, Bson query, Bson sorts, int pn, int ps, final Class sClass) throws Exception {
    final ArrayList myObjs = new ArrayList();

    Block<Document> rowBlock = new Block<Document>() {
//      @Override
      public void apply(final Document document) {
        try {
          myObjs.add(ThriftBson.document2thrift(sClass, document));
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    };

    MongoCollection<Document> collection = DbPool.client(seed)
      .getDatabase(dbName).getCollection(colName);

    if(query != null)
      collection.find(query).sort(
          sorts
      ).skip((pn - 1)*ps).limit(ps).forEach(rowBlock);
    else
      collection.find().sort(
        sorts
      ).skip((pn - 1)*ps).limit(ps).forEach(rowBlock);
    return myObjs;
  }


//  public static long countObjects(String dbName, String colName
//    , String query, Bson bson
//    ) throws Exception {
//    return countObjects(dbName, colName
//      , parseKvs(query), bson
//      );
//  }

//  public static long countObjects(String dbName, String colName
//    , ArrayList<KV> query, Bson bson
//    ) throws Exception {
//    return countObjects(dbName, colName
//      , DocumentHelper.wrapperQuery(DocumentHelper.bsonFilters(query, bson))
//      );
//  }


  public static long countObjects(String seed, String dbName, String colName, Bson query) throws Exception {
    MongoCollection<Document> collection = DbPool.client(seed)
      .getDatabase(dbName).getCollection(colName);

    return collection.count(query);
  }


//  private static ArrayList<KV> parseKvs(String kvs) {
//    ArrayList<KV> oo = new ArrayList<KV>();
//    if(!StringUtil.isEmpty(kvs)) {
//      try {
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode json = mapper.readTree(kvs);
//        oo = JsonThrift.json2thriftlist(KV.class, json);
//      } catch (Exception e) {
//        logger.error(e.getLocalizedMessage());
//      }
//    }
//    return oo;
//  }
}
