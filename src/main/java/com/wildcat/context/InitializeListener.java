package com.wildcat.context;

import com.wildcat.db.active.record.ActiveRecordFactory;
import com.wildcat.db.data.model.Curve;
import com.wildcat.db.data.model.Sample;
import com.wildcat.db.mongodb.DbClient;
import com.wildcat.db.mongodb.active.record.CurveActiveRecord;
import com.wildcat.db.mongodb.active.record.MonodbDocumentActiveRecord;
import com.wildcat.db.mongodb.active.record.SampleActiveRecord;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.net.UnknownHostException;

@WebListener
public class InitializeListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            DbClient.get();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        //register active records
        ActiveRecordFactory activeRecordFactory = ActiveRecordFactory.getInstance();
        activeRecordFactory.registerActiveRecord(Sample.class, new SampleActiveRecord());
        activeRecordFactory.registerActiveRecord(Curve.class, new CurveActiveRecord());
        activeRecordFactory.setDefaultActiveRecord(new MonodbDocumentActiveRecord<>());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
