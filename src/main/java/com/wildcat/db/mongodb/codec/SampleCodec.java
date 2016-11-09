package com.wildcat.db.mongodb.codec;

import com.wildcat.db.data.model.Sample;
import com.wildcat.db.mongodb.collection.DbCollectionUsers;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.EncoderContext;

public class SampleCodec implements Codec<Sample> {
    private static Codec<Document> documentCodec = new DocumentCodec();
    private static SampleCodec instance;

    final private static String FIELD_ID = "_id";
    final private static String FIELD_NAME = "name";
    final private static String FIELD_OPERATOR_ID = "operator_id";

    public static SampleCodec getInstance() {
        if (instance == null) {
            instance = new SampleCodec();
        }

        return instance;
    }

    private SampleCodec() {}

    @Override
    public Sample decode(BsonReader reader, DecoderContext decoderContext) {
        Document document = documentCodec.decode(reader, decoderContext);
        return toObject(document);
    }

    @Override
    public void encode(BsonWriter writer, Sample user, EncoderContext encoderContext) {
        //Document document = toDocument(user);
        documentCodec.encode(writer, user, encoderContext);
    }

    public Sample toObject(Document document) {
        Sample sample = new Sample();
        //sample.setId(document.getObjectId(FIELD_ID));
        sample.setName(document.getString(FIELD_NAME));
        sample.setOperator(DbCollectionUsers.getInstance().findById(document.getObjectId(FIELD_OPERATOR_ID)));

        return sample;
    }

    public Document toDocument(Sample sample) {
        Document document = new Document();
        if (sample.getId() != null) {
            document.put(FIELD_ID, sample.getId());
        }
        document.put(FIELD_NAME, sample.getName());
        if (sample.getOperator() != null) {
            document.put(FIELD_OPERATOR_ID, sample.getOperator().getId());
        }
        return document;
    }

    @Override
    public Class<Sample> getEncoderClass() {
        return Sample.class;
    }
}
