package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
public class DoctorEntity {

    @Id
    private String dni;
    private String fullname;
    private BigDecimal salary;

    // Getters, Setters, Constructor, equals, hashCode, toString...


    public DoctorEntity() {
    }

    public DoctorEntity(String dni, String fullname, BigDecimal salary) {
        this.dni = dni;
        this.fullname = fullname;
        this.salary = salary;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof DoctorEntity)) return false;
        if (!super.equals(object)) return false;
        DoctorEntity that = (DoctorEntity) object;
        return java.util.Objects.equals(getDni(), that.getDni()) && java.util.Objects.equals(getFullname(), that.getFullname()) && java.util.Objects.equals(getSalary(), that.getSalary());
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), getDni(), getFullname(), getSalary());
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "DoctorEntity{" +
                "dni='" + dni + '\'' +
                ", fullname='" + fullname + '\'' +
                ", salary=" + salary +
                '}';
    }
}
