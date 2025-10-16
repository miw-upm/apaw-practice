package es.upm.miw.apaw.adapters.mongodb.football.persistence;


import es.upm.miw.apaw.adapters.mongodb.football.daos.FootballSeeder;
import es.upm.miw.apaw.domain.models.football.Stadium;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class StadiumPersistenceMongodbIT {

    @Autowired
    private StadiumPersistenceMongodb stadiumPersistence;
    @Autowired
    private FootballSeeder footballSeeder;

    @BeforeEach
    void resetDb() {
        footballSeeder.deleteAll();
        footballSeeder.seedDatabase();
    }

    @Test
    void testFindByOfficialName_ok() {
        Optional<Stadium> stadium = this.stadiumPersistence.findByOfficialName("Salamanca Stadium");
        assertThat(stadium).isPresent();
        assertThat(stadium.get().getOfficialName()).isEqualTo("Salamanca Stadium");
        assertThat(stadium.get().getCapacity()).isEqualTo(40000);
    }

    @Test
    void testFindByOfficialName_notFound() {
        Optional<Stadium> stadium = this.stadiumPersistence.findByOfficialName("Nonexistent Stadium");
        assertThat(stadium).isEmpty();
    }

    @Test
    void testReadAll_ok() {
        List<Stadium> stadiums = this.stadiumPersistence.readAll();
        assertThat(stadiums).hasSize(2);
        assertThat(stadiums).extracting(Stadium::getOfficialName)
                .containsExactlyInAnyOrder("Salamanca Stadium", "Madrid Arena");
    }
}

