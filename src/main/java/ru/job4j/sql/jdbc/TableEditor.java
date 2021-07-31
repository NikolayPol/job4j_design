package ru.job4j.sql.jdbc;

import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
        DatabaseMetaData metaData = connection.getMetaData();
        System.out.println(metaData.getUserName());
        System.out.println(metaData.getURL());
    }

    public void createStatement(Connection connection, String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        String sql = String.format(
                "create table if not exists %s (%s);",
                tableName,
                "id serial primary key"
        );
        createStatement(connection, sql);
        try {
            System.out.println(getTableScheme(connection, tableName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropTable(String tableName) {
        String sql = String.format(
                "drop table %s",
                tableName
        );
        createStatement(connection, sql);
        System.out.println("Table " + tableName + " drop succeessfully");
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format(
                "alter table %s "
                        + "add column %s %s",
                tableName,
                columnName,
                type
        );
        createStatement(connection, sql);
        System.out.println("Column " + columnName + " added succeessfully into " + tableName);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format(
                "alter table %s "
                        + "drop column \"%s\"",
                tableName,
                columnName
        );
        createStatement(connection, sql);
        System.out.println("Column " + columnName + " droped succeessfully into " + tableName);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format(
                "alter table %s "
                        + "rename column %s to \"%s\"",
                tableName,
                columnName,
                newColumnName
        );
        createStatement(connection, sql);
        System.out.println("Column " + columnName + " renamed succeessfully into " + tableName);
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        String pathToProperties =
                "src/main/java/ru/job4j/sql/jdbc/test6.properties";
        properties.load(new FileInputStream(pathToProperties));
        TableEditor tableEditor = new TableEditor(properties);
        String tableName = "mytable";
        tableEditor.dropTable(tableName);
        tableEditor.createTable(tableName);
        tableEditor.addColumn(tableName, "Столбец1", "int");
        tableEditor.renameColumn(tableName, "Столбец1", "Столбец2");
        tableEditor.dropColumn(tableName, "Столбец2");
    }
}
