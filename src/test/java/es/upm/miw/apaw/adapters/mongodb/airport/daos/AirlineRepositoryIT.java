package es.upm.miw.apaw.adapters.mongodb.airport.daos;

import es.upm.miw.apaw.adapters.mongodb.airport.entities.AirlineEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class AirlineRepositoryIT {

    @Autowired
    private AirlineRepository airlineRepository;

    @Test
    void testFindByName() {
        assertTrue(this.airlineRepository.findByName("Air Europa").isPresent());
        AirlineEntity plane = this.airlineRepository.findByName("Air Europa").get();
        assertThat(plane.getName()).isEqualTo("Air Europa");
        assertThat(plane.getCode()).isEqualTo("UX");
        assertThat(plane.getCountry()).isEqualTo("ES");
    }

    @Test
    void testDeleteByName() {
        this.airlineRepository.deleteByName("Air Europa");
        assertThat(this.airlineRepository.findByName("Air Europa").isPresent()).isFalse();
    }
}
