package es.upm.miw.apaw_practice.adapters.mongodb.military.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.military.entities.WeaponEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WeaponRepository extends MongoRepository<WeaponEntity, String> {
    List<WeaponEntity> findBySerialCodeIn(List<String> serialCodes);
}
