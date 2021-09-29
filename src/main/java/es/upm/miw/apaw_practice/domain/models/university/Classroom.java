package es.upm.miw.apaw_practice.domain.models.university;

public class Classroom {

    private Integer classroomNumber;
    private String school;
    private Integer capacity;

    public Classroom() {
        //empty for framework
    }

    public Classroom(Integer classroomNumber, String school, Integer capacity) {
        this.classroomNumber = classroomNumber;
        this.school = school;
        this.capacity = capacity;
    }

    public Integer getClassroomNumber() {
        return classroomNumber;
    }

    public void setClassroomNumber(Integer classroomNumber) {
        this.classroomNumber = classroomNumber;
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
