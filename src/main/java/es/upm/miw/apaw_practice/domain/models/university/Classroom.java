package es.upm.miw.apaw_practice.domain.models.university;

public class Classroom {

    private Integer number;
    private String school;
    private Integer capacity;

    public Classroom() {
        //empty for framework
    }

    public Classroom(Integer number, String school, Integer capacity) {
        this.number = number;
        this.school = school;
        this.capacity = capacity;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
