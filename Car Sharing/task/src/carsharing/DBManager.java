package carsharing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DBManager {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_FOLDER = "jdbc:h2:./src/carsharing/db/";

    private Connection connection = null;
    private String dbFileName;

    public DBManager (String dbFileName) {
        this.dbFileName = dbFileName;
    }

    void connect() {
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            System.out.println("Connecting to database " + DB_FOLDER + dbFileName + "...");
            connection = DriverManager.getConnection(DB_FOLDER + dbFileName);
            connection.setAutoCommit(true);
        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            try {
                if(connection!=null) connection.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        }
        System.out.println("Goodbye!");

    }

    void executeUpdate(String sql){
        Statement statement = null;
        try {
            // Execute a query
            System.out.println("Executing query in given database...");
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Created table in given database...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            if(statement!=null) statement.close();
        } catch(SQLException se2) {
        } // nothing we can do

    }
}
