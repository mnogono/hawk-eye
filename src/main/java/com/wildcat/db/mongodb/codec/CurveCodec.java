//package com.wildcat.db.mongodb.codec;
//
//import com.wildcat.db.data.model.Curve;
//import org.bson.BsonReader;
//import org.bson.BsonWriter;
//import org.bson.Document;
//import org.bson.codecs.Codec;
//import org.bson.codecs.DecoderContext;
//import org.bson.codecs.DocumentCodec;
//import org.bson.codecs.EncoderContext;
//
//public class CurveCodec implements Codec<Curve> {
//    private static Codec<Document> documentCodec = new DocumentCodec();
//    private static CurveCodec instance;
//
//    public static CurveCodec getInstance() {
//        if (instance == null) {
//            instance = new CurveCodec();
//        }
//
//        return instance;
//    }
//
//    private CurveCodec() {}
//
//
//    @Override
//    public Curve decode(BsonReader reader, DecoderContext decoderContext) {
//        return (Curve) documentCodec.decode(reader, decoderContext);
//    }
//
//    @Override
//    public void encode(BsonWriter writer, Curve curve, EncoderContext encoderContext) {
//        documentCodec.encode(writer, curve, encoderContext);
//    }
//
//    @Override
//    public Class<Curve> getEncoderClass() {
//        return Curve.class;
//    }
//}
