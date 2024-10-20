package es.upm.miw.apaw_practice.domain.models.course;

public interface UserCourseBuilders {

    interface Email {
        FirstName email(String email);
    }

    interface FirstName {
        Role firstName(String firstName);
    }

    interface Role {
        Builder role(User.TypeUser role);
    }

    interface Builder {
        User build();
    }
}
