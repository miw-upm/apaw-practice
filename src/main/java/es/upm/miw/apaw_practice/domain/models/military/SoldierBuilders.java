package es.upm.miw.apaw_practice.domain.models.military;

import java.time.LocalDate;

public interface SoldierBuilders {
    interface IdentityDocument {
        FullName identityDocument(String identityDocument);
    }

    interface FullName {
        Rank fullName(String fullName);
    }

    interface Rank {
        Optionals rank(String rank);
    }

    interface Optionals {
        Optionals birthDate(LocalDate birthDate);
        Soldier build();
    }
}
