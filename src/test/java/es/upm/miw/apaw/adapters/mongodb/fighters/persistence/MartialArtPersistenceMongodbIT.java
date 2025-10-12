package es.upm.miw.apaw.adapters.mongodb.fighters.persistence;

import es.upm.miw.apaw.adapters.mongodb.fighters.daos.FightersSeeder;
import es.upm.miw.apaw.domain.models.fighters.MartialArt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class MartialArtPersistenceMongodbIT {

    @Autowired
    private MartialArtPersistenceMongodb martialArtPersistenceMongodb;

    @Autowired
    private FightersSeeder fightersSeeder;

    @Test
    void testFindByDiscipline() {
        MartialArt martialArt = this.martialArtPersistenceMongodb.readByDiscipline("BJJ");
        assertThat(martialArt.getDiscipline()).isEqualTo("BJJ");
        assertThat(martialArt.getOrigin()).isEqualTo("Brazil");
        assertThat(martialArt.getDescription()).isEqualTo("Brazilian Jiu-Jitsu");
        assertThat(martialArt.getStriking()).isFalse();
        assertThat(martialArt.getGrappling()).isTrue();
    }

    @Test
    void testUpdate() {
        MartialArt martialArt = this.martialArtPersistenceMongodb.readByDiscipline("BJJ");
        assertThat(martialArt).isNotNull();
        assertThat(martialArt.getDiscipline()).isEqualTo("BJJ");
        martialArt.setOrigin("Brazil/JP");
        martialArt.setDescription("Brazilian Jiu-Jitsu UPDATED");
        martialArt.setStriking(false);
        martialArt.setGrappling(true);
        this.martialArtPersistenceMongodb.update(martialArt);

        MartialArt martialArtUpdated = this.martialArtPersistenceMongodb.readByDiscipline("BJJ");
        assertThat(martialArtUpdated).isNotNull();
        assertThat(martialArtUpdated.getOrigin()).isEqualTo("Brazil/JP");
        assertThat(martialArtUpdated.getDescription()).isEqualTo("Brazilian Jiu-Jitsu UPDATED");
        assertThat(martialArtUpdated.getStriking()).isFalse();
        assertThat(martialArtUpdated.getGrappling()).isTrue();
        fightersSeeder.deleteAll();
        fightersSeeder.seedDatabase();
    }
}
