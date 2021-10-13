package es.upm.miw.apaw_practice.domain.models.university;

public class Student {

    private String dni;
    private String fullName;
    private Boolean internationalStudent;

    public Student(){
        //empty for framework
    }

    public Student(String dni, String fullName, Boolean internationalStudent) {
        this.dni = dni;
        this.fullName = fullName;
        this.internationalStudent = internationalStudent;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getInternationalStudent() {
        return internationalStudent;
    }

    public void setInternationalStudent(Boolean internationalStudent) {
        this.internationalStudent = internationalStudent;
    }
}
