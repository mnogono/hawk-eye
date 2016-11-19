package com.wildcat.db.active.record;

import java.util.HashMap;
import java.util.Map;

public class ActiveRecordFactory<T, AR extends ActiveRecord<T>> {
    private Map<Class<T>, AR> activeRecords;
    private AR defaultActiveRecord;

    static ActiveRecordFactory instance;

    static public ActiveRecordFactory getInstance() {
        if (instance == null) {
            instance = new ActiveRecordFactory();
        }

        return instance;
    }

    private ActiveRecordFactory() {
        activeRecords = new HashMap<>();
    }

    public void setDefaultActiveRecord(AR defaultActiveRecord) {
        this.defaultActiveRecord = defaultActiveRecord;
    }

    public void registerActiveRecord(Class<T> type, AR activeRecord) {
        if (!activeRecords.containsKey(type)) {
            activeRecords.put(type, activeRecord);
        }
    }

    public AR getActiveRecord(Class<T> type) {
        if (activeRecords.containsKey(type)) {
            return activeRecords.get(type);
        }

        return defaultActiveRecord;
    }
}
