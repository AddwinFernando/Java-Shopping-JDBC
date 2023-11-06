package org.example.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static Connection connection = null;

    private static String jdbcURL = "jdbc:mysql://localhost:3306/e_commerce_jdbc";
    private static String jdbcUserName = "root";
    private static String jdbcPassword = "root";

    public static Connection getConection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL,jdbcUserName,jdbcPassword);
            System.out.println("connected -> " + !connection.isClosed());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

}
