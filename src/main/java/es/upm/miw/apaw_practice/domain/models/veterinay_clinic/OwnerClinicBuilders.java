package es.upm.miw.apaw_practice.domain.models.veterinay_clinic;

public interface OwnerClinicBuilders {

    interface Name {
        Address name(String name);
    }

    interface Address {
        Optionals address(String address);
    }

    interface Optionals {
        Optionals phone(String phone);
        OwnerClinic build();
    }
}
