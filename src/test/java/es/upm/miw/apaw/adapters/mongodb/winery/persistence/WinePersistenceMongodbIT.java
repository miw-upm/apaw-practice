package es.upm.miw.apaw.adapters.mongodb.winery.persistence;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.winery.Wine;
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

    @Test
    void testReadById() {
        Wine wine = this.winePersistenceMongodb.readById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"));
        assertThat(wine.getName()).isEqualTo("Chardonnay");
        assertThat(wine.getYear()).isEqualTo(2021);
        assertThat(wine.getPrice()).isEqualByComparingTo("15.75");
        assertThat(wine.getAlcoholPercentage()).isEqualTo(12.5);
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
