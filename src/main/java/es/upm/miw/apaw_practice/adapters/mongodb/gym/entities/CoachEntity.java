package es.upm.miw.apaw_practice.adapters.mongodb.gym.entities;


import es.upm.miw.apaw_practice.domain.models.gym.Coach;
import es.upm.miw.apaw_practice.domain.models.gym.Lesson;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.UUID;

@Document
public class CoachEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String dni;
    private String firstName;
    private String lastName;
    private Integer phone;

    @DBRef
    private LessonEntity lesson;

    public CoachEntity() {
        //empty
    }

    public CoachEntity(String dni, String firstName, String lastName, Integer phone, LessonEntity lesson) {

        this.id = UUID.randomUUID().toString();
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.lesson = lesson;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public LessonEntity getLesson() {
        return lesson;
    }

    public void setLesson(LessonEntity lesson) {
        this.lesson = lesson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoachEntity that = (CoachEntity) o;
        return dni.equals(that.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    public Coach toCoach() {
        Coach coach = new Coach();
        BeanUtils.copyProperties(this, coach, "lesson");
        Lesson lesson = this.lesson.toLesson();
        coach.setLesson(lesson);
        return coach;
    }
}
