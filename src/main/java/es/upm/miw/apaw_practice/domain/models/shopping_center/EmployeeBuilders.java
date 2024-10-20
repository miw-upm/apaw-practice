package es.upm.miw.apaw_practice.domain.models.shopping_center;

public interface EmployeeBuilders {

    interface Dni {
        Name dni(String dni);
    }

    interface Name {
        Optionals name(String name);
    }

    interface Optionals {
        Optionals phone(String phone);
        Employee build();
    }
}
