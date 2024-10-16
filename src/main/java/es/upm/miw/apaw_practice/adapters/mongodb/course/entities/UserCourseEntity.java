package es.upm.miw.apaw_practice.adapters.mongodb.course.entities;

import es.upm.miw.apaw_practice.domain.models.course.User;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class UserCourseEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String firstName;
    private String email;
    private String role;

    public UserCourseEntity() {
        //Empty for framework
    }

    public UserCourseEntity(String firstName, String email, String role) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.email = email;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User toUserCourse(){
        User user = new User();
        BeanUtils.copyProperties(this, user, "role");
        user.setRole(User.TypeUser.valueOf(this.role));
        return user;
    }

    public enum TypeUser {
        STUDENT ,
        STUDENT_TUTOR
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (email.equals(((UserCourseEntity) obj).email));
    }

    @Override
    public int hashCode() {
        return this.email.hashCode();
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
