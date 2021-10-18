package es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class ModelRepositoryIT {

    @Autowired
    private ModelRepository modelRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.modelRepository.findAll().stream()
                .anyMatch(model ->
                        "Opel Insignia".equals(model.getType()) &&
                                        "Tipo Berlina, manual".equals(model.getDescription()) &&
                                        model.getId() != null &&
                                        model.getEnginePower().equals(140) &&
                                        1 == model.getVehicleEntities().size() &&
                                        "VSSZZZ6KZ1R149943".equals(model.getVehicleEntities().get(0).getVinNumber()) &&
                                        model.getVehicleEntities().get(0).getKilometersAmount().equals(25400)
                ));

        assertTrue(this.modelRepository.findAll().stream()
                .anyMatch(model ->
                        "Seat Ibiza".equals(model.getType()) &&
                                    "Tipo Compacto, manual".equals(model.getDescription()) &&
                                    90 == model.getEnginePower() &&
                                    2 == model.getVehicleEntities().size() &&
                                    "JCPCBL6HSCX110002".equals(model.getVehicleEntities().get(0).getVinNumber()) &&
                                    "GYWKAS8AHBD284620".equals(model.getVehicleEntities().get(1).getVinNumber())
                ));
    }
}
