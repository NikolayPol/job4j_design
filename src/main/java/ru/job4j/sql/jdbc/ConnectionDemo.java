package ru.job4j.sql.jdbc;

import org.postgresql.Driver;

import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        String pathToProperties =
                "src/main/java/ru/job4j/sql/jdbc/app.properties";
        try {
            properties.load(new FileInputStream(pathToProperties));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Class.forName("org.postgresql.Driver");
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}