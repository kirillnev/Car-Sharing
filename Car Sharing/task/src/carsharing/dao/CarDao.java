package carsharing.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static carsharing.Config.TBL_CAR_NAME;
import static carsharing.Config.TBL_COMPANY_NAME;

public class CarDao implements Dao<Car, Integer> {

    private String dbFileName;

    public CarDao(String dbFileName) {
        this.dbFileName = dbFileName;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(dbFileName);
            // Execute a query
            statement = connection.createStatement();

            String sql =  "CREATE TABLE IF NOT EXISTS " + TBL_CAR_NAME +
                    " (ID INT NOT NULL AUTO_INCREMENT," +
                    " NAME VARCHAR(255) UNIQUE NOT NULL," +
                    " COMPANY_ID INT NOT NULL," +
                    " CONSTRAINT FK_COMPANY FOREIGN KEY (COMPANY_ID)" +
                    " REFERENCES " + TBL_COMPANY_NAME + "(ID), "+
                    " PRIMARY KEY (ID));";

            statement.executeUpdate(sql);

            sql =  "ALTER TABLE " + TBL_CAR_NAME + " ALTER COLUMN id RESTART WITH 1;";
            statement.executeUpdate(sql);

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

    public List<Car> getAllByCompany(int company_id) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Car> cars = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(dbFileName);
            connection.setAutoCommit(true);
            // Execute a query
            statement = connection.createStatement();
            String sql =  String.format("SELECT * FROM %s WHERE company_id=%d;", TBL_CAR_NAME, company_id);
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                cars.add(new Car(resultSet.getInt("ID"), resultSet.getString("NAME"), resultSet.getInt("COMPANY_ID")));
            }
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
        return cars;
    }

    @Override
    public List<Car> getAll() {
        return null;
    }

    @Override
    public Car get(Integer id) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Car car = null;
        try {
            connection = DriverManager.getConnection(dbFileName);
            connection.setAutoCommit(true);
            // Execute a query
            statement = connection.createStatement();
            String sql =  String.format("SELECT * FROM %s WHERE id=%d;", TBL_CAR_NAME, id);
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                car = new Car(resultSet.getInt("ID"), resultSet.getString("NAME"), resultSet.getInt("COMPANY_ID"));
            }
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
        return car;
    }

    @Override
    public void add(Car car) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(dbFileName);
            connection.setAutoCommit(true);
            // Execute a query
            statement = connection.createStatement();
            String sql =  String.format("INSERT INTO %s (NAME, COMPANY_ID) " +
                    "VALUES ('%s', %d);", TBL_CAR_NAME, car.getName(), car.getCompanyId());
            statement.executeUpdate(sql);

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

    @Override
    public void update(Car car) {

    }

}
