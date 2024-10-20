package es.upm.miw.apaw_practice.domain.models.basketball;

public interface BasketPlayerBuilders {

    interface Dni {
        Name dni(String dni);
    }

    interface Name {
        Dorsal name(String name);
    }

    interface Dorsal {
        Optionals dorsal(int dorsal);
    }

    interface Optionals {
        Optionals points(int points);
        BasketPlayer build();
    }
}