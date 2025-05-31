package es.upm.miw.apaw_practice.domain.models.hotel;

import java.math.BigDecimal;

public interface HotelRoomBuilders {

    interface Number {
        Type number(String number);
    }

    interface Type {
        Price type(String type);
    }

    interface Price {
        Optionals price(BigDecimal price);
    }

    interface Optionals {
        Optionals reserved(boolean reserved);
        HotelRoom build();
    }
}
