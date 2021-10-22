package es.upm.miw.apaw_practice.adapters.mongodb.car_hire.entities;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.car_hire.Model;
import es.upm.miw.apaw_practice.domain.models.car_hire.Vehicle;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class ModelEntityTest {

    @Test
    void testCreateModelEntityAndToModel() {
        List<Vehicle> vehicles = List.of(
                Vehicle.builder()
                        .vinNumber("12345")
                        .dailyCost(new BigDecimal("50"))
                        .kilometersAmount(50000)
                        .goodCondition(Boolean.TRUE)
                        .build(),
                Vehicle.builder()
                        .vinNumber("54321")
                        .dailyCost(new BigDecimal("60"))
                        .kilometersAmount(40000)
                        .goodCondition(Boolean.TRUE)
                        .build()
        );
        Model model = new Model("Type", "Description", 100, vehicles);
        ModelEntity modelEntity = new ModelEntity(model);

        assertEquals(model.getType(), modelEntity.getType());
        assertEquals(model.getDescription(), modelEntity.getDescription());
        assertEquals(model.getEnginePower(), modelEntity.getEnginePower());

        for (int i = 0; i < modelEntity.getVehicleEntities().size(); i++) {
            assertEquals(modelEntity.getVehicleEntities().get(i).getId(), model.getVehicleList().get(i).getId());
            assertEquals(modelEntity.getVehicleEntities().get(i).getVinNumber(), model.getVehicleList().get(i).getVinNumber());
            assertEquals(modelEntity.getVehicleEntities().get(i).getDailyCost(), model.getVehicleList().get(i).getDailyCost());
            assertEquals(modelEntity.getVehicleEntities().get(i).getKilometersAmount(), model.getVehicleList().get(i).getKilometersAmount());
            assertEquals(modelEntity.getVehicleEntities().get(i).getGoodCondition(), model.getVehicleList().get(i).getGoodCondition());
        }

        Model modelToModel = modelEntity.toModel();

        assertEquals(modelToModel.getType(), modelEntity.getType());
        assertEquals(modelToModel.getDescription(), modelEntity.getDescription());
        assertEquals(modelToModel.getEnginePower(), modelEntity.getEnginePower());

        for (int i = 0; i < modelEntity.getVehicleEntities().size(); i++) {
            assertEquals(modelEntity.getVehicleEntities().get(i).getId(), modelToModel.getVehicleList().get(i).getId());
            assertEquals(modelEntity.getVehicleEntities().get(i).getVinNumber(), modelToModel.getVehicleList().get(i).getVinNumber());
            assertEquals(modelEntity.getVehicleEntities().get(i).getDailyCost(), modelToModel.getVehicleList().get(i).getDailyCost());
            assertEquals(modelEntity.getVehicleEntities().get(i).getKilometersAmount(), modelToModel.getVehicleList().get(i).getKilometersAmount());
            assertEquals(modelEntity.getVehicleEntities().get(i).getGoodCondition(), modelToModel.getVehicleList().get(i).getGoodCondition());
        }
    }
}
