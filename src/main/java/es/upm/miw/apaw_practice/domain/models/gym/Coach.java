package es.upm.miw.apaw_practice.domain.models.gym;

import java.util.List;

public class Coach {
    private String dni;
    private String firstName;
    private String lastName;
    private Integer phone;
    private List <Lesson> lesson;



    public Coach(){
       //empty for framework
    }

    public Coach(String dni, String firstName, String lastName, Integer phone, List<Lesson> lesson) {
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.lesson = lesson;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
       this.phone = phone;
    }

    public List<Lesson> getLesson() {
        return lesson;
    }

    public void setLesson(List<Lesson> lesson) {
        this.lesson = lesson;
    }

    @Override
    public String toString() {
        return "Coach{" +
                "dni='" + dni + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone=" + phone +
                ", lesson=" + lesson +
                '}';
    }
}
