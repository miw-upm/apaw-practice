package es.upm.miw.apaw_practice.adapters.mongodb.university.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.ClassroomEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClassroomRepository extends MongoRepository<ClassroomEntity, String> {

    int deleteBySchoolAndNumber(String school, Integer number);

    Optional<ClassroomEntity> findBySchoolAndNumber(String school, Integer number);
}
