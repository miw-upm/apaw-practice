package es.upm.miw.apaw.adapters.mongodb.university.daos;

import es.upm.miw.apaw.adapters.mongodb.university.entities.TeacherEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface TeacherRepository extends MongoRepository<TeacherEntity, UUID> {
}

