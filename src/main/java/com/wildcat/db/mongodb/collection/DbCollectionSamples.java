package com.wildcat.db.mongodb.collection;

import com.mongodb.client.MongoCollection;
import com.wildcat.db.data.model.Sample;
import org.bson.Document;

public class DbCollectionSamples extends DbCollection<Sample> {
    private static DbCollectionSamples instance;

    public static DbCollectionSamples getInstance() {
        if(instance == null) {
            instance = new DbCollectionSamples();
        }

        return instance;
    }

    private DbCollectionSamples() {
        super("samples", Sample.class);
    }

    //static public MongoCollection<Sample> get() {
//        return getInstance().getCollection();
//    }

    static public MongoCollection<Document> get() {
        return getInstance().getDocumentCollection();
    }
}
