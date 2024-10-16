package es.upm.miw.apaw_practice.domain.models.hotel_retired;

import java.time.LocalDate;

public interface BookingBuilders {

    interface Confirmed {
        DateIn confirmed(boolean confirmed);
    }

    interface DateIn {
        DateOut dateIn(LocalDate dateIn);
    }

    interface DateOut {
        Optionals dateOut(LocalDate dateOut);
    }

    interface Optionals {
        Optionals guest(Guest guest);
        Booking build();
    }
}
