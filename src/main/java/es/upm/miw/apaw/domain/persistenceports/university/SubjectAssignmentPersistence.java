package es.upm.miw.apaw.domain.persistenceports.university;

import es.upm.miw.apaw.domain.models.university.SubjectAssignment;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface SubjectAssignmentPersistence {
    SubjectAssignment getById(UUID id);

    SubjectAssignment update(UUID id, SubjectAssignment subjectAssignment);

    Stream<SubjectAssignment> findAll();
}
