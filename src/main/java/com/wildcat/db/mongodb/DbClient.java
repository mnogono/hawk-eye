package com.wildcat.db.mongodb;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
//import com.mongodb.client.MongoDatabase;
//import com.wildcat.db.mongodb.codec.provider.CurveCodecProvider;
//import com.wildcat.db.mongodb.codec.provider.SampleCodecProvider;
//import com.wildcat.db.mongodb.codec.provider.UserCodecProvider;
//import org.bson.codecs.DocumentCodecProvider;
//import org.bson.codecs.configuration.CodecRegistries;
//import org.bson.codecs.configuration.CodecRegistry;

import java.net.UnknownHostException;

public class DbClient {
    private static DbClient instance;
    private static MongoClient client;
    //private static MongoDatabase db;
    private static DB db;
    private static MongoOperations operations;

    final public static String dbName = "hawk-eye";

    private static DbClient getInstance() throws UnknownHostException {
        if (instance == null) {
            instance = new DbClient();
        }

        return instance;
    }

/*
    public static MongoDatabase getDb() {
        if (db == null) {
            db = getInstance().client.getDatabase(dbName);
        }

        return db;
    }
*/

    public static DB getDb() throws UnknownHostException {
        if (db == null) {
            db = getInstance().client.getDB(dbName);
        }

        return db;
    }

    private DbClient() throws UnknownHostException {
/*
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                CodecRegistries.fromProviders(
                        new SampleCodecProvider(),
                        new UserCodecProvider(),
                        new CurveCodecProvider()),
                MongoClient.getDefaultCodecRegistry());

        MongoClientOptions options = MongoClientOptions.builder().codecRegistry(codecRegistry).build();

        client = new MongoClient(new ServerAddress(), options);
*/
        client = new MongoClient();
    }

    public static MongoClient get() throws UnknownHostException {
        return getInstance().client;
    }

    public static MongoOperations getOperations() {
        if (operations == null) {
            try {
                operations = new MongoTemplate(get(), DbClient.dbName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return operations;
    }
}
