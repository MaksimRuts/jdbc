package by.gsu.epamlab.utils;

import by.gsu.epamlab.connectors.BaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class FillCoordinatesByRandom {
    private static final int LINES_COUNT = 1000;
    private static final String QUERY_CLEAR_TABLE = "drop table if EXISTS coordinates;";
    private static final String QUERY_CREATE_TABLE = "CREATE TABLE coordinates(" +
            "    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT," +
            "    x1 DOUBLE NOT NULL," +
            "    x2 DOUBLE NOT NULL" +
            ");";

    public static void main(String[] args) {
        long timeout;
        int linesCount;
        Connection connection = BaseConnector.getConnection();
        Scanner in = new Scanner(System.in);

        System.out.println("Lines generator for lab jdbc started.");
        System.out.print("Enter count of lines for generation: ");
        linesCount = Integer.parseInt(in.nextLine());

        System.out.println("Start filling...");
        timeout = System.currentTimeMillis();

        try {
            fillCoordinates(connection, linesCount);

            timeout = System.currentTimeMillis() - timeout;
            System.out.println("Filling complete with time " + timeout + "ms");
        } catch (SQLException e) {
            System.err.println("Filling error");
        }
    }

    public static double getRandomDouble() {
        return (Math.random() - Math.random()) * 10;
    }

    public static String getQueryFromNumbers(double d1, double d2) {
        final String QUERY_INSERT = "insert INTO coordinates (x1, x2) values ";
        StringBuilder builder = new StringBuilder(QUERY_INSERT);
        builder.append("('")
                .append(d1)
                .append("', '")
                .append(d2)
                .append("');");
        return builder.toString();
    }

    public static void fillCoordinates(Connection connection, int numbersCount) throws SQLException {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(QUERY_CLEAR_TABLE);
            statement.executeUpdate(QUERY_CREATE_TABLE);
            String query;
            for (int i = 0; i < numbersCount; i++) {
                query = getQueryFromNumbers(getRandomDouble(), getRandomDouble());
                statement.executeUpdate(query);
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
}