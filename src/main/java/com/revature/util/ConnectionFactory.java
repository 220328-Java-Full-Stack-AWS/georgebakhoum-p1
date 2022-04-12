package com.revature.util;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * <p>This ConnectionFactory class follows the Singleton Design Pattern and facilitates obtaining a connection to a Database for the ERS application.</p>
 * <p>Following the Singleton Design Pattern, the provided Constructor is private, and you obtain an instance via the {@link ConnectionFactory#getInstance()} method.</p>
 */
public class ConnectionFactory {

    private static Connection connection;
    private static ConnectionFactory instance;

    private ConnectionFactory() {
        //super();
    }

    /**
     * <p>This method follows the Singleton Design Pattern to restrict this class to only having 1 instance.</p>
     * <p>It is invoked via:</p>
     *
     * {@code ConnectionFactory.getInstance()}
     */
    public static ConnectionFactory getInstance() throws SQLException, IOException {
        if(instance == null) {
            instance = new ConnectionFactory();
        }

        return instance;
    }

    private static ConnectionFactory connect() throws IOException, SQLException {
        Properties props = new Properties();

        /*ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream input = loader.getResourceAsStream("application.properties");
        props.load(input);*/

        FileReader fr = new FileReader("src/main/resources/application.properties");
        props.load(fr);

        String connectionString = "jbc:postgresql://" +
                props.getProperty("hostname") + ":" +
                props.getProperty("port") + "/" +
                props.getProperty("dbname"); /* + "?user=" +
                props.getProperty("username") + "&password=" +
                props.getProperty("password");*/

        String username = props.getProperty("username");
        String password = props.getProperty("password");
        connection = DriverManager.getConnection(connectionString, username, password);


    }

    /**
     * <p>The {@link ConnectionFactory#getConnection()} method is responsible for leveraging a specific Database Driver to obtain an instance of the {@link java.sql.Connection} interface.</p>
     * <p>Typically, this is accomplished via the use of the {@link java.sql.DriverManager} class.</p>
     */
    public Connection getConnection() {
        return null;
    }
}
