package com.revature.util;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static Connection connection;

    private ConnectionFactory() {}

    public static Connection getConnection() throws SQLException, IOException {
        if(connection == null) {
            connection = connect();
        }
        return connection;
    }

    public static void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection = null;
    }

    private static Connection connect() {
        try {
            Class.forName("org.postgresql.Driver");
            Properties props = new Properties();

            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream input = loader.getResourceAsStream("application.properties");
            props.load(input);

            String connectionString = "jdbc:postgresql://" +
                    props.getProperty("hostname") + ":" +
                    props.getProperty("port") + "/" +
                    props.getProperty("dbname");

            String username = props.getProperty("username");
            String password = props.getProperty("password");

            connection = DriverManager.getConnection(connectionString, username, password);
        }
        catch(IOException | SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }

        return connection;
    }
}
