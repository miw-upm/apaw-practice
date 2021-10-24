package es.upm.miw.apaw_practice.domain.models.cinema;

import java.time.LocalDate;

public interface SpectatorBuilder {

    interface IdCard {
        Name idCard(String idCard);
    }

    interface Name {
        Optionals name(String name);
    }

    interface Optionals {
        Optionals familyName(String familyName);
        Optionals registrationDate(LocalDate registrationDate);

        Spectator build();
    }

}
