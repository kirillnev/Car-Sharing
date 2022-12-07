package carsharing.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static carsharing.Config.*;

public class CompanyDao implements Dao<Company, Integer> {

    private String dbFileName;

    public CompanyDao(String dbFileName) {
        this.dbFileName = dbFileName;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(dbFileName);
            // Execute a query
            statement = connection.createStatement();
            String sql =  "CREATE TABLE IF NOT EXISTS " + TBL_COMPANY_NAME +
                    " (ID INT NOT NULL AUTO_INCREMENT," +
                    " NAME VARCHAR(255) UNIQUE NOT NULL," +
                    " PRIMARY KEY (ID));";
            statement.executeUpdate(sql);

            sql =  "ALTER TABLE " + TBL_COMPANY_NAME + " ALTER COLUMN id RESTART WITH 1;";
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
    public List<Company> getAll() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Company> companies = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(dbFileName);
            connection.setAutoCommit(true);
            // Execute a query
            statement = connection.createStatement();
            String sql =  String.format("SELECT * FROM %s;", TBL_COMPANY_NAME);
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                companies.add(new Company(resultSet.getInt("ID"), resultSet.getString("NAME")));
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
        return companies;
    }

    @Override
    public Company get(Integer id) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Company company = null;
        try {
            connection = DriverManager.getConnection(dbFileName);
            connection.setAutoCommit(true);
            // Execute a query
            statement = connection.createStatement();
            String sql =  String.format("SELECT * FROM %s WHERE id=%d;", TBL_COMPANY_NAME, id);
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                company = new Company(resultSet.getInt("ID"), resultSet.getString("NAME"));
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
        return company;
    }

    @Override
    public void add(Company company) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(dbFileName);
            connection.setAutoCommit(true);
            // Execute a query
            statement = connection.createStatement();
            String sql =  String.format("INSERT INTO %s (NAME) " +
                    "VALUES ('%s');", TBL_COMPANY_NAME, company.getName());
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
    public void update(Company company) {

    }

}
