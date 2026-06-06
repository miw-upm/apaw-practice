package es.upm.miw.apaw.adapters.mongodb.theater.daos;

import es.upm.miw.apaw.BaseTheaterTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class TheaterArtistRepositoryIT extends BaseTheaterTests {

    @Autowired
    private TheaterArtistRepository theaterArtistRepository;

    @Test
    void testFindByArtistCode() {
        assertThat(theaterArtistRepository.findByArtistCode(artists[0].getArtistCode()))
                .isPresent()
                .hasValueSatisfying(e -> {
                    assertThat(e.getArtistCode()).isEqualTo(artists[0].getArtistCode());
                    assertThat(e.getArtistFullName()).isEqualTo(artists[0].getArtistFullName());
                    assertThat(e.getArtistBirthDate()).isEqualTo(artists[0].getArtistBirthDate());
                    assertThat(e.getArtistFee()).isEqualTo(artists[0].getArtistFee());
                    assertThat(e.getArtistActive()).isEqualTo(artists[0].getArtistActive());
                });
    }

    @Test
    void testFindByArtistCode_NotFound() {
        assertThat(theaterArtistRepository.findByArtistCode("NONEXISTENT")).isEmpty();
    }

    @Test
    void testDeleteByArtistCode() {
        int deleted = theaterArtistRepository.deleteByArtistCode(artists[0].getArtistCode());
        assertThat(deleted).isEqualTo(1);
        assertThat(theaterArtistRepository.findByArtistCode(artists[0].getArtistCode())).isEmpty();
    }

    @Test
    void testFindAll() {
        assertThat(theaterArtistRepository.findAll()).hasSizeGreaterThanOrEqualTo(2);
    }
}
