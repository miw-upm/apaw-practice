package es.upm.miw.apaw_practice.domain.models.university;

public class Student {

    private String name;
    private String lastName;
    private Boolean isInternationalStudent;

    public Student(){
        //empty for framework
    }

    public Student(String name, String lastName, Boolean isInternationalStudent) {
        this.name = name;
        this.lastName = lastName;
        this.isInternationalStudent = isInternationalStudent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getIsInternationalStudent() {
        return isInternationalStudent;
    }

    public void setIsInternationalStudent(Boolean isInternationalStudent) {
        this.isInternationalStudent = isInternationalStudent;
    }
}
