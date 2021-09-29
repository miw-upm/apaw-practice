package es.upm.miw.apaw_practice.domain.models.university;

public class Student {

    private String name;
    private String lastName;
    private Boolean internationalStudent;

    public Student(){
        //empty for framework
    }

    public Student(String name, String lastName, Boolean internationalStudent) {
        this.name = name;
        this.lastName = lastName;
        this.internationalStudent = internationalStudent;
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

    public Boolean getInternationalStudent() {
        return internationalStudent;
    }

    public void setInternationalStudent(Boolean internationalStudent) {
        this.internationalStudent = internationalStudent;
    }
}
