package es.upm.miw.apaw.domain.services.fighters;

import es.upm.miw.apaw.adapters.mongodb.fighters.daos.FightersSeeder;
import es.upm.miw.apaw.adapters.mongodb.fighters.daos.MartialArtRepository;
import es.upm.miw.apaw.adapters.mongodb.fighters.entities.MartialArtEntity;
import es.upm.miw.apaw.domain.models.fighters.MartialArt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class MartialArtServiceIT {
    @Autowired
    private MartialArtService service;

    @Autowired
    private MartialArtRepository repository;

    @Autowired
    private FightersSeeder seeder;

    @Test
    void testUpdate_usesPathDiscipline() {
        MartialArt body = MartialArt.builder()
                .discipline("IGNORED")
                .origin("Japan")
                .description("kata & kumite")
                .striking(true)
                .grappling(false)
                .build();

        MartialArt result = service.update("Karate", body);

        assertThat(result.getDiscipline()).isEqualTo("Karate");
        assertThat(result.getOrigin()).isEqualTo("Japan");
        assertThat(result.getDescription()).isEqualTo("kata & kumite");
        assertThat(result.getStriking()).isTrue();
        assertThat(result.getGrappling()).isFalse();

        MartialArtEntity martialArtUpdated = repository.findByDiscipline("Karate").orElseThrow();
        assertThat(martialArtUpdated.getOrigin()).isEqualTo("Japan");
        assertThat(martialArtUpdated.getDescription()).isEqualTo("kata & kumite");
        seeder.deleteAll();
        seeder.seedDatabase();
    }
}
