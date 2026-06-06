package es.upm.miw.apaw.adapters.mongodb.theater.persistence;

import es.upm.miw.apaw.BaseTheaterTests;
import es.upm.miw.apaw.adapters.mongodb.theater.daos.TheaterHallRepository;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.theater.TheaterHall;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TheaterHallPersistenceMongodbIT extends BaseTheaterTests {

    @Autowired
    private TheaterHallPersistenceMongodb theaterHallPersistenceMongodb;

    @Autowired
    private TheaterHallRepository theaterHallRepository;

    @Test
    void testCreateAndRead() {
        TheaterHall hall = TheaterHall.builder()
                .hallCode("THLCR")
                .hallName("New Test Hall")
                .hallCapacity(200)
                .hallAccessible(true)
                .build();
        theaterHallPersistenceMongodb.create(hall);
        TheaterHall read = theaterHallPersistenceMongodb.read("THLCR");
        assertThat(read.getHallCode()).isEqualTo("THLCR");
        assertThat(read.getHallName()).isEqualTo("New Test Hall");
        assertThat(read.getHallCapacity()).isEqualTo(200);
        assertThat(read.getHallAccessible()).isTrue();
        theaterHallRepository.deleteByHallCode("THLCR");
    }

    @Test
    void testRead_NotFound() {
        assertThrows(NotFoundException.class, () -> theaterHallPersistenceMongodb.read("NONEXISTENT"));
    }

    @Test
    void testUpdate() {
        TheaterHall hall = TheaterHall.builder()
                .hallCode("THLUP")
                .hallName("Original Hall")
                .hallCapacity(150)
                .hallAccessible(false)
                .build();
        theaterHallPersistenceMongodb.create(hall);
        hall.setHallName("Updated Hall");
        hall.setHallCapacity(180);
        hall.setHallAccessible(true);
        TheaterHall updated = theaterHallPersistenceMongodb.update("THLUP", hall);
        assertThat(updated.getHallName()).isEqualTo("Updated Hall");
        assertThat(updated.getHallCapacity()).isEqualTo(180);
        assertThat(updated.getHallAccessible()).isTrue();
        theaterHallRepository.deleteByHallCode("THLUP");
    }

    @Test
    void testUpdate_NotFound() {
        TheaterHall hall = TheaterHall.builder()
                .hallCode("NONEXISTENT")
                .hallName("No Hall")
                .hallCapacity(0)
                .hallAccessible(false)
                .build();
        assertThrows(NotFoundException.class, () -> theaterHallPersistenceMongodb.update("NONEXISTENT", hall));
    }

    @Test
    void testReadAll() {
        long initial = theaterHallPersistenceMongodb.readAll().count();
        TheaterHall hall = TheaterHall.builder()
                .hallCode("THLRA")
                .hallName("ReadAll Hall")
                .hallCapacity(50)
                .hallAccessible(true)
                .build();
        theaterHallPersistenceMongodb.create(hall);
        assertThat(theaterHallPersistenceMongodb.readAll().count()).isEqualTo(initial + 1);
        theaterHallRepository.deleteByHallCode("THLRA");
    }

    @Test
    void testExistsByHallCode() {
        assertThat(theaterHallPersistenceMongodb.existsByHallCode(halls[0].getHallCode())).isTrue();
        assertThat(theaterHallPersistenceMongodb.existsByHallCode("NONEXISTENT")).isFalse();
    }
}
