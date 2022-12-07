package carsharing.dao;

public class Company {
    private  int id;
    private String name;

    public Company(String name) {
        this.name = name;
    }
    public Company(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
