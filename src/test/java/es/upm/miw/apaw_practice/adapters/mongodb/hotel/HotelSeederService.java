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

        HotelGuestEntity[] hotelGuests = {
                new HotelGuestEntity(new HotelGuest("Mario", "88888888K", LocalDateTime.of(2015, 8, 10, 12, 30),
                        LocalDateTime.of(2015, 8, 25, 15, 30))),
                new HotelGuestEntity(new HotelGuest("Mario", "88888888K", LocalDateTime.of(2020, 6, 15, 9, 0),
                        LocalDateTime.of(2018, 9, 16, 16, 0))),
                new HotelGuestEntity(new HotelGuest("Pedro", "11111111S", LocalDateTime.of(2020, 6, 15, 9, 0),
                        LocalDateTime.of(2018, 9, 16, 16, 0))),
                new HotelGuestEntity(new HotelGuest("Luca", "56565656P", LocalDateTime.of(2020, 10, 6, 12, 0),
                        LocalDateTime.of(2020, 10, 12, 18, 0)))

        };
        this.hotelGuestRepository.saveAll(Arrays.asList(hotelGuests));

        RoomEntity[] rooms = {
                new RoomEntity(22, new BigDecimal(45), false, List.of(hotelGuests[0],hotelGuests[1], hotelGuests[2])),
                new RoomEntity(3, new BigDecimal(250), true, new ArrayList<>()),
                new RoomEntity(45, new BigDecimal(120), true, List.of(hotelGuests[0])),
                new RoomEntity(12, new BigDecimal(60), false, new ArrayList<>())
        };

        HotelEntity[] hotels = {
                new HotelEntity("MariaLuisa", "Av. Madrid, Madrid, 32452", 3,  List.of(rooms[0])),
                new HotelEntity("Estrella", "Av. Luto, 23981", 2,  List.of(rooms[1])),
                new HotelEntity("Gran hotel", "Av. Salamanca, Salamanca, 15243", 4, List.of(rooms[1], rooms[2], rooms[3]))
        };
        hotels[1].setId("1");
        hotels[2].setId("2");
        this.hotelRepository.saveAll(Arrays.asList(hotels));

        DirectorEntity[] directors = {
                new DirectorEntity("77777777V", "test@email.com", 222222222, List.of(hotels[0])),
                new DirectorEntity("22222222P", "email@email.com", 999999999, List.of(hotels[1])),
                new DirectorEntity("44444444L", "director@email.com", 222222222, List.of(hotels[2]))
        };
        this.directorRepository.saveAll(Arrays.asList(directors));


    }

    public void deleteAll() {
        this.hotelRepository.deleteAll();
        this.hotelGuestRepository.deleteAll();
        this.directorRepository.deleteAll();
    }
}
