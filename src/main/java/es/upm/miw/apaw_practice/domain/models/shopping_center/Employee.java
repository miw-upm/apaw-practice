package es.upm.miw.apaw_practice.domain.models.shopping_center;

import java.time.LocalDate;

public class Employee {
    private String dni;
    private String name;
    private String phone;
    private LocalDate hiringDay;

    public Employee() {
        //empty for framework
    }

    public Employee(String dni, String name, String phone, LocalDate hiringDay) {
        this.dni = dni;
        this.name = name;
        this.phone = phone;
        this.hiringDay = hiringDay;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getHiringDay() {
        return hiringDay;
    }

    public void setHiringDay(LocalDate hiringDay) {
        this.hiringDay = hiringDay;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "dni='" + dni + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", hiringDay=" + hiringDay +
                '}';
    }
}
