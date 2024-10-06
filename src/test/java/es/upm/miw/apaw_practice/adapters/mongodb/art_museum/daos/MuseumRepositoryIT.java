package es.upm.miw.apaw_practice.adapters.mongodb.art_museum.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.entities.ArtworkEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.entities.ExhibitionEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.entities.MuseumEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class MuseumRepositoryIT {
    @Autowired
    private MuseumRepository museumRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.museumRepository.findByName("El Prado").isPresent());
        MuseumEntity museum = this.museumRepository.findByName("El Prado").get();
        assertEquals("El Prado", museum.getName());
        assertEquals(600, museum.getCapacity());
        assertTrue(museum.getOpen());
        assertTrue(museum.getArtworkEntities().stream()
                .map(ArtworkEntity::getInventoryNumber)
                .toList()
                .containsAll(List.of("27004", "27005")));
        assertTrue(museum.getExhibitionEntities().stream()
                .map(ExhibitionEntity::getName)
                .toList()
                .contains("Spanish authors"));
    }
}
