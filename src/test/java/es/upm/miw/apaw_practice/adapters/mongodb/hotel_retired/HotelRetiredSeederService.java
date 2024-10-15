package es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.daos.BookingRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.daos.GuestRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.daos.HotelRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.daos.RoomRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.entities.BookingEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.entities.GuestEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.entities.HotelEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.entities.RoomEntity;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class HotelRetiredSeederService {

    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private GuestRepository guestRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private BookingRepository bookingRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Hotel Retired Initial Load -----------");

        GuestEntity[] guests = {
                new GuestEntity("99527370E", "Emilio Pedrajas", LocalDateTime.of(1990, 10, 27, 23, 2, 2)),
                new GuestEntity("38184875E", "Juan Padrón", LocalDateTime.of(1980, 7, 2, 23, 2, 2)),
                new GuestEntity("38087519W", "Iñaki Jauregui", LocalDateTime.of(1976, 2, 21, 23, 2, 2)),
                new GuestEntity("84133147K", "Basilio Chamartín", LocalDateTime.of(1945, 2, 21, 23, 2, 2)),
                new GuestEntity("03948142P", "Carla Sempere", LocalDateTime.of(2021, 4, 12, 23, 2, 2)),
                new GuestEntity("70526971R", "Antonia Ndonga", LocalDateTime.of(2004, 12, 21, 23, 2, 2)),
                new GuestEntity("02465328G", "Sisinio Riubal", LocalDateTime.of(1936, 1, 2, 23, 2, 2)),
        };
        this.guestRepository.saveAll(Arrays.asList(guests));

        BookingEntity[] bookings = {
                new BookingEntity(true, LocalDate.of(2024, 10, 1), LocalDate.of(2024, 10, 7), Arrays.asList(guests).get(0)),
                new BookingEntity(false, LocalDate.of(2024, 10, 1), LocalDate.of(2024, 10, 7), Arrays.asList(guests).get(1)),
                new BookingEntity(true, LocalDate.of(2024, 10, 1), LocalDate.of(2024, 10, 7), Arrays.asList(guests).get(2)),
                new BookingEntity(true, LocalDate.of(2024, 10, 1), LocalDate.of(2024, 10, 7), Arrays.asList(guests).get(3)),
                new BookingEntity(true, LocalDate.of(2024, 10, 1), LocalDate.of(2024, 10, 7), Arrays.asList(guests).get(4)),
                new BookingEntity(true, LocalDate.of(2024, 10, 1), LocalDate.of(2024, 10, 7), Arrays.asList(guests).get(5)),
                new BookingEntity(true, LocalDate.of(2024, 10, 1), LocalDate.of(2024, 10, 7), Arrays.asList(guests).get(6)),
        };
        bookingRepository.saveAll(Arrays.asList(bookings));

        RoomEntity[] rooms = {
                new RoomEntity("101", false, 1, BigDecimal.valueOf(59.99), List.of(bookings)),
                new RoomEntity("102", false, 1, BigDecimal.valueOf(59.99), Collections.emptyList()),
                new RoomEntity("103", false, 2, BigDecimal.valueOf(79.99), List.of(bookings)),
                new RoomEntity("104", false, 1, BigDecimal.valueOf(59.99), List.of(bookings)),
                new RoomEntity("105", false, 2, BigDecimal.valueOf(79.99), Collections.emptyList()),
                new RoomEntity("106", false, 1, BigDecimal.valueOf(59.99), Collections.emptyList()),
                new RoomEntity("107", false, 1, BigDecimal.valueOf(59.99), Collections.emptyList()),
                new RoomEntity("108", false, 1, BigDecimal.valueOf(59.99), Collections.emptyList()),
                new RoomEntity("109", false, 1, BigDecimal.valueOf(59.99), List.of(bookings)),
                new RoomEntity("201", false, 4, BigDecimal.valueOf(99.99), Collections.emptyList()),
                new RoomEntity("201", false, 1, BigDecimal.valueOf(59.99), Collections.emptyList()),
                new RoomEntity("202", false, 1, BigDecimal.valueOf(59.99), Collections.emptyList()),
                new RoomEntity("203", false, 1, BigDecimal.valueOf(59.99), Collections.emptyList()),
                new RoomEntity("204", false, 1, BigDecimal.valueOf(59.99), Collections.emptyList()),
                new RoomEntity("205", false, 4, BigDecimal.valueOf(99.99), Collections.emptyList()),
                new RoomEntity("206", false, 1, BigDecimal.valueOf(59.99), List.of(bookings)),
                new RoomEntity("207", false, 1, BigDecimal.valueOf(59.99), Collections.emptyList()),
                new RoomEntity("208", false, 2, BigDecimal.valueOf(79.99), Collections.emptyList()),
                new RoomEntity("209", false, 1, BigDecimal.valueOf(59.99), Collections.emptyList()),
        };
        this.roomRepository.saveAll(Arrays.asList(rooms));

        HotelEntity[] hotels = {
                new HotelEntity("F91635847", "LaMaria", "C/ Mandrágora 32, Retuerta (Burgos)", List.of(rooms)),
                new HotelEntity("W2135920C", "Papagayo", "C/ Ventisquera 45, Roquetas de Mar (Almería)", Collections.emptyList())
        };
        this.hotelRepository.saveAll(Arrays.asList(hotels));
    }

    public void deleteAll() {
        this.hotelRepository.deleteAll();
        this.roomRepository.deleteAll();
        this.bookingRepository.deleteAll();
        this.guestRepository.deleteAll();
    }
}
