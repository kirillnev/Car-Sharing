package carsharing.dao;

public class Car {
    private  int id;
    private  int company_id;
    private String name;

    public Car(String name, int company_id) {
        this.name = name;
        this.company_id = company_id;
    }

    public int getId() {
        return id;
    }
    public int getCompanyId() {
        return company_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
