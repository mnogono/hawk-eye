//package com.wildcat.db.mongodb.codec.provider;
//
//import com.wildcat.db.data.model.Curve;
//import com.wildcat.db.mongodb.codec.CurveCodec;
//import org.bson.codecs.Codec;
//import org.bson.codecs.configuration.CodecProvider;
//import org.bson.codecs.configuration.CodecRegistry;
//
//public class CurveCodecProvider implements CodecProvider{
//    @Override
//    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
//        if (clazz == Curve.class) {
//            return (Codec<T>) CurveCodec.getInstance();
//        }
//        return null;
//    }
//}
