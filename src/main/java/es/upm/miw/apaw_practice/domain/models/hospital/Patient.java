package es.upm.miw.apaw_practice.domain.models.hospital;

import java.util.List;

public class Patient {

    private Integer dni;
    private String gender;
    private Integer age;
    private List<Disease> diseases;
    private Doctor doctor;

    Patient(){
        //empty for framework
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
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

    public List<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<Disease> diseases) {
        this.diseases = diseases;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "dni=" + this.dni +
                ", gender='" + this.gender + '\'' +
                ", age=" + this.age +
                '}';
    }
}
