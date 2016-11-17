//package com.wildcat.db.data.model;
//
////import com.wildcat.db.data.model.curve.kind.Raw;
////import com.wildcat.db.data.model.curve.type.Fid;
////import com.wildcat.db.data.model.curve.type.Time;
////import com.wildcat.db.data.model.curve.type.Type;
////import com.wildcat.db.mongodb.collection.DbCollectionCurves;
////import com.wildcat.db.mongodb.collection.DbCollectionUsers;
//import org.bson.Document;
//import org.bson.types.ObjectId;
//
//public class Sample extends Document {
//    //private User operator;
//
//    //private Map<Class<? extends Type>, Curve> rawCurves;
//    //private List<Curve> curvesCalibrated;
//
//    public String getName() {
//        return getString("name");
//    }
//
//    public void setName(String name) {
//        put("name", name);
//    }
//
//    public User getOperator() {
//        return DbCollectionUsers.getInstance().findById(getObjectId("operator_id"));
//        //return operator;
//    }
//
//    public void setOperator(User operator) {
//        //this.operator = operator;
//        put("operator_id", operator.getId());
//    }
//
//    public ObjectId getId() {
//        return getObjectId("_id");
//    }
//
//    public void setRawCurve(Curve curve) {
//        Document jsonCurves = (Document) get("curves");
//        if (jsonCurves == null) {
//            jsonCurves = new Document();
//            put("curves", jsonCurves);
//        }
//
//        Document jsonRaw = (Document) jsonCurves.get("raw");
//        if (jsonRaw == null) {
//            jsonRaw = new Document();
//            jsonCurves.put("raw", jsonRaw);
//        }
//
//        jsonRaw.put(curve.getType().toString().toLowerCase(), curve.getObjectId("_id"));
//
////        Document jsonCurve = (Document) jsonRaw.get(key);
////
////        if (jsonCurve == null) {
////            jsonCurve = new Document();
////            jsonRaw.put(key, jsonCurve);
////        }
////
//
////
////        jsonCurve.put("data", curve.getData());
//
//        //rawCurves.put(curveType, curve);
//        //Class<? extends Type> clazz = curveType
//
//
//        /*
//        Class<CT> clazz = Class<CT>.getClass();
//        rawCurves.put(clazz, curve);
////        if (rawCurves.containsKey(Class<K>)) {
////
////        }
//        */
//    }
//
//    /*
//    public Map<Class<? extends Type>, Curve> getRawCurves() {
//        return rawCurves;
//    }
//    */
//
//    public Curve getRawCurve(Curve.Type type) {
//        Document jsonCurves = (Document) get("curves");
//        if (jsonCurves == null) {
//            return null;
//        }
//
//        Document jsonRaw = (Document) jsonCurves.get("raw");
//        if (jsonRaw == null) {
//            return null;
//        }
//
//        ObjectId id = jsonRaw.getObjectId(type.toString().toLowerCase());
//        if (id == null) {
//            return null;
//        }
//
//        //return DbCollectionCurves.getInstance().findById(id);
//        return null;
//    }
//
//    public Sample() {
//        //rawCurves = new HashMap<>();
//    }
///*
//    public void setId(ObjectId id) {
//        this._id = id;
//    }
//    */
//}
//
///**
// * exec add_col 'sample','vdb_id','[int] IDENTITY (1, 1) NOT NULL'
// exec add_col 'sample','vdb_rec','[int] NULL'
// exec add_col 'sample','vdb_cdate','[int] NULL'
// exec add_col 'sample','vdb_edate','[int] NULL'
// exec add_col 'sample','vdb_sid','[int] NULL'
// exec add_col 'sample','sample_id','[varchar] (255) NULL'
// exec add_col 'sample','sample_date','[int] NULL'
// exec add_col 'sample','sample_time','[int] NULL'
// exec add_col 'sample','sample_operator','[varchar] (50) NULL'
// exec add_col 'sample','sample_project_id','[varchar] (255) NULL'
// exec add_col 'sample','sample_well_id','[varchar] (255) NULL'
// exec add_col 'sample','sample_welbore_id','[varchar] (255) NULL'
// exec add_col 'sample','sample_method_id','[int] NULL'
// exec add_col 'sample','sample_sequence_id','[int] NULL'
// exec add_col 'sample','sample_depth','[float] NULL'
// exec add_col 'sample','sample_type','[int] NULL'
// exec add_col 'sample','sample_flag','[int] NULL'
// exec add_col 'sample','sample_comment','[text] NULL'
// exec add_col 'sample','sample_status','[int] NULL'
// exec add_col 'sample','sample_pos','[int] NULL'
// exec add_col 'sample','sample_weight','[float] NULL'
// exec add_col 'sample','sample_calibration_id','[int] NULL'
// exec add_col 'sample','sample_quality_result','[int] NULL'
// exec add_col 'sample','uid','varchar(50) NULL CONSTRAINT [DF_sample_uid]  DEFAULT (newid())'
// exec add_col 'sample','sample_data_format','[int] NULL'
// exec add_col 'sample','sample_data_file_path','[varchar] (255) NULL'
// exec add_col 'sample','sample_instrument_name','[varchar] (255) NULL'
// exec add_col 'sample','sample_project_link','[int] NULL'
// exec add_col 'sample','sample_well_link','[int] NULL'
// exec add_col 'sample','sample_wellbore_link','[int] NULL'
// exec add_col 'sample','sample_sequence_order','[int] NULL'
// exec add_col 'sample','sample_date_finish','[int] NULL'
// exec add_col 'sample','sample_time_finish','[int] NULL'
// exec add_col 'sample','sample_method_multiramp','[text] NULL'
// exec add_col 'sample','sample_method_data','[text] NULL'
// exec add_col 'sample','sample_flows_data','[text] NULL'
// exec add_col 'sample','sample_fid_temperature','[float] NULL'
// exec add_col 'sample','sample_style_id','[int] NULL'
// exec add_col 'sample','sample_hawk_version','[varchar] (255) NULL'
// exec add_col 'sample','sample_is_ir_rev_b','[int] NULL'
// exec add_col 'sample','sample_zones_data','[text] NULL'
// */