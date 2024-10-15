package es.upm.miw.apaw_practice.domain.models.veterinay_clinic;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Clinic {

    private String name;
    private String address;
    private List<Employee> employees;

    public Clinic() {
        //empty from framework
    }

    public Clinic(String name, String address, List<Employee> employees) {
        this.name = name;
        this.address = address;
        this.employees = employees;
    }

    public void doDefault() {
        if(Objects.isNull(name)) {
            this.name = "clinic";
        }
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public static Clinic ofEmployeeName(Clinic clinic) {
        clinic.setEmployees(
                clinic.employees.stream().map(Employee::ofAnimalName)
                        .collect(Collectors.toList())
        );
        return clinic;
    }

    @Override
    public String toString() {
        return "Clinic{" +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", employees=" + employees.stream().map(Employee::getName).collect(Collectors.toList()) +
                '}';
    }
}