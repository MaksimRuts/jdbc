package by.gsu.epamlab.connectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class BaseConnector {
    private static final String DRIVER_URL = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost/segments";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection connection;

    private BaseConnector(){

    }

    static {
        try {
            Class.forName(DRIVER_URL);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver loading error!!");
        }
    }

    public static Connection getConnection() {
        if (connection == null)
            try {
                connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            } catch (SQLException e) {
                System.err.println("Database connecting error (" + DATABASE_URL + ")");
            }
        return connection;
    }

    public static void closeConnections() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                System.err.println("Connection closing error");
            }
        }
    }
}
