package carsharing;

import carsharing.dao.Car;

import java.sql.*;
import java.util.Optional;

import static carsharing.Config.*;

public class Test {
/*
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);

        String dbFileName = DB_FOLDER + DEFAULT_DB_FILE_NAME;

        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(dbFileName);
            // Execute a query
            statement = connection.createStatement();



            String sql =  "SELECT * FROM CAR;";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.printf("ID: %d\nNAME: %s\nCOMPANY_ID: %d\n\n", resultSet.getInt("ID"), resultSet.getString("NAME"), resultSet.getInt("COMPANY_ID"));
            }

            sql =  "SELECT * FROM CUSTOMER;";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.printf("ID: %d\nNAME: %s\nRENTED_CAR_ID: %d\n\n", resultSet.getInt("ID"), resultSet.getString("NAME"), resultSet.getInt("RENTED_CAR_ID"));
            }

            sql =  "SELECT * FROM COMPANY;";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.printf("ID: %d\nNAME: %s\nRENTED_CAR_ID: %d\n\n", resultSet.getInt("ID"), resultSet.getString("NAME"), resultSet.getInt("RENTED_CAR_ID"));
            }

//            sql =  "INSERT INTO CAR (NAME, COMPANY_ID) VALUES ('car4', 1);";
//            statement.executeUpdate(sql);

//            sql =  "DELETE FROM CAR WHERE ID = 5;";
//            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            //Clean-up environment
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
*/
}
