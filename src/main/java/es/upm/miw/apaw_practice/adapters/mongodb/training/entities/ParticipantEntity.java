package es.upm.miw.apaw_practice.adapters.mongodb.training.entities;

import es.upm.miw.apaw_practice.domain.models.training.Course;
import es.upm.miw.apaw_practice.domain.models.training.Participant;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Document
public class ParticipantEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private Boolean graduate;
    private Integer phone;
    private String email;
    @DBRef
    private List<CourseEntity> courseList;

    public ParticipantEntity() {
        //empty from framework
    }

    public ParticipantEntity(String name, Boolean graduate, Integer phone, String email, List<CourseEntity> courseList) {
        this.name = name;
        this.graduate = graduate;
        this.phone = phone;
        this.email = email;
        this.courseList = courseList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getGraduate() {
        return graduate;
    }

    public void setGraduate(Boolean graduate) {
        this.graduate = graduate;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CourseEntity> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseEntity> courseList) {
        this.courseList = courseList;
    }

    public Participant toParticipant() {
        List<Course> courses = this.courseList.stream()
                .map(CourseEntity::toCourse)
                .collect(Collectors.toList());
        return new Participant(name, graduate, phone, email, courses);
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass()
                && (phone.equals(((ParticipantEntity) obj).phone))
                && (email.equals(((ParticipantEntity) obj).email));
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone,email);
    }

    @Override
    public String toString() {
        return "ParticipantEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", graduate=" + graduate +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", courseList=" + courseList +
                '}';
    }
}
