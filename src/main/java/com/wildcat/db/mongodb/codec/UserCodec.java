package com.wildcat.db.mongodb.codec;

import com.wildcat.db.data.model.User;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.EncoderContext;

public class UserCodec implements Codec<User> {
    private static Codec<Document> documentCodec = new DocumentCodec();
    private static UserCodec instance;

    final private static String FIELD_ID = "_id";
    final private static String FIELD_LOGIN = "login";
    final private static String FIELD_PASSWORD = "password";

    public static UserCodec getInstance() {
        if (instance == null) {
            instance = new UserCodec();
        }

        return instance;
    }

    @Override
    public User decode(BsonReader reader, DecoderContext decoderContext) {
        Document document = documentCodec.decode(reader, decoderContext);
        return toObject(document);
    }

    @Override
    public void encode(BsonWriter writer, User user, EncoderContext encoderContext) {
        //Document document = toDocument(user);
        documentCodec.encode(writer, user, encoderContext);
    }

    public User toObject(Document document) {
        User user = new User();
        //user.setId(document.getObjectId(FIELD_ID));
        user.setLogin(document.getString(FIELD_LOGIN));
        user.setPassword(document.getString(FIELD_PASSWORD));
        //user.setId(document.getObjectId("_id"));

        return user;
    }

    public Document toDocument(User user) {
        Document document = new Document();
        if (user.getId() != null) {
            document.put(FIELD_ID, user.getId());
        }
        document.put(FIELD_LOGIN, user.getLogin());
        document.put(FIELD_PASSWORD, user.getPassword());

        return document;
    }

    @Override
    public Class<User> getEncoderClass() {
        return User.class;
    }
}
