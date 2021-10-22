package es.upm.miw.apaw_practice.domain.models.emarketer;

public interface CustomerBuilders {
    interface Name {
        CustomerBuilders.Address name(String name);

    }
    interface Address {
        CustomerBuilders.Type address(String address);
    }

    interface Type {
        CustomerBuilders.Optionals type(String type);
    }

    interface Optionals {
        Customer build();
    }

}
