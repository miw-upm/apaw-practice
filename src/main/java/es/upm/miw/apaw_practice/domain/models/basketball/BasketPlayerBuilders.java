package es.upm.miw.apaw_practice.domain.models.basketball;

public interface BasketPlayerBuilders {

    interface Dni {
        Name dni(String dni);
    }

    interface Name {
        Dorsal name(String name);
    }

    interface Dorsal {
        Points dorsal(int dorsal);
    }

    interface Points {
        Builder points(int points);
    }

    interface Builder {
        BasketPlayer build();
    }
}