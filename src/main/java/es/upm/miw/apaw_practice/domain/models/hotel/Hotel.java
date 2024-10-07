package es.upm.miw.apaw_practice.domain.models.hotel;

public class Hotel {
    private String name;
    private String address;
    private String phone;

    public Hotel() {

    }

    public Hotel(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "name: " + name + '\'' +
                ", address: " + address + '\'' +
                ", phone:" + phone + '\'' +
                "}";
    }

}