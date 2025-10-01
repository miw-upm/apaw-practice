package es.upm.miw.apaw.adapters.mongodb.apiary.daos;

import es.upm.miw.apaw.adapters.mongodb.apiary.entities.HiveEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HiveRepository extends MongoRepository<HiveEntity, UUID> {
    Optional<HiveEntity> findByCode(Integer code);

    List<HiveEntity> findByInstallationDateAfter(LocalDate installationDate);

    int deleteByCode(Integer code);
}