package es.upm.miw.apaw.adapters.mongodb.theater.daos;

import es.upm.miw.apaw.BaseTheaterTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class TheaterVenueRepositoryIT extends BaseTheaterTests {

    @Autowired
    private TheaterVenueRepository theaterVenueRepository;

    @Test
    void testFindByVenueCode() {
        assertThat(theaterVenueRepository.findByVenueCode(venues[0].getVenueCode()))
                .isPresent()
                .hasValueSatisfying(e -> {
                    assertThat(e.getVenueCode()).isEqualTo(venues[0].getVenueCode());
                    assertThat(e.getVenueName()).isEqualTo(venues[0].getVenueName());
                    assertThat(e.getVenueCity()).isEqualTo(venues[0].getVenueCity());
                    assertThat(e.getVenueOpen()).isEqualTo(venues[0].getVenueOpen());
                    assertThat(e.getVenueManagerId()).isEqualTo(venues[0].getVenueManagerId());
                    assertThat(e.getVenueHalls()).hasSize(2);
                });
    }

    @Test
    void testFindByVenueCode_NotFound() {
        assertThat(theaterVenueRepository.findByVenueCode("NONEXISTENT")).isEmpty();
    }

    @Test
    void testDeleteByVenueCode() {
        int deleted = theaterVenueRepository.deleteByVenueCode(venues[0].getVenueCode());
        assertThat(deleted).isEqualTo(1);
        assertThat(theaterVenueRepository.findByVenueCode(venues[0].getVenueCode())).isEmpty();
    }

    @Test
    void testFindAll() {
        assertThat(theaterVenueRepository.findAll()).hasSizeGreaterThanOrEqualTo(1);
    }
}
