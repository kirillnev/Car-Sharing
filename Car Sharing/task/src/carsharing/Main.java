package carsharing;

import carsharing.dao.Company;
import carsharing.dao.CompanyDao;
import com.beust.jcommander.JCommander;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static carsharing.Config.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        Args arguments = new Args();
        Class.forName(JDBC_DRIVER);

        JCommander.newBuilder()
                .addObject(arguments)
                .build()
                .parse(args);

        String dbFileName = DB_FOLDER + Optional.ofNullable(arguments.getDbFileName()).orElseGet(() -> DEFAULT_DB_FILE_NAME);
        CompanyDao companyDao = new CompanyDao(dbFileName);

        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("1. Log in as a manager");
            System.out.println("0. Exit");
            int cmd = scanner.nextInt();
            if (cmd == 0 ){
                break;
            } else if (cmd == 1) {
                while(true) {
                    System.out.println("1. Company list");
                    System.out.println("2. Create a company");
                    System.out.println("0. Back");
                    cmd = scanner.nextInt();
                    if (cmd == 1) {
                        System.out.println("Company list:");
                        List<Company> companies = companyDao.getAll();
                        if (companies.size() == 0) {
                            System.out.println("The company list is empty!");
                        } else {
                            for (int i = 0; i < companies.size(); i++) {
                                System.out.println((i + 1) + ". " + companies.get(i).getName());
                            }
                        }
                    } else if (cmd == 2) {
                        System.out.println("Enter the company name:");
                        String name = scanner.nextLine();
                        name = scanner.nextLine();
                        companyDao.update(new Company(name));
                        System.out.println("The company was created!");
                    } else if (cmd == 0) break;
                }
            }
        }
    }
}