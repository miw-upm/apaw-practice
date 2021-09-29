package es.upm.miw.apaw_practice.domain.models.university;

public class Classroom {

    private Integer code;
    private String school;
    private Integer capacity;

    public Classroom() {
        //empty for framework
    }

    public Classroom(Integer code, String school, Integer capacity) {
        this.code = code;
        this.school = school;
        this.capacity = capacity;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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
