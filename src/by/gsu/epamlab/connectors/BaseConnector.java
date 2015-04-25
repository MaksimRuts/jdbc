package by.gsu.epamlab.connectors;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseConnector {
    private static final String DRIVER_URL = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "\"jdbc:mysql://localhost/segments";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static final Connection connection = null;
    private static final Statement statement = null;

    private BaseConnector(){

    }

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            System.err.println("Driver loading error!!");
        } catch (SQLException e) {
            System.err.println("Error connect to database " + DATABASE_URL);
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static Statement getStatement() {
        return statement;
    }
}
