package carsharing;

import javax.sql.RowSet;
import java.sql.*;

import static carsharing.Config.*;


public class DBManager {

    private Connection connection = null;
    private String dbFileName;

    public DBManager (String dbFileName) {
        this.dbFileName = dbFileName;
    }

    void connect() {

        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            // Open a connection
            System.out.println("Connecting to database " + DB_FOLDER + dbFileName + "...");
            connection = DriverManager.getConnection(DB_FOLDER + dbFileName);
            //connection.setAutoCommit(true);
        } catch(SQLException e) {
            //Handle errors for JDBC
            e.printStackTrace();
        }
    }

    void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } //end finally try
        System.out.println("Goodbye!");

    }
}
