package es.upm.miw.apaw_practice.domain.models.basketball;

import java.time.LocalDateTime;
import java.util.List;

public interface BasketMatchBuilders {

    interface Id {
        Date id(int id);
    }

    interface Date {
        Address date(LocalDateTime date);
    }

    interface Address {
        Optionals address(String address);
    }

    interface Optionals {
        Optionals basketPlayers(List<BasketPlayer> basketPlayers);
        BasketMatch build();
    }

}
