package es.upm.miw.apaw_practice.adapters.mongodb.art_museum.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.art_museum.Artwork;
import es.upm.miw.apaw_practice.domain.models.art_museum.Exhibition;
import es.upm.miw.apaw_practice.domain.models.art_museum.Museum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class MuseumPersistenceMongodbIT {
    @Autowired
    private MuseumPersistenceMongodb museumPersistence;

    @Test
    void testReadByName() {
        Museum museum = this.museumPersistence.readByName("El Prado");
        assertEquals(600, museum.getCapacity());
        assertTrue(museum.getOpen());
        assertEquals(2, museum.getArtworks().size());
        assertEquals(1, museum.getExhibitions().size());
        assertTrue(museum.getArtworks().stream()
                .map(Artwork::getInventoryNumber)
                .toList()
                .containsAll(List.of("27004", "27005")));
        assertTrue(museum.getExhibitions().stream()
                .map(Exhibition::getName)
                .toList()
                .contains("Spanish authors"));
    }

    @Test
    void testReadByNameNotFound() {
        assertThrows(NotFoundException.class, () -> this.museumPersistence.readByName("Reina Sofia"));
    }

    @Test
    void testUpdateExhibition() {
        Museum museum = this.museumPersistence.readByName("Thyssen");
        BigDecimal newPrice = BigDecimal.valueOf(30.00);
        museum.getExhibitions().get(1).setPrice(newPrice);
        this.museumPersistence.updateExhibitionPrice(museum, museum.getExhibitions().get(1).getName());

        Museum updateMuseum = this.museumPersistence.readByName("Thyssen");
        assertEquals(newPrice, updateMuseum.getExhibitions().get(1).getPrice());
    }
}
