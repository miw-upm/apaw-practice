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

    public static UserCourseBuilders.Email build(){
        return new Builder();
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

    public static class Builder implements UserCourseBuilders.FirstName, UserCourseBuilders.Email, UserCourseBuilders.Role, UserCourseBuilders.Builder {


        private final User user;

        public Builder(){
            this.user = new User();
        }

        @Override
        public UserCourseBuilders.FirstName email(String email) {
            this.user.email = email;
            return this;
        }

        @Override
        public UserCourseBuilders.Role firstName(String firstName) {
            this.user.firstName = firstName;
            return this;
        }

        @Override
        public UserCourseBuilders.Builder role(User.TypeUser role) {
            this.user.role = role;
            return this;
        }

        @Override
        public User build() {
            return this.user;
        }
    }

}
