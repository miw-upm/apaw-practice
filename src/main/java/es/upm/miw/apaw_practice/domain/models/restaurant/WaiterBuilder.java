package es.upm.miw.apaw_practice.domain.models.restaurant;

public interface WaiterBuilder {

    interface Id{
        Optionals id(String id);
    }

    interface Optionals{

        Optionals section(String section);

        Optionals category(String category);

        Waiter build();
    }
}
