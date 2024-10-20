package es.upm.miw.apaw_practice.adapters.mongodb.martial_art.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.persistence.StylePersistenceMongodb;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.martial_art.Style;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class StylePersistenceMongodbIT {
    @Autowired
    private StylePersistenceMongodb stylePersistenceMongodb;
    @Test
    void testReadNotFound() {
        assertThrows(NotFoundException.class, () -> this.stylePersistenceMongodb.read("0"));
    }

    @Test
    void testNameNotExists() {
        assertFalse(this.stylePersistenceMongodb.existsByName("0"));
    }

    @Test
    void testNameExists() {
        assertTrue(this.stylePersistenceMongodb.existsByName("Estilo de combate"));
    }

    /*
    @Test
    void testCreateAndRead() {
        Style style;
        style = new Style(
                "Estilo ITF",
                "Estilo que actual no es un deporte olimpico",
                100,
                "Norte america"
        );
        this.stylePersistenceMongodb.create(style);
        Style style1 = this.stylePersistenceMongodb.read("Estilo ITF");
        assertEquals("Estilo ITF", style1.getName());
        ;
        assertEquals("Norte america", style1.getOriginCountry());
        assertEquals(100, style1.getPopularity());
    }*/
    @Test
    void testCreateAndUpdate() {
            Style style = new Style(
                    "Estilo ITF",
                    "Estilo que actual no es un deporte olimpico",
                    100,
                    "Norte america"
            );
            this.stylePersistenceMongodb.create(style);
            Style instructor1 = this.stylePersistenceMongodb.read("Estilo ITF");
            assertEquals("Estilo ITF", instructor1.getName());
            instructor1.setName("Estilo DO");
            Style instructor2 = this.stylePersistenceMongodb.update("Estilo ITF", instructor1);
            assertEquals("Estilo DO", instructor2.getName());
    }
}
