package mongoutil.data.client;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * Created by wuhuajie on 2017/5/8.
 */
public class DbPool {
  private static MongoClient _mongoClient = null;

  private DbPool(String seed) {
    makePool(seed);
  }

  public static MongoClient client(String seed) {
    if(_mongoClient == null)
      makePool(seed);
    return _mongoClient;
  }


  private static MongoClient makePool(String seed) {
    try {
      MongoClientURI connectionString = new MongoClientURI(seed);
      _mongoClient = new MongoClient(connectionString);

      return _mongoClient;
    } catch (Exception e) {
      return new MongoClient();
    }
  }

}
