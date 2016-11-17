//package com.wildcat.db.mongodb.collection;
//
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoCursor;
//import com.mongodb.client.MongoDatabase;
//import com.wildcat.db.mongodb.DbClient;
//import org.bson.Document;
//import org.bson.types.ObjectId;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DbCollection<T> {
//    protected static MongoDatabase db = DbClient.getDb();
//    protected MongoCollection<T> collection;
//    protected MongoCollection documentCollection;
//
//    //collection name
//    final public String name;
//
//    //collection object class
//    final public Class<T> clazz;
//
//    public DbCollection(String name, Class<T> clazz) {
//        this.name = name;
//        this.clazz = clazz;
//
//        if(!exist(name)) {
//            db.createCollection(name);
//        }
//
//        documentCollection = db.getCollection(name);
//
//        collection = db.getCollection(name).withDocumentClass(clazz);
//    }
//
//    /**
//     * check does the collection exist in database
//     * @param collectionName collection name
//     * @return exist or not
//     */
//    public static boolean exist(String collectionName) {
//        for (String name : db.listCollectionNames()) {
//            if (name.equals(collectionName)) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    /**
//     * return collections or empty list
//     * @return collection of objects
//     */
//    public List<T> findAll() {
//        List<T> objects = new ArrayList<>();
//        try (MongoCursor<T> cursor = collection.find().iterator()) {
//            while (cursor.hasNext()) {
//                objects.add(cursor.next());
//            }
//        }
//        return objects;
//    }
//
//    /**
//     * return first find object
//     * @param query criteria to find
//     * @return T or null
//     */
//    public T findFirstBy(Document query) {
//        return collection.find(query).first();
//    }
//
//    public T findById(ObjectId id) {
//        return findFirstBy(new Document("_id", id));
//    }
//
//    public MongoCollection<T> getCollection() {
//        return collection;
//    }
//
//    public MongoCollection getDocumentCollection() {
//        return documentCollection;
//    }
//}
