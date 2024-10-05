package es.upm.miw.apaw_practice.domain.models.course;

public class User {
    private String firstName;
    private String lastName;
    private TypeUser role;

    public User() {
        //empty for framework
    }

    public User(String firstName, String lastName, TypeUser role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
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
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                '}';
    }

    public enum TypeUser {
        STUDENT,
        STUDENT_TUTOR
    }
}
