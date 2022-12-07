package carsharing.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static carsharing.Config.*;

public class CustomerDao implements Dao<Customer, Integer> {

    private String dbFileName;

    public CustomerDao(String dbFileName) {
        this.dbFileName = dbFileName;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(dbFileName);
            // Execute a query
            statement = connection.createStatement();

            String sql =  "CREATE TABLE IF NOT EXISTS " + TBL_CUSTOMER_NAME +
                    " (ID INT NOT NULL AUTO_INCREMENT," +
                    " NAME VARCHAR(255) UNIQUE NOT NULL," +
                    " RENTED_CAR_ID INT," +
                    " CONSTRAINT FK_RENTED_CAR FOREIGN KEY (RENTED_CAR_ID)" +
                    " REFERENCES " + TBL_CAR_NAME + "(ID), "+
                    " PRIMARY KEY (ID));";

            statement.executeUpdate(sql);

            sql =  "ALTER TABLE " + TBL_CUSTOMER_NAME + " ALTER COLUMN id RESTART WITH 1;";
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
    public List<Customer> getAll() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Customer> customers = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(dbFileName);
            connection.setAutoCommit(true);
            // Execute a query
            statement = connection.createStatement();
            String sql =  String.format("SELECT * FROM %s;", TBL_CUSTOMER_NAME);
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                customers.add(new Customer(resultSet.getInt("ID"), resultSet.getString("NAME"), resultSet.getInt("RENTED_CAR_ID") == 0 ? null : resultSet.getInt("RENTED_CAR_ID")));
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
        return customers;
    }

    @Override
    public Customer get(Integer id) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Customer customer = null;
        try {
            connection = DriverManager.getConnection(dbFileName);
            connection.setAutoCommit(true);
            // Execute a query
            statement = connection.createStatement();
            String sql =  String.format("SELECT * FROM %s WHERE id=%d;", TBL_CUSTOMER_NAME, id);
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                customer = new Customer(resultSet.getInt("ID"), resultSet.getString("NAME"), resultSet.getInt("RENTED_CAR_ID") == 0 ? null : resultSet.getInt("RENTED_CAR_ID"));
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
        return customer;
    }

    @Override
    public void update(Customer consumer) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(dbFileName);
            connection.setAutoCommit(true);
            // Execute a query
            statement = connection.createStatement();
            String sql = null;
            if (consumer.getRentedCarId() == null) {
                sql =  String.format("UPDATE %s SET RENTED_CAR_ID=null WHERE ID=%d;",
                        TBL_CUSTOMER_NAME, consumer.getId());
            } else {
                sql =  String.format("UPDATE %s SET RENTED_CAR_ID=%d WHERE ID=%d;",
                        TBL_CUSTOMER_NAME, consumer.getRentedCarId(), consumer.getId());
            }
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
    public void add(Customer consumer) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(dbFileName);
            connection.setAutoCommit(true);
            // Execute a query
            statement = connection.createStatement();
            String sql = null;
            if (consumer.getRentedCarId() == null) {
                sql =  String.format("INSERT INTO %s (NAME) " +
                        "VALUES ('%s');", TBL_CUSTOMER_NAME, consumer.getName());
            }
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

}
