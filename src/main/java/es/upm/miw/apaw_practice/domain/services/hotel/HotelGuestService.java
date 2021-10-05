package es.upm.miw.apaw_practice.domain.services.hotel;

import es.upm.miw.apaw_practice.domain.models.hotel.HotelGuest;
import es.upm.miw.apaw_practice.domain.persistence_ports.hotel.HotelGuestPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelGuestService {

    private final HotelGuestPersistence hotelGuestPersistence;

    @Autowired
    public HotelGuestService(HotelGuestPersistence hotelGuestPersistence) {
        this.hotelGuestPersistence = hotelGuestPersistence;
    }

    public HotelGuest create(HotelGuest hotelGuest) {
        return this.hotelGuestPersistence.create(hotelGuest);
    }
}
