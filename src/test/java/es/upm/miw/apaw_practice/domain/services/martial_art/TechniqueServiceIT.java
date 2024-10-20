package es.upm.miw.apaw_practice.domain.services.martial_art;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;


import es.upm.miw.apaw_practice.domain.models.martial_art.Style;
import es.upm.miw.apaw_practice.domain.models.martial_art.Technique;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@TestConfig

public class TechniqueServiceIT {
    @Autowired
    private TechniqueService techniqueService;

    @Test
    void testCreateAndRead() {
        Style style = new Style(
                "Estilo de combate",
                "Tecnica pensada para el deporte de contacto",
                4,
                "america"
        );
        Technique technique = new Technique("Ap dollyo", 3, true, 1.0, style,Collections.emptyList());
        Technique technique1 = this.techniqueService.create(technique);
        assertEquals("Ap dollyo", technique1.getName());
        assertEquals(1.0, technique1.getDifficulty());
    }



    @Test
    void testDelete() {
        Style style = new Style(
                "Estilo de exhibición",
                "Tecnica pensada el entretenimiento",
                4,
                "america"
        );
        Technique technique = new Technique("Tornado Kick", 3, true, 1.0, style,Collections.emptyList());
        this.techniqueService.create(technique);
        this.techniqueService.delete("Tornado Kick");
        assertThrows(NotFoundException.class, () -> this.techniqueService.read("Tornado Kick"));
    }
}
