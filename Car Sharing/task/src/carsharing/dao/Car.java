package carsharing.dao;

public class Car {
    private  int id;
    private  int company_id;
    private String name;

    public Car(int id, String name, int company_id) {
        this.id = id;
        this.name = name;
        this.company_id = company_id;
    }
    public Car(String name, int company_id) {
        this.name = name;
        this.company_id = company_id;
    }

    public int getCompanyId() {
        return company_id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", company_id=" + company_id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }
}
