package es.upm.miw.apaw.adapters.mongodb.apiary.daos;

import es.upm.miw.apaw.adapters.mongodb.apiary.entities.ApiaryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface ApiaryRepository extends MongoRepository<ApiaryEntity, UUID> {
    Optional<ApiaryEntity> findByCadastralRef(String cadastralRef);

    int deleteByCadastralRef(String cadastralRef);
}
