package es.upm.miw.apaw_practice.domain.models.hotel;

import java.time.LocalDateTime;

public interface HotelGuestBuilders {
    interface Dni{
        Name dni(String dni);
    }

    interface Name{
        EntryDate name(String name);
    }

    interface EntryDate{
        DepartureDate entryDate(LocalDateTime entryDate);
    }

    interface DepartureDate{
       Optionals departureDate(LocalDateTime departureDate);
    }

    interface Optionals{
        HotelGuest build();
    }

}
