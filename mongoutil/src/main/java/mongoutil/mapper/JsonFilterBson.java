package mongoutil.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import jdk.nashorn.internal.runtime.regexp.joni.constants.NodeType;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Iterator;

import static com.mongodb.client.model.Filters.*;

/**
 * Created by wuhuajie on 2017/10/27.
 */
public class JsonFilterBson {

  /**
   * Convert json to embedded bson.
   * 1. Combine a list of com.mongodb.client.model.Updates into a single update.
   * 2. every single com.mongodb.client.model.Updates with dot notation field.
   * 3. If a field is an array in json, it will update the entire data in single field.

   * @param json
   * @return
   * @throws Exception
   */
  public static Bson json2bson(JsonNode json) throws Exception {
    return json2bson(json, "");
  }



  /**
   * convert json to embedded bson.
   * 1. Combine a list of com.mongodb.client.model.Updates into a single update.
   * 2. every single com.mongodb.client.model.Updates with dot notation field.
   * 3. If a field is an array in json, it will update the entire data in single field.
   * {
   *    "id":"593a37c3ac32dc2af9f93de7"
   *    , "col": "test"
   *    , "data": {
   *      "int": 10
   *      , "long": 104934
   *      , "double": 3.24
   *      , "string": "string"
   *      , "object.dot": "dot"
   *      , "object.dotint": 10
   *      , "object": {
   *        "int": 10
   *        , "long": 104934
   *        , "double": 3.24
   *        , "string": "string"
   *        , "strarray": [
   *            "1"
   *          , "2"
   *          , "3"
   *        ]
   *      }
   *      , "strarray": [
   *        "1"
   *        , "2"
   *        , "3"
   *       ]
   *      , "intarray": [
   *        1
   *        , 2
   *        , 3
   *      ]
   *      , "objectArray": [
   *        {
   *          "int": 10
   *        , "long": 104934
   *        , "double": 3.24
   *        , "string": "string"
   *        , "strarray": [
   *          "1"
   *          , "2"
   *          , "3"
   *        ]
   *        }
   *        {
   *          "int": 10
   *        , "long": 104934
   *        , "double": 3.24
   *        , "string": "string"
   *        , "strarray": [
   *          "1"
   *          , "2"
   *          , "3"
   *        ]
   *        }
   *      ]
   *    }
   *   }
   *
   *
   *
   * @param json
   * @return
   * @throws Exception
   */
  public static Bson json2bson(JsonNode json, String field)
    throws Exception {
    ArrayList<Bson> filter = new ArrayList<Bson>();

    return and(json2bsonImpl(json, field, filter));
  }


  /**
   * Implement json 2 bson.
   * Recursive call.
   * @param json
   * @param field
   * @param updates
   * @return
   * @throws Exception
   */
  private static ArrayList<Bson> json2bsonImpl(JsonNode json, String field, ArrayList<Bson> filters) throws Exception {
    switch (json.getNodeType()) {
      case OBJECT: {
        Iterator<String> iterator = json.fieldNames();

        if(field.trim().length() > 0) {
          field = field + ".";
        }

        while (iterator.hasNext()) {
          String subField = iterator.next();
          JsonNode subJson = json.get(subField);
          json2bsonImpl(subJson, field + subField, filters);
        }
        break;
      }
      case BOOLEAN: {
        eq(field, json.asBoolean());

        filters.add(eq(field, json.asBoolean()));
        break;
      }
      case NUMBER: {
        if(json.isFloatingPointNumber())
          filters.add(eq(field, json.asDouble()));
        else if (json.isLong())
          filters.add(eq(field, json.asLong()));
        else if (json.isInt())
          filters.add(eq(field, json.asInt()));
        else
          ;
        break;
      }
      case STRING: {
        filters.add(eq(field, json.asText()));
        break;
      }
      case NULL:{
        //Nothing to do.
        break;
      }
      default: {
        throw new Exception("Contact Wu Huajie. Unsupported type in JsonNode" + json.toString() );
      }
    }
    return filters;
  }
}

