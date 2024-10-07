package es.upm.miw.apaw_practice.domain.models.veterinay_clinic;

import java.util.List;

public class Employee {

    private String name;
    private boolean isDoctor;
    private List<Animal> animals;

    public Employee() {
        //empty from framework
    }

    public Employee(String name, boolean isDoctor, List<Animal> animals) {
        this.name = name;
        this.isDoctor = isDoctor;
        this.animals = animals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDoctor() {
        return isDoctor;
    }

    public void setDoctor(boolean doctor) {
        isDoctor = doctor;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public static Employee ofName(Employee employee) {
        Employee employeeDto = new Employee();
        employeeDto.setName(employee.getName());
        return employeeDto;
    }

    @Override
    public String toString() {
        return "Employee{" +
                ", name='" + name + '\'' +
                ", isDoctor=" + isDoctor +
                ", animals=" + animals +
                '}';
    }
}