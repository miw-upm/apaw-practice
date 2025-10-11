package es.upm.miw.apaw.adapters.mongodb.winery.persistence;

import es.upm.miw.apaw.adapters.mongodb.winery.daos.WinerySeeder;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.winery.Wine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
public class WinePersistenceMongodbIT {

    @Autowired
    private WinePersistenceMongodb winePersistenceMongodb;

    @Autowired
    private WinerySeeder winerySeeder;

    @BeforeEach
    void resetDb(){
        winerySeeder.deleteAll();
        winerySeeder.seedDatabase();
    }

    @Test
    void testReadById() {
        Wine wine = this.winePersistenceMongodb.readById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"));
        assertThat(wine.getName()).isEqualTo("Cabernet Sauvignon");
        assertThat(wine.getYear()).isEqualTo(2020);
        assertThat(wine.getPrice()).isEqualByComparingTo("25.50");
        assertThat(wine.getAlcoholPercentage()).isEqualTo(13.5);
    }

    @Test
    void testDelete() {
        UUID wineId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003");

        Wine wineBefore = this.winePersistenceMongodb.readById(wineId);
        assertThat(wineBefore).isNotNull();
        assertThat(wineBefore.getId()).isEqualTo(wineId);

        this.winePersistenceMongodb.delete(wineId);

        assertThrows(NotFoundException.class, () -> this.winePersistenceMongodb.readById(wineId));
    }

}
