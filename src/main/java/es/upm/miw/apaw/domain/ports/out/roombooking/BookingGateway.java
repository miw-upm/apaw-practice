package es.upm.miw.apaw.domain.ports.out.roombooking;

import es.upm.miw.apaw.domain.model.roombooking.Booking;

public interface BookingGateway {
    Booking create(Booking booking);
}