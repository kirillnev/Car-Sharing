package carsharing;

import carsharing.dao.*;
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
        CustomerDao customerDao = new CustomerDao(dbFileName);

        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("1. Log in as a manager");
            System.out.println("2. Log in as a customer");
            System.out.println("3. Create a customer");
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
                                        List<Car> cars = carDao.getAllByCompany(company_id);
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
                                        carDao.add(new Car(name, company_id));
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
                        companyDao.add(new Company(name));
                        System.out.println("The company was created!");
                        System.out.println();
                    } else if (cmd == 0) break;
                }
            } else if (cmd == 2) {
                cm2:
                while (true) {
                    System.out.println("Choose a customer:");
                    List<Customer> customers = customerDao.getAll();
                    if (customers.size() == 0) {
                        System.out.println("The customer list is empty!");
                        break;
                    } else {
                        for (int i = 0; i < customers.size(); i++) {
                            System.out.println((i + 1) + ". " + customers.get(i).getName());
                        }
                        System.out.println("0. Back");

                        int customer_id = scanner.nextInt();
                        if (customer_id == 0) {
                            break;
                        } else if (customer_id <= 0 || customer_id > customers.size()){
                            System.out.println("Incorrect consumer");
                            System.out.println();
                            continue;
                        }
                        System.out.println();

                        while (true) {
                            Customer currentCustomer = customerDao.get(customer_id);
                            System.out.println(currentCustomer.getName() + " customer:");
                            System.out.println("1. Rent a car");
                            System.out.println("2. Return a rented car");
                            System.out.println("3. My rented car");
                            System.out.println("0. Back");
                            cmd = scanner.nextInt();
                            System.out.println();

                            if (cmd == 3) {
                                if (currentCustomer.getRentedCarId() == null) {
                                    System.out.println("You didn't rent a car!");
                                } else {
                                    Car rentedCar = carDao.get(currentCustomer.getRentedCarId());
                                    System.out.println("Your rented car:");
                                    System.out.println(rentedCar.getName());
                                    //System.out.println(rentedCar);

                                    System.out.println("Company:");
                                    Company company = companyDao.get(rentedCar.getCompanyId());
                                    System.out.println(company.getName());
                                }
                            } else if (cmd == 2) {
                                if (currentCustomer.getRentedCarId() == null) {
                                    System.out.println("You didn't rent a car!");
                                } else {
                                    customerDao.update(new Customer(currentCustomer.getId(), currentCustomer.getName(), null));
                                    System.out.println("You've returned a rented car!");
                                }
                            } else if (cmd == 1) {
                                if (currentCustomer.getRentedCarId() != null) {
                                    System.out.println("You've already rented a car!");
                                } else {
                                    cm3:
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
                                            } else if (company_id <= 0 || company_id > companies.size()) {
                                                System.out.println("Incorrect company");
                                                System.out.println();
                                                continue;
                                            }
                                            System.out.println();

                                            while (true) {
                                                System.out.println("Choose a car:");
                                                List<Car> cars = carDao.getAllByCompany(company_id);
                                                List<Customer> customers1 = customerDao.getAll();

                                                for (int i = 0; i < customers1.size(); i++) {
                                                    for (int j = 0; j < cars.size(); j++) {
                                                        if (customers1.get(i).getRentedCarId() != null && cars.get(j).getId() == customers1.get(i).getRentedCarId()) {
                                                            cars.remove(j);
                                                        }
                                                    }
                                                }
                                                if (cars.size() == 0) {
                                                    System.out.println("The car list is empty!");
                                                } else {
                                                    for (int i = 0; i < cars.size(); i++) {
                                                        System.out.println((i + 1) + ". " + cars.get(i).getName());
                                                    }
                                                }
                                                System.out.println("0. Back");
                                                int selectedCar = scanner.nextInt();
                                                System.out.println();
                                                if (selectedCar <= 0 || selectedCar > cars.size()) {
                                                    System.out.println("Incorrect car");
                                                    System.out.println();
                                                    continue;
                                                } else if (selectedCar == 0) {
                                                    break cm3;
                                                } else {
                                                    customerDao.update(new Customer(currentCustomer.getId(), currentCustomer.getName(), cars.get(selectedCar - 1).getId()));
                                                    System.out.println("You rented '" + cars.get(selectedCar - 1).getName() + "'");
                                                    break cm3;
                                                }
                                            }
                                        }
                                    }
                                }
                            } else if (cmd == 0) break cm2;
                            System.out.println();
                        }
                    }
                }
                
            } else if (cmd == 3) {
                System.out.println("Enter the customer name:");
                String name = scanner.nextLine();
                name = scanner.nextLine();
                customerDao.add(new Customer(name, null));
                System.out.println("The customer was added!");
            }
        }
    }
}