package es.upm.miw.apaw_practice.domain.models.university;

public class Classroom {

    private String school;
    private Integer number;
    private Integer capacity;

    public Classroom() {
        //empty for framework
    }

    public Classroom(String school, Integer number, Integer capacity) {
        this.school = school;
        this.number = number;
        this.capacity = capacity;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
