package es.upm.miw.apaw_practice.domain.models.emarketer;

public class Customer {

    private String name;
    private String address;
    private String type;

    public Customer() {
        //empty for framework
    }

    public Customer(String name, String address, String type) {
        this.name = name;
        this.address = address;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name=" + name +
                ", address" + address +
                ", type=" + type +
                '}';
    }

}
