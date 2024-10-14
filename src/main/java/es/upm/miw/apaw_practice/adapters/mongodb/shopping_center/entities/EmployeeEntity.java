package es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.entities;

import es.upm.miw.apaw_practice.domain.models.shopping_center.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document
public class EmployeeEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String dni;
    private String name;
    private String phone;
    private LocalDate hiringDay;

    public EmployeeEntity() {
        //empty from framework
    }

    public EmployeeEntity(Employee employee) {
        BeanUtils.copyProperties(employee, this);
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
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

    public Employee toEmployee() {
        Employee employee = new Employee();
        BeanUtils.copyProperties(this, employee);
        return employee;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "id='" + id + '\'' +
                ", dni='" + dni + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", hiringDay=" + hiringDay +
                '}';
    }
}
