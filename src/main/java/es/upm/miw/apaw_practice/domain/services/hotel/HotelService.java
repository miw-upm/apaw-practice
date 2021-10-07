package es.upm.miw.apaw_practice.domain.services.hotel;

import es.upm.miw.apaw_practice.domain.persistence_ports.hotel.HotelPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class HotelService {
    private final HotelPersistence hotelPersistence;

    @Autowired
    public HotelService(HotelPersistence hotelPersistence) {
        this.hotelPersistence = hotelPersistence;
    }


}
