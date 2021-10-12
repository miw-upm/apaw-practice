package es.upm.miw.apaw_practice.adapters.rest.hotel;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos.DirectorRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos.HotelRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.DirectorEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.RoomEntity;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.hotel.Director;
import es.upm.miw.apaw_practice.domain.models.hotel.Hotel;
import es.upm.miw.apaw_practice.domain.models.hotel.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RestTestConfig
class HotelResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private DirectorRepository directorRepository;

    @BeforeEach
    void init() {
        DirectorEntity directorEntity =
                new DirectorEntity(new Director("5050505L", "email@email.email", 920110022));

        this.directorRepository.save(directorEntity);
        List<RoomEntity> roomEntities = List.of(
                new RoomEntity(56, new BigDecimal(90), true, new ArrayList<>()),
                new RoomEntity(15, new BigDecimal(50), false, new ArrayList<>()));


        HotelEntity hotelEntity = new HotelEntity("Barcelona, Perla", 4, directorEntity,
                roomEntities);

        hotelEntity.setId("0");
        this.hotelRepository.save(hotelEntity);
    }

    @Test
    void testRead() {
        String id = "0";
        this.webTestClient
                .get()
                .uri(HotelResource.HOTELS + HotelResource.ID_ID, id)
                .exchange()
                .expectStatus().isOk().expectBody(Hotel.class)
                .value(hotel -> {
                    assertEquals("5050505L", hotel.getDirector().getDni());
                    assertEquals(4, hotel.getNumberStars());
                    assertEquals(new BigDecimal(90), hotel.getRooms().get(0).getPrice());
                    assertTrue(hotel.getRooms().get(0).isVip());
                });
    }

    @Test
    void testReadNotFound() {
        String id = "oo";
        this.webTestClient
                .get()
                .uri(HotelResource.HOTELS + HotelResource.ID_ID, id)
                .exchange()
                .expectStatus().isNotFound();
    }


    @Test
    void testUpdateRoomPrice() {
        Room roomParams = new Room(12, new BigDecimal(120), false, null);

        String id = "0";
        this.webTestClient
                .get()
                .uri(HotelResource.HOTELS + HotelResource.ID_ID, id)
                .exchange()
                .expectStatus().isOk().expectBody(Hotel.class)
                .value(hotel ->
                        assertEquals(new BigDecimal(90), hotel.getRooms().get(0).getPrice())
                );

        this.webTestClient
                .put()
                .uri(HotelResource.HOTELS + HotelResource.ID_ID + HotelResource.ROOMS + HotelResource.NUMBER_ROOM + HotelResource.PRICE_ROOM, 0, 56)
                .body(BodyInserters.fromValue(roomParams))
                .exchange()
                .expectStatus().isOk();

        this.webTestClient
                .get()
                .uri(HotelResource.HOTELS + HotelResource.ID_ID, id)
                .exchange()
                .expectBody(Hotel.class)
                .value(hotel ->
                        assertEquals(new BigDecimal(120), hotel.getRooms().get(0).getPrice())
                );

    }

}
