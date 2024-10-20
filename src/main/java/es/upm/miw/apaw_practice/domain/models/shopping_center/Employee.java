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

    public Employee(String dni, String name, String phone) {
        this.dni = dni;
        this.name = name;
        this.phone = phone;
    }

    public static EmployeeBuilders.Dni builder() {
        return new Builder();
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

    public static class Builder implements EmployeeBuilders.Dni, EmployeeBuilders.Name, EmployeeBuilders.Optionals {

        private final Employee employee;

        public Builder() {
            this.employee = new Employee();
        }

        @Override
        public EmployeeBuilders.Name dni(String dni) {
            this.employee.dni = dni;
            return this;
        }

        @Override
        public EmployeeBuilders.Optionals name(String name) {
            this.employee.name = name;
            return this;
        }

        @Override
        public EmployeeBuilders.Optionals phone(String phone) {
            this.employee.phone = phone;
            return this;
        }

        @Override
        public Employee build() {
            return this.employee;
        }
    }
}
