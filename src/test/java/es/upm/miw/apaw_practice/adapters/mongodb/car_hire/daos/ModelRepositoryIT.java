package es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class ModelRepositoryIT {

    @Autowired
    private ModelRepository modelRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(modelRepository.findAll().stream()
                .anyMatch(model ->
                        "Opel Insignia".equals(model.getType()) &&
                                        "Tipo Berlina, manual".equals(model.getDescription()) &&
                                        model.getId() != null &&
                                        model.getEnginePower().equals(140) &&
                                        1 == model.getVehicleEntities().size()
                ));
    }
}
