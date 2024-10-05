package es.upm.miw.apaw_practice.domain.models.course;

public class User {
    private String firstName;
    private String email;
    private TypeUser role;

    public User() {
        //empty for framework
    }

    public User(String firstName, String email, TypeUser role) {
        this.firstName = firstName;
        this.email = email;
        this.role = role;
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

    public TypeUser getRole() {
        return role;
    }

    public void setRole(TypeUser role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }

    public enum TypeUser {
        STUDENT,
        STUDENT_TUTOR
    }
}
