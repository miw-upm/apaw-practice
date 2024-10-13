package es.upm.miw.apaw_practice.domain.models.night_life;

public interface CustomerBuilders {
    interface Name {
        Phone name(String name);
    }
    interface Phone {
        Optionals phone(String phone);
    }

    interface Optionals {
        Optionals email(String email);
        Customer build();
    }
}
