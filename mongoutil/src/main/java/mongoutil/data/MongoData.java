package mongoutil.data;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;
import mongoutil.data.client.DbPool;
import mongoutil.helper.MapperHelper;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.in;

/**
 * Created by wuhuajie on 2017/8/14.
 */
public class MongoData {

  public static UpdateResult updateOne(String seed, String dbName, String colName, String id, Bson updates) throws Exception {
    UpdateOptions updateOptions = new UpdateOptions();
    updateOptions.upsert(true);
    return updateOne(seed, dbName, colName, MapperHelper.objectId(id), updates, updateOptions);
  }

  public static UpdateResult updateMany(String seed, String dbName, String colName, String id, Bson updates) throws Exception {
    return updateMany(seed, dbName, colName, MapperHelper.objectId(id), updates);
  }


  public static UpdateResult deleteMany(String seed,String dbName, String colName, List<String> ids, Bson updates) throws Exception {
    ArrayList<ObjectId> objectIds = new ArrayList<ObjectId>();
    for(String id : ids) {
      objectIds.add(new ObjectId(id));
    }
    UpdateOptions updateOptions = new UpdateOptions();
    return updateOne(seed, dbName, colName, in("_id", objectIds), updates, updateOptions);
  }


  private static UpdateResult updateOne(String seed, String dbName, String colName, Bson filter, Bson updates, UpdateOptions updateOptions) throws Exception {
    MongoCollection<Document> collection = DbPool.client(seed)
      .getDatabase(dbName).getCollection(colName);

    return collection.updateOne(filter, updates, updateOptions);
  }

  public static UpdateResult updateMany(String seed, String dbName, String colName, Bson filter, Bson updates) throws Exception {
    MongoCollection<Document> collection = DbPool.client(seed)
      .getDatabase(dbName).getCollection(colName);

    return collection.updateMany(filter, updates);
  }
}
