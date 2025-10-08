package es.upm.miw.apaw.adapters.mongodb.warehouse.daos;


import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.LocationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LocationRepository extends MongoRepository<LocationEntity, UUID> {

    Optional<LocationEntity> findByPosition(String position);

    List<LocationEntity> findByAvailabilityTrue();

}
