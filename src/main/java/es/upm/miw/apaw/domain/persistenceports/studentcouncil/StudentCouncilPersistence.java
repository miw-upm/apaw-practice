package es.upm.miw.apaw.domain.persistenceports.studentcouncil;

import es.upm.miw.apaw.domain.models.studentcouncil.StudentCouncil;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public interface StudentCouncilPersistence {
    Optional<StudentCouncil> readById(UUID id);

    StudentCouncil update(StudentCouncil studentCouncil);

    Stream<StudentCouncil> readAll();
}
