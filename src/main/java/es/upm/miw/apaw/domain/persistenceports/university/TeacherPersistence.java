package es.upm.miw.apaw.domain.persistenceports.university;

import es.upm.miw.apaw.domain.models.university.Teacher;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TeacherPersistence {
    Teacher getById(UUID id);

    Teacher update(UUID id, Teacher teacher);

    boolean existIdentificationCode(String identificationCode);
}

