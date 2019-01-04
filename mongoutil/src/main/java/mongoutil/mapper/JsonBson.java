package mongoutil.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import jdk.nashorn.internal.runtime.regexp.joni.constants.NodeType;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Iterator;

import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

/**
 * Created by wuhuajie on 2017/10/27.
 */
public class JsonBson {

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

  public static Bson wrapperBaseUpdates(Bson bson, String owner, String ownerName, boolean created) throws Exception {
    if(created)
      return combine(
        set("base.updateTime", System.currentTimeMillis())
        , set("base.createTime", System.currentTimeMillis())
        , set("base.owner", owner)
        , set("base.ownerName", ownerName)
        , set("base.creator", owner)
        , set("base.creatorName", ownerName)
        , bson
      );
    else
      return combine(
      set("base.updateTime", System.currentTimeMillis())
      , set("base.owner", owner)
      , set("base.ownerName", ownerName)
      , bson
    );
  }

  /**
     * Set Base updates on every updates.
     * @param bson
     * @param owner
     * @param ownerName
     * @return
     * @throws Exception
     */
  public static Bson wrapperBaseUpdates(Bson bson, String owner, String ownerName) throws Exception {
    return wrapperBaseUpdates(bson, owner, ownerName, false);
  }

  /**
   * Set Base updates on every delete.
   * @param bson
   * @param owner
   * @param ownerName
   * @return
   * @throws Exception
   */
  public static Bson wrapperDelete(String owner, String ownerName) throws Exception {
    return wrapperBaseUpdates(set("base.removed", true), owner, ownerName);
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
    ArrayList<Bson> updates = new ArrayList<Bson>();

    return combine(json2bsonImpl(json, field, updates));
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
  private static ArrayList<Bson> json2bsonImpl(JsonNode json, String field, ArrayList<Bson> updates) throws Exception {
    switch (json.getNodeType()) {
      case OBJECT: {
        Iterator<String> iterator = json.fieldNames();

        if(field.trim().length() > 0) {
          field = field + ".";
        }

        while (iterator.hasNext()) {
          String subField = iterator.next();
          JsonNode subJson = json.get(subField);
          json2bsonImpl(subJson, field + subField, updates);
        }
        break;
      }
      case ARRAY: {
        Iterator<JsonNode> arrayIterator = json.elements();
        ArrayList documents = new ArrayList();
        while (arrayIterator.hasNext()) {
          /**
           * When set on Array, it will update  values entirely without prefix.
           */
          JsonNode arrayNode = arrayIterator.next();
          if(arrayNode.isObject()) {
            Document doc = json2document(arrayNode);
            documents.add(doc);
          } else {
            addBasicNode(documents, arrayNode);
          }
        }
        updates.add(new Document("$set", new Document(field, documents)));
        break;
      }
      case BOOLEAN: {
        updates.add(set(field, json.asBoolean()));
        break;
      }
      case NUMBER: {
        if(json.isFloatingPointNumber())
          updates.add(set(field, json.asDouble()));
        else if (json.isLong())
          updates.add(set(field, json.asLong()));
        else if (json.isInt())
          updates.add(set(field, json.asInt()));
        else
          ;
        break;
      }
      case STRING: {
        updates.add(set(field, json.asText()));
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
    return updates;
  }


  /**
   * convert json to embedded document.
   * 1. Convert json into org.bson.Document.
   * 2. Which will replace entire data structure.
   *
   * @param json
   * @return
   * @throws Exception
   */
  public static Document json2document(JsonNode json)
    throws Exception {
    return json2documentImpl(json, "", new Document());
  }

  /**
   * Implement json 2 document.
   * Recursive call.
   *
   * Usage: json data is array.
   *
   * @param jsonNode
   * @param field
   * @param document
   * @return
   * @throws Exception
   */
  private static Document json2documentImpl(JsonNode jsonNode, String field, Document document) throws Exception {
    switch (jsonNode.getNodeType()) {
      case OBJECT: {
        Iterator<String> iterator = jsonNode.fieldNames();

        while (iterator.hasNext()) {
          String subField = iterator.next();
          JsonNode subJson = jsonNode.get(subField);
          json2documentImpl(subJson, subField, document);
        }
        break;
      }
      case ARRAY: {
        Iterator<JsonNode> arrayIterator = jsonNode.elements();

        ArrayList documents = new ArrayList();

        while (arrayIterator.hasNext()) {
          /**
           * When set on Array, it will update  values entirely without prefix.
           */
          JsonNode arrayNode = arrayIterator.next();
          if(arrayNode.isObject()) {
            Document doc = json2documentImpl(arrayNode, "", new Document());
            documents.add(doc);
          } else {
            addBasicNode(documents, arrayNode);
          }
        }
        document.append(field, documents);
        break;
      }
      case BOOLEAN: {
        document.append(field, jsonNode.asBoolean());
        break;
      }
      case NUMBER: {
        if(jsonNode.isFloatingPointNumber())
          document.append(field, jsonNode.asDouble());
        else if (jsonNode.isLong())
          document.append(field, jsonNode.asLong());
        else if (jsonNode.isInt())
          document.append(field, jsonNode.asInt());
        else
          ;
        break;
      }
      case STRING: {
        document.append(field, jsonNode.asText());
        break;
      }
      case NULL:{
        //Nothing to do.
        break;
      }
      default: {
        throw new Exception("Contact Wu Huajie. Unsupported type in JsonNode" + jsonNode.toString() );
      }
    }
    return document;
  }



  /***
   * Add JsonNode value into ArrayList.
   * @param list
   * @param node
   * @return
   */
  private static ArrayList addBasicNode(ArrayList list, JsonNode node) {
    switch (node.getNodeType()) {
      case BOOLEAN: {
        list.add(node.asBoolean());
        break;
      }
      case NUMBER: {
        if (node.isFloatingPointNumber())
          list.add(node.asDouble());
        else if (node.isLong())
          list.add(node.asLong());
        else if (node.isInt())
          list.add(node.asInt());
        else
          ;
        break;
      }
      case STRING: {
        list.add(node.asText());
        break;
      }
      case NULL: {
        //Nothing to do.
        break;
      }
    }
    return list;
  }
}

