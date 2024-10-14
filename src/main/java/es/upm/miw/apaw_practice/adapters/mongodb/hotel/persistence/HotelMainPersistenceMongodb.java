package es.upm.miw.apaw_practice.adapters.mongodb.hotel.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos.HotelMainRepository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelMain;
import es.upm.miw.apaw_practice.domain.persistence_ports.hotel.HotelMainPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

public class HotelMainPersistenceMongodb implements HotelMainPersistence{

    private final HotelMainRepository hotelMainRepository;

        @Autowired
        public HotelMainPersistenceMongodb(HotelMainRepository hotelMainRepository) {
            this.hotelMainRepository = hotelMainRepository;
        }

        @Override
        public HotelMain findById(String id) {
            return this.hotelMainRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException(" HotelMain id: " + id))
                    .toHotel();
        }
}

