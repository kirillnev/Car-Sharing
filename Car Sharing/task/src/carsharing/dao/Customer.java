package carsharing.dao;

public class Customer {
    private  int id;
    private  Integer rented_car_id;
    private String name;

    public Customer(String name, Integer rented_car_id) {
        this.name = name;
        this.rented_car_id = rented_car_id;
    }

    public Customer(int id, String name, Integer rented_car_id) {
        this.id = id;
        this.name = name;
        this.rented_car_id = rented_car_id;
    }
    public int getId() {
        return id;
    }
    public Integer getRentedCarId() {
        return rented_car_id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", rented_car_id=" + rented_car_id +
                ", name='" + name + '\'' +
                '}';
    }
}
