package com.revature.servlets;

import com.revature.util.ConnectionFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DependencyLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            ConnectionFactory.getConnection();
            System.out.println("Context is initialized");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ConnectionFactory.close();
        System.out.println("Context is destroyed");
    }
}
