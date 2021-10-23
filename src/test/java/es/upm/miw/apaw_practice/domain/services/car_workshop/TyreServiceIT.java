package es.upm.miw.apaw_practice.domain.services.car_workshop;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class TyreServiceIT {
    @Autowired
    private TyreService tyreService;

    @Test
    void testFindModelByOwnerNameAndRevision() {
        List<String> models = this.tyreService.findModelByOwnerNameAndRevision("John Doe", true)
                .collect(Collectors.toList());
        assertTrue(models.containsAll(List.of("Ventus Prime", "Kinergy", "Primacy")));
        assertEquals(3, models.size());
    }
}
