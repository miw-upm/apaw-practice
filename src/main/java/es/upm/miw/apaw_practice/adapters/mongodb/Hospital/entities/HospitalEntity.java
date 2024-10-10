package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import es.upm.miw.apaw_practice.domain.models.Hospital.Hospital;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.DoctorEntity;

import java.util.List;
import java.util.UUID;

@Document
public class HospitalEntity {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private String location;
    private Integer capacity;
    private List<DoctorEntity> doctors;

    public HospitalEntity() {
        // Empty constructor for framework
    }


    public HospitalEntity(Hospital hospital) {
        BeanUtils.copyProperties(hospital, this);
        this.id = UUID.randomUUID().toString();
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public List<DoctorEntity> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<DoctorEntity> doctors) {
        this.doctors = doctors;
    }

    // Métodos de conversión
    public void fromHospital(Hospital hospital) {
        BeanUtils.copyProperties(hospital, this);
    }

    public Hospital toHospital() {
        Hospital hospital = new Hospital();
        BeanUtils.copyProperties(this, hospital);
        return hospital;
    }

    @Override
    public String toString() {
        return "HospitalEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
