package es.upm.miw.apaw.adapters.mongodb.apiary.persistence;

import es.upm.miw.apaw.adapters.mongodb.apiary.daos.ApiaryRepository;
import es.upm.miw.apaw.adapters.mongodb.apiary.entities.ApiaryEntity;
import es.upm.miw.apaw.domain.models.apiary.Apiary;
import es.upm.miw.apaw.domain.persistenceports.apiary.ApiaryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("apiaryPersistence")
public class ApiaryPersistenceMongodb implements ApiaryPersistence {

    private final ApiaryRepository apiaryRepository;

    @Autowired
    public ApiaryPersistenceMongodb(ApiaryRepository apiaryRepository) {
        this.apiaryRepository = apiaryRepository;
    }

    @Override
    public Stream<Apiary> findByLocation(String location) {
        return this.apiaryRepository.findByLocation(location)
                .stream()
                .map(ApiaryEntity::toApiary);
    }
}
