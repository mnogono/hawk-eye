package com.wildcat.data.mongodb.upload;

import com.wildcat.data.upload.ImporterPersistentLayer;
import com.wildcat.db.mongodb.DbClient;

public class ImporterMongodbPersistentLayer implements ImporterPersistentLayer {
    @Override
    public <T> boolean save(T data) {
        try {
            DbClient.getOperations().save(data);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
