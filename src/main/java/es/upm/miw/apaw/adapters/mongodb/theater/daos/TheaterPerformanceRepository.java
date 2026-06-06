package es.upm.miw.apaw.adapters.mongodb.theater.daos;

import es.upm.miw.apaw.adapters.mongodb.theater.entities.TheaterPerformanceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface TheaterPerformanceRepository extends MongoRepository<TheaterPerformanceEntity, UUID> {
    Optional<TheaterPerformanceEntity> findByPerformanceCode(String performanceCode);

    int deleteByPerformanceCode(String performanceCode);
}
