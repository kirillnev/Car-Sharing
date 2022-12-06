package carsharing;

import carsharing.dao.Company;
import carsharing.dao.CompanyDao;
import carsharing.dao.Car;
import carsharing.dao.CarDao;
import com.beust.jcommander.JCommander;
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
        CarDao carDao = new CarDao(dbFileName);

        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("1. Log in as a manager");
            System.out.println("0. Exit");
            int cmd = scanner.nextInt();
            System.out.println();

            if (cmd == 0 ){
                break;
            } else if (cmd == 1) {
                while(true) {
                    System.out.println("1. Company list");
                    System.out.println("2. Create a company");
                    System.out.println("0. Back");
                    cmd = scanner.nextInt();
                    System.out.println();

                    if (cmd == 1) {
                        cm:
                        while (true) {
                            System.out.println("Choose a company:");
                            List<Company> companies = companyDao.getAll();
                            if (companies.size() == 0) {
                                System.out.println("The company list is empty!");
                                break;
                            } else {
                                for (int i = 0; i < companies.size(); i++) {
                                    System.out.println((i + 1) + ". " + companies.get(i).getName());
                                }
                                System.out.println("0. Back");

                                int company_id = scanner.nextInt();
                                if (company_id == 0) {
                                    break;
                                } else if (company_id <= 0 || company_id > companies.size()){
                                    System.out.println("Incorrect company");
                                    System.out.println();
                                    continue;
                                }
                                System.out.println();

                                while (true) {
                                    System.out.println(companies.get(company_id - 1).getName() + " company:");
                                    System.out.println("1. Car list");
                                    System.out.println("2. Create a car");
                                    System.out.println("0. Back");
                                    cmd = scanner.nextInt();
                                    System.out.println();

                                    if (cmd == 1) {
                                        List<Car> cars = carDao.getAllById(company_id);
                                        if (cars.size() == 0) {
                                            System.out.println("The car list is empty!");
                                        } else {
                                            System.out.println("Car list:");
                                            for (int i = 0; i < cars.size(); i++) {
                                                System.out.println((i + 1) + ". " + cars.get(i).getName());
                                            }
                                        }
                                    } else if (cmd == 2) {
                                        System.out.println("Enter the car name:");
                                        String name = scanner.nextLine();
                                        name = scanner.nextLine();
                                        carDao.update(new Car(name, company_id));
                                        System.out.println("The car was added!");
                                    } else if (cmd == 0) break cm;
                                    System.out.println();
                                }
                            }
                        }
                    } else if (cmd == 2) {
                        System.out.println("Enter the company name:");
                        String name = scanner.nextLine();
                        name = scanner.nextLine();
                        companyDao.update(new Company(name));
                        System.out.println("The company was created!");
                        System.out.println();
                    } else if (cmd == 0) break;
                }
            }
        }
    }
}