package com.wildcat.db.mongodb.active.record;

import com.wildcat.db.active.record.ActiveRecord;
import com.wildcat.db.mongodb.DbClient;
import org.springframework.data.mongodb.core.MongoOperations;

public class MonodbDocumentActiveRecord<T> extends ActiveRecord<T> {
    static MongoOperations operations = DbClient.getOperations();

    public void save() {
        operations.save(record);
    }

    @Override
    public void delete() {
        operations.remove(record);
    }
}
