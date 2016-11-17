//package com.wildcat.db.mongodb.codec.provider;
//
//import com.wildcat.db.data.model.Sample;
//import com.wildcat.db.mongodb.codec.SampleCodec;
//import org.bson.codecs.Codec;
//import org.bson.codecs.configuration.CodecProvider;
//import org.bson.codecs.configuration.CodecRegistry;
//
//public class SampleCodecProvider implements CodecProvider {
//
//    @Override
//    @SuppressWarnings("unchecked")
//    public <T> Codec<T> get(Class<T> aClass, CodecRegistry codecRegistry) {
//        if (aClass == Sample.class) {
//            return (Codec<T>) SampleCodec.getInstance();
//        }
//        return null;
//    }
//}