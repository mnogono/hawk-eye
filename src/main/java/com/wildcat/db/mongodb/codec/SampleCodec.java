//package com.wildcat.db.mongodb.codec;
//
//import com.wildcat.db.data.model.Sample;
////import com.wildcat.db.data.model.curve.type.Fid;
////import com.wildcat.db.data.model.curve.type.Time;
////import com.wildcat.db.data.model.curve.type.Type;
//import com.wildcat.db.mongodb.collection.DbCollectionUsers;
//import org.bson.BsonReader;
//import org.bson.BsonWriter;
//import org.bson.Document;
//import org.bson.codecs.Codec;
//import org.bson.codecs.DecoderContext;
//import org.bson.codecs.DocumentCodec;
//import org.bson.codecs.EncoderContext;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class SampleCodec implements Codec<Sample> {
//    private static Codec<Document> documentCodec = new DocumentCodec();
//    private static SampleCodec instance;
//
//    final private static String FIELD_ID = "_id";
//    final private static String FIELD_NAME = "name";
//    final private static String FIELD_OPERATOR_ID = "operator_id";
//    final private static String FIELD_CURVES = "curves";
//
//    public static SampleCodec getInstance() {
//        if (instance == null) {
//            instance = new SampleCodec();
//        }
//
//        return instance;
//    }
//
//    private SampleCodec() {}
//
//    @Override
//    public Sample decode(BsonReader reader, DecoderContext decoderContext) {
//        return (Sample) documentCodec.decode(reader, decoderContext);
//        //return toObject(document);
//    }
//
//    @Override
//    public void encode(BsonWriter writer, Sample sample, EncoderContext encoderContext) {
//        //Document document = toDocument(user);
//        documentCodec.encode(writer, sample, encoderContext);
//    }
//
//    public Sample toObject(Document document) {
//        Sample sample = new Sample();
//        //sample.setId(document.getObjectId(FIELD_ID));
//        sample.setName(document.getString(FIELD_NAME));
//        sample.setOperator(DbCollectionUsers.getInstance().findById(document.getObjectId(FIELD_OPERATOR_ID)));
//
//        Document jsonCurves = (Document) document.get(FIELD_CURVES);
//        if (jsonCurves != null) {
//            Document jsonRaw = (Document) jsonCurves.get("raw");
//            if (jsonRaw != null) {
//                for (String key : jsonRaw.keySet()) {
//                    if (key.equals("time")) {
//                        List<Double> data = new ArrayList<>();
//                        data = jsonRaw.get("data", data.getClass());
//                    }
//                }
//            }
//        }
//
//        return sample;
//    }
//
//    public Document toDocument(Sample sample) {
////        Document document = new Document();
////        if (sample.getId() != null) {
////            document.put(FIELD_ID, sample.getId());
////        }
////
////        document.put(FIELD_NAME, sample.getName());
////
////        if (sample.getOperator() != null) {
////            document.put(FIELD_OPERATOR_ID, sample.getOperator().getId());
////        }
////
////        Document jsonCurves = new Document();
////        Document jsonRaw = new Document();
////
////        Map<Class<? extends Type>, Curve> rawCurves = sample.getRawCurves();
////        if (rawCurves != null) {
////            for (Class<? extends Type> type : rawCurves.keySet()) {
////                Document jsonCurve = new Document();
////                Curve curve = rawCurves.get(type);
////
////                String key = null;
////                if (type == Time.class) {
////                    key = "time";
////                } else if (type == Fid.class) {
////                    key = "fid";
////                }
////
////                if (key != null) {
////                    jsonCurve.put("data", curve.getData());
////                    jsonRaw.put(key, jsonCurve);
////                }
////            }
////        }
////
////        jsonCurves.put("raw", jsonRaw);
////        document.put(FIELD_CURVES, jsonCurves);
////
////        return document;
//        return null;
//    }
//
//    @Override
//    public Class<Sample> getEncoderClass() {
//        return Sample.class;
//    }
//}
