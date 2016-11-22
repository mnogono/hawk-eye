package com.wildcat.db.mongodb.active.record;

import com.wildcat.db.active.record.ActiveRecord;
import com.wildcat.db.active.record.ActiveRecordFactory;
import com.wildcat.db.data.model.Curve;
import com.wildcat.db.data.model.Sample;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class SampleActiveRecord extends MonodbDocumentActiveRecord<Sample> {
    @Override
    public void delete() {
        ActiveRecord curveActiveRecord = ActiveRecordFactory.getInstance().getActiveRecord(Curve.class);

        List<Curve> curves = getCurves();
        for (Curve curve: curves) {
            curveActiveRecord.wrap(curve).delete();
        }

        super.delete();
    }

    public List<Curve> getCurves() {
        Query query = new Query();
        query.addCriteria(Criteria.where("sampleId").is(record.getId()));
        return operations.find(query, Curve.class);
    }

    public Curve getCurve(Curve.Type type, Curve.Kind kind) {
        Query query = new Query();
        query.addCriteria(Criteria.where("sampleId").is(record.getId()));
        query.addCriteria(Criteria.where("type").is(type));
        query.addCriteria(Criteria.where("kind").is(kind));
        return operations.findOne(query, Curve.class);
    }

    @Override
    public SampleActiveRecord wrap(Sample record) {
        return (SampleActiveRecord) super.wrap(record);
    }
}
