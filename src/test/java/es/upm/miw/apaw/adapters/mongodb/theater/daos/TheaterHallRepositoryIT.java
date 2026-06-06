package es.upm.miw.apaw.adapters.mongodb.theater.daos;

import es.upm.miw.apaw.BaseTheaterTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class TheaterHallRepositoryIT extends BaseTheaterTests {

    @Autowired
    private TheaterHallRepository theaterHallRepository;

    @Test
    void testFindByHallCode() {
        assertThat(theaterHallRepository.findByHallCode(halls[0].getHallCode()))
                .isPresent()
                .hasValueSatisfying(e -> {
                    assertThat(e.getHallCode()).isEqualTo(halls[0].getHallCode());
                    assertThat(e.getHallName()).isEqualTo(halls[0].getHallName());
                    assertThat(e.getHallCapacity()).isEqualTo(halls[0].getHallCapacity());
                    assertThat(e.getHallAccessible()).isEqualTo(halls[0].getHallAccessible());
                });
    }

    @Test
    void testFindByHallCode_NotFound() {
        assertThat(theaterHallRepository.findByHallCode("NONEXISTENT")).isEmpty();
    }

    @Test
    void testDeleteByHallCode() {
        int deleted = theaterHallRepository.deleteByHallCode(halls[0].getHallCode());
        assertThat(deleted).isEqualTo(1);
        assertThat(theaterHallRepository.findByHallCode(halls[0].getHallCode())).isEmpty();
    }

    @Test
    void testFindAll() {
        assertThat(theaterHallRepository.findAll()).hasSizeGreaterThanOrEqualTo(2);
    }
}
