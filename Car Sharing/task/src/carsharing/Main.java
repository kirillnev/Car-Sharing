package carsharing;

import com.beust.jcommander.JCommander;

import java.util.Optional;

public class Main {

    static final String DEFAULT_DB_FILE_NAME = "db";

    public static void main(String[] args) {
        Args arguments = new Args();

        JCommander.newBuilder()
                .addObject(arguments)
                .build()
                .parse(args);

        Optional<String> dbFileName = Optional.ofNullable(arguments.getDbFileName());
        DBManager dbManager = new DBManager(dbFileName.orElseGet( () -> DEFAULT_DB_FILE_NAME ));
        dbManager.connect();
        String sql = "CREATE TABLE COMPANY " +
                "(id INTEGER not NULL, " +
                " NAME VARCHAR(255), " +
                " PRIMARY KEY ( id ))";
        dbManager.executeUpdate(sql);
        dbManager.close();

    }

}