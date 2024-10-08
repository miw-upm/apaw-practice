package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;
import es.upm.miw.apaw_practice.domain.models.Hospital;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class DoctorEntity {
    @Id
    private String id;

    @Indexed(unique = true)
    private String dni;

    private String fullname;
    private Double salary;

    public DoctorEntity(String dni, String fullname, Double salary) {
        this.id = UUID.randomUUID().toString();
        this.dni = dni;
        this.fullname = fullname;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public int hashCode() {
        return this.dni.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || (obj != null && getClass() == obj.getClass() && dni.equals(((DoctorEntity) obj).dni));
    }

    @Override
    public String toString() {
        return "DoctorEntity{" +
                "id='" + id + '\'' +
                ", dni='" + dni + '\'' +
                ", fullname='" + fullname + '\'' +
                ", salary=" + salary +
                '}'; 
    }
}
