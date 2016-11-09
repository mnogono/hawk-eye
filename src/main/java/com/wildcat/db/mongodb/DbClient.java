package com.wildcat.db.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import com.wildcat.db.mongodb.codec.provider.SampleCodecProvider;
import com.wildcat.db.mongodb.codec.provider.UserCodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

public class DbClient {
    private static DbClient instance;
    private static MongoClient client;
    private static MongoDatabase db;
    final public static String dbName = "hawk-eye";

    private static DbClient getInstance() {
        if (instance == null) {
            instance = new DbClient();
        }

        return instance;
    }

    public static MongoDatabase getDb() {
        if (db == null) {
            db = getInstance().client.getDatabase(dbName);
        }

        return db;
    }

    private DbClient() {
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                CodecRegistries.fromProviders(
                        new SampleCodecProvider(),
                        new UserCodecProvider()),
                MongoClient.getDefaultCodecRegistry());
        MongoClientOptions options = MongoClientOptions.builder().codecRegistry(codecRegistry).build();

        client = new MongoClient(new ServerAddress(), options);
    }

    public static MongoClient get() {
        return getInstance().client;
    }

}
