package DAO;

import java.sql.*;

/**
 * Created by Super_hao on 2017/4/9.
 */
public class JDBCTools {
    public void testget() {

    }

    static Connection getConnection() {
        final String driver = "com.mysql.jdbc.Driver";
        final String dbUrl = "jdbc:mysql://localhost/test";
        final String user = "root";
        final String password = "szh960411";
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(dbUrl, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    static void releaseDB(ResultSet resultSet, Statement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
