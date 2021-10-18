package es.upm.miw.apaw_practice.adapters.mongodb.hotel.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos.HotelGuestRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos.HotelRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelGuestEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelGuest;
import es.upm.miw.apaw_practice.domain.persistence_ports.hotel.HotelGuestPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("hotelGuestPersistence")
public class HotelGuestPersistenceMongodb implements HotelGuestPersistence {

    private final HotelGuestRepository hotelGuestRepository;
    private final HotelRepository hotelRepository;

    @Autowired
    public HotelGuestPersistenceMongodb(HotelGuestRepository hotelGuestRepository, HotelRepository hotelRepository) {
        this.hotelGuestRepository = hotelGuestRepository;
        this.hotelRepository = hotelRepository;
    }

    @Override
    public HotelGuest create(HotelGuest hotelGuest) {
        return this.hotelGuestRepository
                .save(new HotelGuestEntity(hotelGuest))
                .toHotelGuest();
    }

    @Override
    public HotelGuest readByDni(String dni) {
        return this.hotelGuestRepository
                .findByDni(dni)
                .orElseThrow(() -> new NotFoundException("HotelGuest DNI: " + dni))
                .toHotelGuest();
    }

    @Override
    public void delete(String dni) {
        this.hotelGuestRepository.deleteByDni(dni);
    }


}
