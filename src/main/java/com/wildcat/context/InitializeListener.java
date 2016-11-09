package com.wildcat.context;

import com.wildcat.db.mongodb.DbClient;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitializeListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DbClient.get();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
