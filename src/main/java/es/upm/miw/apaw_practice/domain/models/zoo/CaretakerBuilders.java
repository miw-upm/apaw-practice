package es.upm.miw.apaw_practice.domain.models.zoo;

public interface CaretakerBuilders {

    interface Dni {

        CaretakerBuilders.Name dni(String dni);

    }

    interface Name {

        CaretakerBuilders.Surname name(String name);

    }

    interface Surname {

        CaretakerBuilders.Optionals surname(String surname);

    }

    interface Optionals {

        Caretaker build();

    }
}
