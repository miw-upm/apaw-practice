package es.upm.miw.apaw_practice.domain.models.veterinay_clinic;

import java.util.List;
import java.util.Objects;

public class Clinic {

    private String id;
    private String name;
    private String address;
    private Employee employee;

    public Clinic() {
        //empty from framework
    }

    public Clinic(String id, String name, String address, Employee employee) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.employee = employee;
    }

    public void doDefault() {
        if(Objects.isNull(name)) {
            this.name = "clinic";
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Clinic{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", employee=" + employee +
                '}';
    }
}