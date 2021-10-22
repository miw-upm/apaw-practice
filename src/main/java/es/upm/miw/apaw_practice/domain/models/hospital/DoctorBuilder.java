package es.upm.miw.apaw_practice.domain.models.hospital;

import java.time.LocalDate;

public interface DoctorBuilder {

    interface Nick {
        Surname nick(String nick);
    }

    interface Surname {
        ActiveSince surname(String surname);
    }

    interface ActiveSince {
        Optionals activeSince(LocalDate activeSince);
    }

    interface Optionals {
        Doctor build();
    }
}
