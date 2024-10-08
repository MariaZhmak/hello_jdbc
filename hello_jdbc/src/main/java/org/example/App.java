package org.example;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        String dbUrl = "jdbc:postgresql://localhost:5432/mydatabase";
        String dbUser = "postgres";
        String dbPassword = "Moremore";

        String createTable = "CREATE TABLE IF NOT EXISTS hello_jdbc (id INT PRIMARY KEY, message VARCHAR(255))";
        String insertDate = "INSERT INTO hello_jdbc (id, message) VALUES (1, 'Hello JDBC!'),\n" + "(2, 'Super Homework!')";
        String selectQuery = "SELECT * FROM hello_jdbc";

        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL(dbUrl);
        dataSource.setUser(dbUser);
        dataSource.setPassword(dbPassword);


        try {
//            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

            statement.execute(createTable);
            statement.execute(insertDate);

            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String message = resultSet.getString("message");
                System.out.println("ID: " + id + " message: " + message);
            }
            resultSet.close();
            statement.close();
            connection.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
