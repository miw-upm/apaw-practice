package es.upm.miw.apaw_practice.domain.persistence_ports.hotel;

import es.upm.miw.apaw_practice.domain.models.hotel.HotelGuest;

public interface HotelGuestPersistence {
    HotelGuest create(HotelGuest hotelGuest);
}
