package es.upm.miw.apaw.adapters.mongodb.vehicle.persistence;

import es.upm.miw.apaw.adapters.mongodb.vehicle.daos.VehicleRepository;
import es.upm.miw.apaw.adapters.mongodb.vehicle.entities.ExtraEntity;
import es.upm.miw.apaw.adapters.mongodb.vehicle.entities.VehicleEntity;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.vehicle.Vehicle;
import es.upm.miw.apaw.domain.persistenceports.vehicle.VehiclePersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository("vehiclePersistence")
public class VehiclePersistenceMongodb implements VehiclePersistence {

    private final VehicleRepository vehicleRepository;

    private final UserRestClient userRestClient;

    @Autowired
    public VehiclePersistenceMongodb(VehicleRepository vehicleRepository,
                                     UserRestClient userRestClient) {
        this.vehicleRepository = vehicleRepository;
        this.userRestClient = userRestClient;
    }

    @Override
    public Stream<Vehicle> readByBrand(String brand) {
        return this.vehicleRepository.findByBrand(brand).stream()
                .map(VehicleEntity::toVehicle);
    }

    @Override
    public List<String> findExtraCategoriesByDocumentationName(String documentationName) {
        return this.vehicleRepository.findAll().stream()
                .filter(vehicleEntity -> vehicleEntity.getDocumentationEntities().stream()
                        .anyMatch(doc -> doc.getName().equals(documentationName)))
                .flatMap(vehicleEntity -> vehicleEntity.getExtraEntities().stream())
                .map(ExtraEntity::getCategory)
                .distinct()
                .toList();
    }

    @Override
    public List<String> findUserMobilesByEngineType(String engineType) {
        return this.vehicleRepository.findAll().stream()
                .filter(vehicleEntity -> vehicleEntity.getEngineEntity() != null
                    && vehicleEntity.getEngineEntity().getType().equals(engineType))
                .map(VehicleEntity::getOwner)
                .map(userId -> userRestClient.readById(userId).getMobile())
                .distinct()
                .toList();
    }

}
