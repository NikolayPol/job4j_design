package ru.job4j.sql.jdbc;

import org.postgresql.Driver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "";
        String login = "";
        String password = "";
        try (BufferedReader in = new BufferedReader(new FileReader(
                "src/main/java/ru/job4j/sql/jdbc/app.properties"))){
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                if (line.contains("url")) {
                    url = line.split("=")[1];
                }
                if (line.contains("login")) {
                    login = line.split("=")[1];
                }
                if (line.contains("password")) {
                    password = line.split("=")[1];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}