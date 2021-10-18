package es.upm.miw.apaw_practice.adapters.mongodb.car_hire.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos.ModelRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.entities.ModelEntity;
import es.upm.miw.apaw_practice.domain.models.car_hire.Model;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_hire.ModelPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository("modelPersistence")
public class ModelPersistenceMongodb implements ModelPersistence {

    private final ModelRepository modelRepository;

    @Autowired
    public ModelPersistenceMongodb(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public Stream<Model> readAll() {
        return this.modelRepository.findAll()
                .stream()
                .map(ModelEntity::toModel);
    }

    @Override
    public Model readByVinNumber(String vinNumber) {
        return this.modelRepository
                .findAll().stream()
                .filter(modelEntity ->
                        modelEntity.getVehicleEntities().stream()
                                .anyMatch(vehicleEntity ->
                                        vehicleEntity.getVinNumber().equals(vinNumber)))
                .map(ModelEntity::toModel)
                .collect(Collectors.toList()).get(0);
    }
}