package es.upm.miw.apaw_practice.domain.models.school;

import java.util.List;

public class SchoolCourse {
    private String name;
    private String description;
    private Boolean isActive;
    private SchoolClassroom schoolClassroom;
    private List<SchoolTeacher> schoolTeachers;

    public SchoolCourse() {
        //empty for framework
    }

    public SchoolCourse(String name, String description, Boolean isActive, SchoolClassroom schoolClassroom, List<SchoolTeacher> schoolTeachers) {
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.schoolClassroom = schoolClassroom;
        this.schoolTeachers = schoolTeachers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public SchoolClassroom getSchoolClassroom() {
        return schoolClassroom;
    }

    public void setSchoolClassroom(SchoolClassroom schoolClassroom) {
        this.schoolClassroom = schoolClassroom;
    }

    public List<SchoolTeacher> getSchoolTeachers() {
        return schoolTeachers;
    }

    public void setSchoolTeachers(List<SchoolTeacher> schoolTeachers) {
        this.schoolTeachers = schoolTeachers;
    }

    @Override
    public String toString() {
        return "SchoolCourse{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isActive=" + isActive +
                ", schoolClassroom=" + schoolClassroom +
                ", schoolTeachers=" + schoolTeachers +
                '}';
    }
}
