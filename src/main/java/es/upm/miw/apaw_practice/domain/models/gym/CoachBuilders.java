package es.upm.miw.apaw_practice.domain.models.gym;

public interface CoachBuilders {
    interface Dni {
        FirstName dni(String dni);

    }

    interface FirstName {
        LastName firstName(String firstName);
    }

    interface LastName {
        Phone lastname(String lastname);
    }

    interface Phone {
        Lessons phone(Integer phone);
    }

    interface Lessons {
        Optionals lessons(Lesson lesson);
    }

    interface Optionals {
        Coach bulid();
    }
}
