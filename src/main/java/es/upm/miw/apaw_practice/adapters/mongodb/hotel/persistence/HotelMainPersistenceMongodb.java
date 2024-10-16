package es.upm.miw.apaw_practice.adapters.mongodb.hotel.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos.HotelMainRepository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelMain;
import es.upm.miw.apaw_practice.domain.persistence_ports.hotel.HotelMainPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("hotelMainPersistence")
public class HotelMainPersistenceMongodb implements HotelMainPersistence{

    private final HotelMainRepository hotelMainRepository;

        @Autowired
        public HotelMainPersistenceMongodb(HotelMainRepository hotelMainRepository) {
            this.hotelMainRepository = hotelMainRepository;
        }

        @Override
        public HotelMain findByName(String name) {
            return this.hotelMainRepository.findByName(name)
                    .orElseThrow(() -> new NotFoundException(" HotelMain name: " + name))
                    .toHotel();
        }
}

