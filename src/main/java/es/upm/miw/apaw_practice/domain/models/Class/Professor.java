package es.upm.miw.apaw_practice.domain.models.Class;

import java.time.LocalDate;

public class Professor {

    private String name;
    private String course;
    private int age;
    private LocalDate EntryDate;

    public Professor() {
        //empty for framework
    }

    public Professor(String name, String course, int age, LocalDate EntryDate) {
        this.name = name;
        this.course = course;
        this.age = age;
        this.EntryDate = EntryDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getEntryData() {
        return EntryDate;
    }

    public void setEntryDate(LocalDate EntryData) {
        this.EntryDate = EntryData;
    }


    @Override
    public String toString() {
        return "Professor {" +
                "name =" + name + '\'' +
                ", course =" + course + '\'' +
                ", age =" + age + '\'' +
                ", EntryDate =" + EntryDate + '\'' +
                '}';
    }

}
