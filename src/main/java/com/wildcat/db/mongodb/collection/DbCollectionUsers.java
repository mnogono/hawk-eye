package com.wildcat.db.mongodb.collection;


import com.mongodb.client.MongoCollection;
import com.wildcat.db.data.model.User;
import org.bson.Document;

public class DbCollectionUsers extends DbCollection<User> {
    private static DbCollectionUsers instance;

    public static DbCollectionUsers getInstance() {
        if(instance == null) {
            instance = new DbCollectionUsers();
        }

        return instance;
    }

    private DbCollectionUsers() {
        super("users", User.class);
    }

    /*
    static public MongoCollection<User> get() {
        return getInstance().getCollection();
    }
    */

    static public MongoCollection<Document> get() {
        return getInstance().getDocumentCollection();
    }

    /*
    public static MongoCollection<User> get() {
        if (collection == null) {
            collection = getInstance().db.getCollection(name).withDocumentClass(User.class);
        }

        return collection;
    }
    */

//    public static List<User> findAll() {
//        List<User> users = new ArrayList<>();
//        try (MongoCursor<User> cursor = DbCollectionUsers.get().find().iterator()) {
//            while (cursor.hasNext()) {
//                users.add(cursor.next());
//            }
//        }
//        return users;
//    }

    /*
    public static Organization findBy(Document query) {
        try (MongoCursor<Organization> cursor = DbCollectionOrganization.get().find(query).iterator()) {
            if (cursor.hasNext()) {
                return cursor.next();
            }
        }

        return null;
    }

    public static Organization findByUserId(String userId) {
        Document query = new Document("uuid", new Document("$eq", userId));
        return findBy(query);
    }

    public static Organization findOneAndUpdateByUserId(String clientId, Organization organization) {
        Document query = new Document("uuid", new Document("$eq", clientId));
        Document update = new Document("$set", organization);

        return get().findOneAndUpdate(query, update);
    }
    */
}


