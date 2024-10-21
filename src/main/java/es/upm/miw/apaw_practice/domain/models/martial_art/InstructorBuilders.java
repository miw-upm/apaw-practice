package es.upm.miw.apaw_practice.domain.models.martial_art;

import java.time.LocalDateTime;

public interface InstructorBuilders {

    interface Dni {
        FullName dni(String dni);
    }

    interface FullName {
        PhoneNumber fullName(String fullName);
    }

    interface PhoneNumber {
        BirthDate phoneNumber(Integer phoneNumber);
    }

    interface BirthDate {
        Optionals birthDate(LocalDateTime birthDate);
    }

    interface Optionals {
        Instructor build();
    }
}
