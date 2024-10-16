package es.upm.miw.apaw_practice.domain.models.hotel_retired;

import java.time.LocalDateTime;

public interface GuestBuilders {

    interface Nif {
        FullName nif(String nif);
    }

    interface FullName {
        BirthDay fullName(String fullName);
    }

    interface BirthDay {
        Optionals birthDay(LocalDateTime birthDay);
    }

    interface Optionals {
        Guest build();

    }
}
