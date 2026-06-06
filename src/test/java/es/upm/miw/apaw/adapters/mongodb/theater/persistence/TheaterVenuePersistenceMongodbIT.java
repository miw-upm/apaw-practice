package es.upm.miw.apaw.adapters.mongodb.theater.persistence;

import es.upm.miw.apaw.BaseTheaterTests;
import es.upm.miw.apaw.adapters.mongodb.theater.daos.TheaterVenueRepository;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.theater.TheaterHall;
import es.upm.miw.apaw.domain.models.theater.TheaterVenue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TheaterVenuePersistenceMongodbIT extends BaseTheaterTests {

    @Autowired
    private TheaterVenuePersistenceMongodb theaterVenuePersistenceMongodb;

    @Autowired
    private TheaterVenueRepository theaterVenueRepository;

    @Test
    void testCreateAndRead() {
        TheaterVenue venue = TheaterVenue.builder()
                .venueCode("TVPCR")
                .venueName("Create Test Venue")
                .venueCity("Valencia")
                .venueOpen(true)
                .venueCreatedAt(LocalDateTime.of(2024, 2, 15, 0, 0))
                .venueHalls(List.of())
                .venueManager(UserDto.builder().id(UUID.randomUUID()).build())
                .build();
        theaterVenuePersistenceMongodb.create(venue);
        TheaterVenue read = theaterVenuePersistenceMongodb.read("TVPCR");
        assertThat(read.getVenueCode()).isEqualTo("TVPCR");
        assertThat(read.getVenueName()).isEqualTo("Create Test Venue");
        assertThat(read.getVenueCity()).isEqualTo("Valencia");
        assertThat(read.getVenueOpen()).isTrue();
        assertThat(read.getVenueManager()).isNotNull();
        theaterVenueRepository.deleteByVenueCode("TVPCR");
    }

    @Test
    void testRead_NotFound() {
        assertThrows(NotFoundException.class, () -> theaterVenuePersistenceMongodb.read("NONEXISTENT"));
    }

    @Test
    void testUpdate() {
        TheaterVenue venue = TheaterVenue.builder()
                .venueCode("TVPUP")
                .venueName("Original Venue")
                .venueCity("Seville")
                .venueOpen(true)
                .venueCreatedAt(LocalDateTime.of(2024, 3, 1, 0, 0))
                .venueHalls(List.of())
                .venueManager(UserDto.builder().id(UUID.randomUUID()).build())
                .build();
        theaterVenuePersistenceMongodb.create(venue);
        venue.setVenueName("Updated Venue");
        venue.setVenueCity("Bilbao");
        venue.setVenueOpen(false);
        TheaterVenue updated = theaterVenuePersistenceMongodb.update("TVPUP", venue);
        assertThat(updated.getVenueName()).isEqualTo("Updated Venue");
        assertThat(updated.getVenueCity()).isEqualTo("Bilbao");
        assertThat(updated.getVenueOpen()).isFalse();
        theaterVenueRepository.deleteByVenueCode("TVPUP");
    }

    @Test
    void testUpdate_NotFound() {
        TheaterVenue venue = TheaterVenue.builder()
                .venueCode("NONEXISTENT")
                .venueName("No Venue")
                .venueCity("Nowhere")
                .venueOpen(false)
                .venueCreatedAt(LocalDateTime.now())
                .venueHalls(List.of())
                .venueManager(UserDto.builder().id(UUID.randomUUID()).build())
                .build();
        assertThrows(NotFoundException.class, () -> theaterVenuePersistenceMongodb.update("NONEXISTENT", venue));
    }

    @Test
    void testReadAll() {
        long initial = theaterVenuePersistenceMongodb.readAll().count();
        TheaterVenue venue = TheaterVenue.builder()
                .venueCode("TVPRA")
                .venueName("ReadAll Venue")
                .venueCity("Malaga")
                .venueOpen(true)
                .venueCreatedAt(LocalDateTime.of(2024, 4, 10, 0, 0))
                .venueHalls(List.of())
                .venueManager(UserDto.builder().id(UUID.randomUUID()).build())
                .build();
        theaterVenuePersistenceMongodb.create(venue);
        assertThat(theaterVenuePersistenceMongodb.readAll().count()).isEqualTo(initial + 1);
        theaterVenueRepository.deleteByVenueCode("TVPRA");
    }

    @Test
    void testExistsByVenueCode() {
        assertThat(theaterVenuePersistenceMongodb.existsByVenueCode(venues[0].getVenueCode())).isTrue();
        assertThat(theaterVenuePersistenceMongodb.existsByVenueCode("NONEXISTENT")).isFalse();
    }

    @Test
    void testFindHallsByVenueCode() {
        var halls = theaterVenuePersistenceMongodb.findHallsByVenueCode(venues[0].getVenueCode()).toList();
        assertThat(halls).hasSize(2);
    }

    @Test
    void testFindHallsByVenueCode_NotFound() {
        assertThrows(NotFoundException.class,
                () -> theaterVenuePersistenceMongodb.findHallsByVenueCode("NONEXISTENT"));
    }

    @Test
    void testVenueToHallUnidirectionalRelationship() {
        TheaterVenue read = theaterVenuePersistenceMongodb.read(venues[0].getVenueCode());
        assertThat(read.getVenueHalls()).hasSize(2);
        TheaterHall firstHall = read.getVenueHalls().get(0);
        assertThat(firstHall.getHallCode()).isNotNull();
        assertThat(firstHall.getHallName()).isNotNull();
        assertThat(firstHall.getHallCapacity()).isNotNull();
        assertThat(firstHall.getHallAccessible()).isNotNull();
        assertThat(firstHall).isNotNull();
    }
}
