package es.upm.miw.apaw.adapters.mongodb.university.persistence;

import es.upm.miw.apaw.adapters.mongodb.university.daos.SubjectAssignmentRepository;
import es.upm.miw.apaw.adapters.mongodb.university.entities.SubjectAssignmentEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.university.SubjectAssignment;
import es.upm.miw.apaw.domain.persistenceports.university.SubjectAssignmentPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("subjectAssignmentPersistence")
public class SubjectAssignmentPersistenceMongodb implements SubjectAssignmentPersistence {
    private final SubjectAssignmentRepository subjectAssignmentRepository;

    @Autowired
    public SubjectAssignmentPersistenceMongodb(SubjectAssignmentRepository subjectAssignmentRepository) {
        this.subjectAssignmentRepository = subjectAssignmentRepository;
    }

    @Override
    public SubjectAssignment getById(UUID id) {
        return this.subjectAssignmentRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("SubjectAssignment id: " + id))
                .toSubjectAssignment();
    }

    @Override
    public SubjectAssignment update(UUID id, SubjectAssignment subjectAssignment) {
        SubjectAssignmentEntity subjectAssignmentEntity = this.subjectAssignmentRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("SubjectAssignment id: " + id));

        subjectAssignmentEntity.setCapacity(subjectAssignment.getCapacity());

        return this.subjectAssignmentRepository
                .save(subjectAssignmentEntity)
                .toSubjectAssignment();
    }
}
