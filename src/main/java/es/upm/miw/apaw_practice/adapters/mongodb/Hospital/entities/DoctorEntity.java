package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import es.upm.miw.apaw_practice.domain.models.Hospital.Doctor;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.UUID;

public class DoctorEntity {

    private String id;
    private String name;
    private BigDecimal salary;
    private String hospitalId;


    public DoctorEntity() {

    }


    public DoctorEntity(Doctor doctor) {
        BeanUtils.copyProperties(doctor, this);
        if (doctor.getId() == null) {
            this.id = UUID.randomUUID().toString();
        }
    }


    public DoctorEntity(String id, String name, BigDecimal salary, String hospitalId) {
        this.id = id == null ? UUID.randomUUID().toString() : id;
        this.name = name;
        this.salary = salary;
        this.hospitalId = hospitalId;
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

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }



    public void fromDoctor(Doctor doctor) {
        BeanUtils.copyProperties(doctor, this);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || (obj != null && getClass() == obj.getClass() && id.equals(((DoctorEntity) obj).id));
    }
    public Doctor toDoctor() {
        return new Doctor(this.id, this.name, this.salary);
    }

    @Override
    public String toString() {
        return "DoctorEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", hospitalId='" + hospitalId + '\'' +
                '}';
    }
}
