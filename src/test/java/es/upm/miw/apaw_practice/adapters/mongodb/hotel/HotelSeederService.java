package es.upm.miw.apaw_practice.adapters.mongodb.hotel;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos.DirectorRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos.HotelGuestRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos.HotelRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.DirectorEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelGuestEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.RoomEntity;
import es.upm.miw.apaw_practice.domain.models.hotel.Director;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelGuest;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class HotelSeederService {

    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private DirectorRepository directorRepository;
    @Autowired
    private HotelGuestRepository hotelGuestRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Hotel Initial Load -----------");

        DirectorEntity[] directors = {
                new DirectorEntity(new Director("77777777V", "test@email.com", 222222222)),
                new DirectorEntity(new Director("22222222P", "email@email.com", 999999999)),
                new DirectorEntity(new Director("44444444L", "director@email.com", 222222222))
        };
        this.directorRepository.saveAll(Arrays.asList(directors));

        HotelGuestEntity[] hotelGuests = {
                new HotelGuestEntity(new HotelGuest("Mario", "88888888K", LocalDateTime.of(2015, 8, 10, 12, 30),
                        LocalDateTime.of(2015, 8, 25, 15, 30))),
                new HotelGuestEntity(new HotelGuest("Laura", "25252525R", LocalDateTime.of(2020, 6, 15, 9, 0),
                        LocalDateTime.of(2018, 9, 16, 16, 0))),
                new HotelGuestEntity(new HotelGuest("Pedro", "11111111S", LocalDateTime.of(2020, 6, 15, 9, 0),
                        LocalDateTime.of(2018, 9, 16, 16, 0))),
                new HotelGuestEntity(new HotelGuest("Luca", "56565656P", LocalDateTime.of(2020, 10, 6, 12, 0),
                        LocalDateTime.of(2020, 10, 12, 18, 0)))

        };
        this.hotelGuestRepository.saveAll(Arrays.asList(hotelGuests));

        RoomEntity[] rooms = {
                new RoomEntity(22, new BigDecimal(45), false, List.of(hotelGuests[1], hotelGuests[2])),
                new RoomEntity(3, new BigDecimal(250), true, new ArrayList<>()),
                new RoomEntity(45, new BigDecimal(120), true, List.of(hotelGuests[0])),
                new RoomEntity(12, new BigDecimal(60), false, new ArrayList<>())
        };

        HotelEntity[] hotels = {
                new HotelEntity("Av. Madrid, Madrid, 32452", 3, directors[0], List.of(rooms[0])),
                new HotelEntity("Av. Luto, 23981", 2, directors[0], List.of(rooms[1])),
                new HotelEntity("Av. Salamanca, Salamanca, 15243", 4, directors[2], List.of(rooms[1], rooms[2], rooms[3]))
        };
        hotels[1].setId("1");
        this.hotelRepository.saveAll(Arrays.asList(hotels));
    }

    public void deleteAll() {
        this.hotelRepository.deleteAll();
        this.hotelGuestRepository.deleteAll();
        this.directorRepository.deleteAll();
    }
}
