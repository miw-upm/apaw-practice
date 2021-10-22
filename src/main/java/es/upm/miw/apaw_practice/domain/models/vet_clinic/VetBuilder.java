package es.upm.miw.apaw_practice.domain.models.vet_clinic;

public interface VetBuilder {

    interface VetNumber {
        Name vetNumber(Integer vetnumber);
    }

    interface Name {
        Surname name(String name);
    }

    interface Surname {
        Optionals surname(String surname);
    }

    interface Optionals {
        Vet build();
    }
}
