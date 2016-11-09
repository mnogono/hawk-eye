package com.wildcat.db.data.model;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Date;

public class Sample extends Document {
    /*
    private ObjectId _id;
    private String name;
    private Date startDate;
    private Date finishDate;
    */
    private User operator;

    public String getName() {
        //return name;
        return getString("name");
    }

    public void setName(String name) {
        //this.name = name;
        put("name", name);
    }

    public User getOperator() {
        return operator;
    }

    public void setOperator(User operator) {
        this.operator = operator;
        put("operator_id", operator.getId());
    }

    public ObjectId getId() {
        //return _id;
        return getObjectId("_id");
    }

    /*
    public void setId(ObjectId id) {
        this._id = id;
    }
    */
}

/**
 * exec add_col 'sample','vdb_id','[int] IDENTITY (1, 1) NOT NULL'
 exec add_col 'sample','vdb_rec','[int] NULL'
 exec add_col 'sample','vdb_cdate','[int] NULL'
 exec add_col 'sample','vdb_edate','[int] NULL'
 exec add_col 'sample','vdb_sid','[int] NULL'
 exec add_col 'sample','sample_id','[varchar] (255) NULL'
 exec add_col 'sample','sample_date','[int] NULL'
 exec add_col 'sample','sample_time','[int] NULL'
 exec add_col 'sample','sample_operator','[varchar] (50) NULL'
 exec add_col 'sample','sample_project_id','[varchar] (255) NULL'
 exec add_col 'sample','sample_well_id','[varchar] (255) NULL'
 exec add_col 'sample','sample_welbore_id','[varchar] (255) NULL'
 exec add_col 'sample','sample_method_id','[int] NULL'
 exec add_col 'sample','sample_sequence_id','[int] NULL'
 exec add_col 'sample','sample_depth','[float] NULL'
 exec add_col 'sample','sample_type','[int] NULL'
 exec add_col 'sample','sample_flag','[int] NULL'
 exec add_col 'sample','sample_comment','[text] NULL'
 exec add_col 'sample','sample_status','[int] NULL'
 exec add_col 'sample','sample_pos','[int] NULL'
 exec add_col 'sample','sample_weight','[float] NULL'
 exec add_col 'sample','sample_calibration_id','[int] NULL'
 exec add_col 'sample','sample_quality_result','[int] NULL'
 exec add_col 'sample','uid','varchar(50) NULL CONSTRAINT [DF_sample_uid]  DEFAULT (newid())'
 exec add_col 'sample','sample_data_format','[int] NULL'
 exec add_col 'sample','sample_data_file_path','[varchar] (255) NULL'
 exec add_col 'sample','sample_instrument_name','[varchar] (255) NULL'
 exec add_col 'sample','sample_project_link','[int] NULL'
 exec add_col 'sample','sample_well_link','[int] NULL'
 exec add_col 'sample','sample_wellbore_link','[int] NULL'
 exec add_col 'sample','sample_sequence_order','[int] NULL'
 exec add_col 'sample','sample_date_finish','[int] NULL'
 exec add_col 'sample','sample_time_finish','[int] NULL'
 exec add_col 'sample','sample_method_multiramp','[text] NULL'
 exec add_col 'sample','sample_method_data','[text] NULL'
 exec add_col 'sample','sample_flows_data','[text] NULL'
 exec add_col 'sample','sample_fid_temperature','[float] NULL'
 exec add_col 'sample','sample_style_id','[int] NULL'
 exec add_col 'sample','sample_hawk_version','[varchar] (255) NULL'
 exec add_col 'sample','sample_is_ir_rev_b','[int] NULL'
 exec add_col 'sample','sample_zones_data','[text] NULL'
 */