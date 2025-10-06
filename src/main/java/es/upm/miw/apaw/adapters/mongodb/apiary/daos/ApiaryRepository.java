package es.upm.miw.apaw.adapters.mongodb.apiary.daos;

import es.upm.miw.apaw.adapters.mongodb.apiary.entities.ApiaryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface ApiaryRepository extends MongoRepository<ApiaryEntity, UUID> {
    List<ApiaryEntity> findByLocation(String location);

}
