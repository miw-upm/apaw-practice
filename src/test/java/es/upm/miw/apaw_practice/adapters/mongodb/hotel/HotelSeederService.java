package es.upm.miw.apaw_practice.adapters.mongodb.hotel;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.DirectorEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelGuestEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.RoomEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.daos.DirectorRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.daos.HotelGuestRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.daos.HotelRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.daos.RoomRepository;
import es.upm.miw.apaw_practice.domain.models.hotel.Director;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;

@Service
public class HotelSeederService {

    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private DirectorRepository directorRepository;
    @Autowired
    private HotelGuestRepository hotelGuestRepository;
    @Autowired
    private RoomRepository roomRepository;

    public void sendDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Hotel Initial Load -----------");

        DirectorEntity[] directors = {
                new DirectorEntity(new Director("77777777V", "test@email.com", 222222222)),
                new DirectorEntity(new Director("22222222P", "email@email.com", 999999999)),
                new DirectorEntity(new Director("44444444L", "director@email.com", 222222222))
        };
        this.directorRepository.saveAll(Arrays.asList(directors));

        HotelGuestEntity[] hotelGuestEntities = {
                new HotelGuestEntity("88888888K", "Mario", LocalDateTime.of(2015, 8, 10, 12, 30),
                        LocalDateTime.of(2015, 8, 25, 15, 30)),
                new HotelGuestEntity("25252525R", "Laura", LocalDateTime.of(2018, 9, 1, 9, 0),
                        LocalDateTime.of(2018, 9, 16, 16, 0)),
                new HotelGuestEntity("11111111S", "Pedro", LocalDateTime.of(2020, 6, 15, 9, 0),
                        LocalDateTime.of(2018, 9, 16, 16, 0))

        };
        this.hotelGuestRepository.saveAll(Arrays.asList(hotelGuestEntities));

        RoomEntity[]rooms = {
                //Todo
        };
        //Todo --> Save Repository
        HotelEntity[]hotels = {
                //Todo
        };
        //Todo --> Save Repository
    }

    public void deleteAll(){
        //ToDo
    }

}
