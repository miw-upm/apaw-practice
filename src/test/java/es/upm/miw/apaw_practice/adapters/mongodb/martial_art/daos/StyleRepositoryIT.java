package es.upm.miw.apaw_practice.adapters.mongodb.martial_art.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.entities.StyleEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class StyleRepositoryIT {
    @Autowired
    private StyleRepository styleRepository;

    @Test
    void testFindByName() {
        assertTrue(this.styleRepository.findByName("Estilo de combate").isPresent());
        StyleEntity styleEntity = this.styleRepository.findByName("Estilo de combate").get();
        assertEquals("Estilo de combate", styleEntity.getName());
        assertEquals("Tecnica pensada para el deporte de contacto", styleEntity.getDescription());
    }
}
