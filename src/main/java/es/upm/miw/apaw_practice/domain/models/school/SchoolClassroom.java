package es.upm.miw.apaw_practice.domain.models.school;

import java.util.List;

public class SchoolClassroom {
    private Integer classroomNumber;
    private String description;
    private List<SchoolCourse> schoolCourses;

    public SchoolClassroom() {
        //empty for framework
    }

    public SchoolClassroom(Integer classroomNumber, String description, List<SchoolCourse> schoolCourses) {
        this.classroomNumber = classroomNumber;
        this.description = description;
        this.schoolCourses = schoolCourses;
    }

    public Integer getClassroomNumber() {
        return classroomNumber;
    }

    public void setClassroomNumber(Integer classroomNumber) {
        this.classroomNumber = classroomNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SchoolCourse> getSchoolCourses() {
        return schoolCourses;
    }

    public void setSchoolCourses(List<SchoolCourse> schoolCourses) {
        this.schoolCourses = schoolCourses;
    }

    @Override
    public String toString() {
        return "SchoolClassroom{" +
                "classroomNumber=" + classroomNumber +
                ", description='" + description + '\'' +
                ", schoolCourses=" + schoolCourses +
                '}';
    }
}
