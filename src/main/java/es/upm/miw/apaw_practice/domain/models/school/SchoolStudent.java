package es.upm.miw.apaw_practice.domain.models.school;

import java.time.LocalDate;

public class SchoolStudent {
    private String name;
    private LocalDate birthday;
    private String level;
    private SchoolTeacher schoolTeacher;

    public SchoolStudent() {
        //empty for framework
    }

    public SchoolStudent(String name, LocalDate birthday, String level, SchoolTeacher schoolTeacher) {
        this.name = name;
        this.birthday = birthday;
        this.level = level;
        this.schoolTeacher = schoolTeacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public SchoolTeacher getSchoolTeacher() {
        return schoolTeacher;
    }

    public void setSchoolTeacher(SchoolTeacher schoolTeacher) {
        this.schoolTeacher = schoolTeacher;
    }

    @Override
    public String toString() {
        return "SchoolStudent{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                ", level='" + level + '\'' +
                ", schoolTeacher=" + schoolTeacher +
                '}';
    }
}
