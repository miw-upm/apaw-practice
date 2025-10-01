package es.upm.miw.apaw.adapters.mongodb.vehicle.daos;

import es.upm.miw.apaw.adapters.mongodb.vehicle.entities.VehicleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface VehicleRepository extends MongoRepository<VehicleEntity, UUID> {
}
