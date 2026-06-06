package es.upm.miw.apaw.adapters.mongodb.theater.persistence;

import es.upm.miw.apaw.BaseTheaterTests;
import es.upm.miw.apaw.adapters.mongodb.theater.daos.TheaterArtistRepository;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.theater.TheaterArtist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TheaterArtistPersistenceMongodbIT extends BaseTheaterTests {

    @Autowired
    private TheaterArtistPersistenceMongodb theaterArtistPersistenceMongodb;

    @Autowired
    private TheaterArtistRepository theaterArtistRepository;

    @Test
    void testCreateAndRead() {
        TheaterArtist artist = TheaterArtist.builder()
                .artistCode("TARTCR")
                .artistFullName("Create Test Artist")
                .artistBirthDate(LocalDate.of(1993, 5, 20))
                .artistFee(new BigDecimal("700.00"))
                .artistActive(true)
                .build();
        theaterArtistPersistenceMongodb.create(artist);
        TheaterArtist read = theaterArtistPersistenceMongodb.read("TARTCR");
        assertThat(read.getArtistCode()).isEqualTo("TARTCR");
        assertThat(read.getArtistFullName()).isEqualTo("Create Test Artist");
        assertThat(read.getArtistBirthDate()).isEqualTo(LocalDate.of(1993, 5, 20));
        assertThat(read.getArtistFee()).isEqualTo(new BigDecimal("700.00"));
        assertThat(read.getArtistActive()).isTrue();
        theaterArtistRepository.deleteByArtistCode("TARTCR");
    }

    @Test
    void testRead_NotFound() {
        assertThrows(NotFoundException.class, () -> theaterArtistPersistenceMongodb.read("NONEXISTENT"));
    }

    @Test
    void testUpdate() {
        TheaterArtist artist = TheaterArtist.builder()
                .artistCode("TARTUP")
                .artistFullName("Update Test Artist")
                .artistBirthDate(LocalDate.of(1994, 1, 1))
                .artistFee(new BigDecimal("500.00"))
                .artistActive(true)
                .build();
        theaterArtistPersistenceMongodb.create(artist);
        artist.setArtistFullName("Updated Name");
        artist.setArtistFee(new BigDecimal("600.00"));
        artist.setArtistActive(false);
        TheaterArtist updated = theaterArtistPersistenceMongodb.update("TARTUP", artist);
        assertThat(updated.getArtistFullName()).isEqualTo("Updated Name");
        assertThat(updated.getArtistFee()).isEqualTo(new BigDecimal("600.00"));
        assertThat(updated.getArtistActive()).isFalse();
        theaterArtistRepository.deleteByArtistCode("TARTUP");
    }

    @Test
    void testUpdate_NotFound() {
        TheaterArtist artist = TheaterArtist.builder()
                .artistCode("NONEXISTENT")
                .artistFullName("No Name")
                .artistBirthDate(LocalDate.of(1995, 1, 1))
                .artistFee(new BigDecimal("100.00"))
                .artistActive(true)
                .build();
        assertThrows(NotFoundException.class, () -> theaterArtistPersistenceMongodb.update("NONEXISTENT", artist));
    }

    @Test
    void testReadAll() {
        long initial = theaterArtistPersistenceMongodb.readAll().count();
        TheaterArtist artist = TheaterArtist.builder()
                .artistCode("TARTRA")
                .artistFullName("ReadAll Test")
                .artistBirthDate(LocalDate.of(1996, 3, 3))
                .artistFee(new BigDecimal("300.00"))
                .artistActive(false)
                .build();
        theaterArtistPersistenceMongodb.create(artist);
        assertThat(theaterArtistPersistenceMongodb.readAll().count()).isEqualTo(initial + 1);
        theaterArtistRepository.deleteByArtistCode("TARTRA");
    }

    @Test
    void testExistsByArtistCode() {
        assertThat(theaterArtistPersistenceMongodb.existsByArtistCode(artists[0].getArtistCode())).isTrue();
        assertThat(theaterArtistPersistenceMongodb.existsByArtistCode("NONEXISTENT")).isFalse();
    }
}
