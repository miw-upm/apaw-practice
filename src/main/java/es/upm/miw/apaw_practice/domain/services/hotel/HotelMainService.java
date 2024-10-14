package es.upm.miw.apaw_practice.domain.services.hotel;

import es.upm.miw.apaw_practice.domain.models.hotel.HotelMain;
import es.upm.miw.apaw_practice.domain.persistence_ports.hotel.HotelMainPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class HotelMainService {

    private final HotelMainPersistence hotelMainPersistence;

    @Autowired
    public HotelMainService (HotelMainPersistence hotelMainPersistence) {
        this.hotelMainPersistence = hotelMainPersistence;
    }

    public HotelMain findById(String id) {
        return this.hotelMainPersistence.findById(id);
    }
}
