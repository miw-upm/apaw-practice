package es.upm.miw.apaw_practice.domain.services.hotel;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelBooking;
import es.upm.miw.apaw_practice.domain.persistence_ports.hotel.HotelBookingPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelBookingService {

    private final HotelBookingPersistence hotelBookingPersistence;

    @Autowired
    public HotelBookingService(HotelBookingPersistence hotelBookingPersistence) {
        this.hotelBookingPersistence = hotelBookingPersistence;
    }

    public HotelBooking create(HotelBooking booking) {
        this.assertNumberNotExist(booking.getNumber());
        return this.hotelBookingPersistence.create(booking);
    }

    private void assertNumberNotExist(Integer number) {
        if (this.hotelBookingPersistence.existsBooking(number)) {
            throw new ConflictException("Booking number taken: " + number);
        }
    }
}
