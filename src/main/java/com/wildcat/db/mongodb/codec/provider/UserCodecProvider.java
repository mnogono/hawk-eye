package com.wildcat.db.mongodb.codec.provider;

import com.wildcat.db.data.model.User;
import com.wildcat.db.mongodb.codec.UserCodec;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

public class UserCodecProvider implements CodecProvider {
    @Override
    public <T> Codec<T> get(Class<T> aClass, CodecRegistry codecRegistry) {
        if (aClass == User.class) {
            return (Codec<T>) UserCodec.getInstance();
        }
        return null;
    }
}
