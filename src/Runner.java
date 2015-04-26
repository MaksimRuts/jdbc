import by.gsu.epamlab.connectors.BaseConnector;
import by.gsu.epamlab.entity.Line;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Runner {
    private static final String QUERY_GET_LINES = "select ABS(ROUND(x1 +  x2)) as len, COUNT(*) as num from coordinates GROUP BY len order by num DESC ;";
    private static final String QUERY_CLEAR_FREQ = "delete from frequencies where id >= 0;";
    private static final String QUERY_SELECT_FROM_FREQ = "select * from frequencies where len > num";
    private static final String QUERY_INSERT_TO_FREQ = "insert into frequencies (len, num) values ";

    public static void main(String[] args) {
        Connection connection = BaseConnector.getConnection();
        List<Line> lines = new ArrayList<Line>();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(QUERY_GET_LINES);
                while (resultSet.next()) {
                    lines.add(new Line(resultSet.getInt("len"),
                            resultSet.getInt("num")));
                }

                statement.executeUpdate(QUERY_CLEAR_FREQ);
                StringBuilder builder = new StringBuilder(QUERY_INSERT_TO_FREQ);
                for (Line line : lines) {
                    builder.replace(QUERY_INSERT_TO_FREQ.length(), builder.toString().length(), line.getStringToQuery());
                    statement.executeUpdate(builder.toString());
                    System.out.println(line);
                }

                System.out.println();

                resultSet = statement.executeQuery(QUERY_SELECT_FROM_FREQ);
                while (resultSet.next()) {
                    System.out.println(new Line(resultSet.getInt("len"),
                            resultSet.getInt("num")));
                }

                resultSet.close();
                statement.close();
            } finally {
                if (resultSet != null && !resultSet.isClosed()) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                BaseConnector.closeConnections();
            }
        } catch (SQLException e) {
            System.err.println("Database processing error.");
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("Connection access error. Check driver setting.");
        }
    }
}