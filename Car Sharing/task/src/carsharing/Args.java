package carsharing;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class Args {
    @Parameter(names = {"-databaseFileName"})
    String dbFileName;

    public String getDbFileName() {
        return dbFileName;
    }

}
