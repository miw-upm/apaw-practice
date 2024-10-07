package es.upm.miw.apaw_practice.domain.models.veterinay_clinic;

import java.util.Objects;

public class Owner {

    private String name;
    private String address;
    private String phone;

    public Owner(){
        //empty from framework
    }

    public Owner(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void doDefault() {
        if(Objects.isNull(name)){
            this.name = "name";
        }
    }

    @Override
    public String toString() {
        return "Owner{" +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}