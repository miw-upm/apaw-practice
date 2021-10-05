package es.upm.miw.apaw_practice.adapters.mongodb.hospital.entities;

import es.upm.miw.apaw_practice.domain.models.hospital.Disease;
import es.upm.miw.apaw_practice.domain.models.hospital.Doctor;
import es.upm.miw.apaw_practice.domain.models.hospital.Patient;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;
import java.util.UUID;

public class PatientEntity {

    @Id
    private String id;
    private String dni;
    private String gender;
    private Integer age;
    @DBRef
    private List<DiseaseEntity> diseases;
    @DBRef
    private DoctorEntity doctor;

    public PatientEntity(){
        //empty for framework
    }

    public PatientEntity(String dni, String gender, Integer age, List<DiseaseEntity> diseases, DoctorEntity doctor) {
        this.id = UUID.randomUUID().toString();
        this.dni = dni;
        this.gender = gender;
        this.age = age;
        this.diseases = diseases;
        this.doctor = doctor;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<DiseaseEntity> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<DiseaseEntity> diseases) {
        this.diseases = diseases;
    }

    public DoctorEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorEntity doctor) {
        this.doctor = doctor;
    }


}
