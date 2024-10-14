package es.upm.miw.apaw_practice.domain.models.veterinay_clinic;

import java.util.Objects;

public class OwnerClinic {

    private String name;
    private String address;
    private String phone;

    public OwnerClinic(){
        //empty from framework
    }

    public OwnerClinic(String name, String address, String phone) {
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
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        OwnerClinic ownerClinic = (OwnerClinic) object;
        return name.equals(ownerClinic.name) && address.equals(ownerClinic.address) && phone.equals(ownerClinic.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, phone);
    }

    @Override
    public String toString() {
        return "OwnerClinic{" +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}