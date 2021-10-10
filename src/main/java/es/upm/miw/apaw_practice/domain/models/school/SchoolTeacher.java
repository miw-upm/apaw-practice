package es.upm.miw.apaw_practice.domain.models.school;

import java.util.List;

public class SchoolTeacher {
    private String name;
    private Integer age;
    private String phoneNumber;
    private List<SchoolCourse> schoolCourses;
    private List<SchoolStudent> schoolStudents;

    public SchoolTeacher() {
        //empty for framework
    }

    public SchoolTeacher(String name, Integer age, String phoneNumber, List<SchoolCourse> schoolCourses, List<SchoolStudent> schoolStudents) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.schoolCourses = schoolCourses;
        this.schoolStudents = schoolStudents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<SchoolCourse> getSchoolCourses() {
        return schoolCourses;
    }

    public void setSchoolCourses(List<SchoolCourse> schoolCourses) {
        this.schoolCourses = schoolCourses;
    }

    public List<SchoolStudent> getSchoolStudents() {
        return schoolStudents;
    }

    public void setSchoolStudents(List<SchoolStudent> schoolStudents) {
        this.schoolStudents = schoolStudents;
    }

    @Override
    public String toString() {
        return "SchoolTeacher{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", schoolCourses=" + schoolCourses +
                ", schoolStudents=" + schoolStudents +
                '}';
    }
}
