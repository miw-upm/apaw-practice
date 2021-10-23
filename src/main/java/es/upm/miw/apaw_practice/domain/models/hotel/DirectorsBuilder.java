package es.upm.miw.apaw_practice.domain.models.hotel;

import java.util.List;

public interface DirectorsBuilder {

    interface Dni {
        Email dni(String dni);
    }

    interface Email {
        Telephone email(String email);
    }

    interface Telephone {
        ListHotels telephone(Integer telephone);

    }

    interface ListHotels {
        Optionals listHotels(List<Hotel> hotelList);
    }

    interface Optionals {
        Director build();
    }
}
