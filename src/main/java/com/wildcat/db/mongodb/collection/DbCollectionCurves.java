//package com.wildcat.db.mongodb.collection;
//
//import com.mongodb.client.MongoCollection;
//import com.wildcat.db.data.model.Curve;
//import org.bson.Document;
//
//public class DbCollectionCurves extends DbCollection<Curve> {
//    private static DbCollectionCurves instance;
//
//    public static DbCollectionCurves getInstance() {
//        if(instance == null) {
//            instance = new DbCollectionCurves();
//        }
//
//        return instance;
//    }
//
//    static public MongoCollection<Document> get() {
//        return getInstance().getDocumentCollection();
//    }
//
//    public DbCollectionCurves() {
//        super("curves", Curve.class);
//    }
//}
