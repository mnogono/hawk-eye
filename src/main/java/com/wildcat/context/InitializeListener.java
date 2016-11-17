package com.wildcat.context;

import com.wildcat.db.mongodb.DbClient;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.net.UnknownHostException;

public class InitializeListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            DbClient.get();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
