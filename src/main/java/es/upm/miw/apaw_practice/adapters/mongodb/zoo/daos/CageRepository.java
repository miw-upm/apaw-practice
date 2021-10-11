package es.upm.miw.apaw_practice.adapters.mongodb.zoo.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities.CageEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities.ZooEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.stream.Stream;

public interface CageRepository extends MongoRepository<CageEntity, String> {

    Stream<CageEntity> findByLocationCode(String locationCode);

    Stream<CageEntity> findByZoo(ZooEntity zooEntity);

}
