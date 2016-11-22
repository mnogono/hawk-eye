package com.wildcat.db.mongodb.active.record;

import com.wildcat.db.active.record.ActiveRecord;
import com.wildcat.db.data.model.Curve;

public class CurveActiveRecord extends MonodbDocumentActiveRecord<Curve> {
    @Override
    public CurveActiveRecord wrap(Curve record) {
        return (CurveActiveRecord) super.wrap(record);
    }
}
