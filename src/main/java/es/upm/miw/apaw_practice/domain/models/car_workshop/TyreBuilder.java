package es.upm.miw.apaw_practice.domain.models.car_workshop;

import java.math.BigDecimal;

public interface TyreBuilder {

    interface Manufacturer {
        Model manufacturer(String manufacturer);
    }

    interface Model {
        Price model(String model);
    }

    interface Price {
        Optionals price(BigDecimal price);
    }

    interface Optionals {
        Tyre build();
    }

}
