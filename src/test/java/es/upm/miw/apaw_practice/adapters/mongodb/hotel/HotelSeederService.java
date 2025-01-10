package es.upm.miw.apaw_practice.adapters.mongodb.hotel;


import es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos.HotelClientRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos.HotelMainRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos.HotelReservationRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelClientEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelMainEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelReservationEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelRoomEntity;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class HotelSeederService {

    @Autowired
    private HotelMainRepository hotelMainRepository;

    @Autowired
    private HotelClientRepository hotelClientRepository;

    @Autowired
    private HotelReservationRepository hotelReservationRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Hotel Initial Load -----------");

        HotelReservationEntity[] reservations = {
                new HotelReservationEntity("1", "101", LocalDate.of(2020,1,1)),
                new HotelReservationEntity("2", "202", LocalDate.of(2020,12,22)),
                new HotelReservationEntity("3", "303", LocalDate.of(2020,10,12))
        };
        this.hotelReservationRepository.saveAll(Arrays.asList(reservations));

        HotelClientEntity[] clients = {
                new HotelClientEntity("y1111111x", "David", "600000000", "", reservations[0]),
                new HotelClientEntity("y2222222x", "Mengtxu", "612345678", "", reservations[1]),
                new HotelClientEntity("y3333333x", "Wuli", "687654321", "", reservations[2])
        };
        this.hotelClientRepository.saveAll(Arrays.asList(clients));

        HotelRoomEntity[] rooms = {
                new HotelRoomEntity("101", "single", new BigDecimal("75.00"), true),
                new HotelRoomEntity("202", "dual", new BigDecimal("125.00"), true),
                new HotelRoomEntity("303", "dual", new BigDecimal("155.00"), false),
                new HotelRoomEntity("404", "single", new BigDecimal("60.00"), false)
        };

        HotelMainEntity[] hotels = {
                new HotelMainEntity("xiangHotel", "Street God", "966666666", List.of(rooms[0], rooms[1], rooms[2]), List.of(clients[0], clients[1], clients[2])),
                new HotelMainEntity("mengfeiHotel", "Street Kekw", "5201314", List.of(rooms[0], rooms[1]), List.of(clients[0], clients[1]))
        };
        this.hotelMainRepository.saveAll(Arrays.asList(hotels));
    }
    public void deleteAll() {
        this.hotelMainRepository.deleteAll();
        this.hotelClientRepository.deleteAll();
        this.hotelReservationRepository.deleteAll();
    }
}
