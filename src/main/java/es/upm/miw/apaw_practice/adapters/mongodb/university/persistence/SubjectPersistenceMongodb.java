package es.upm.miw.apaw_practice.adapters.mongodb.university.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.university.daos.ClassroomRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.university.daos.SubjectRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.SubjectEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.university.Classroom;
import es.upm.miw.apaw_practice.domain.models.university.Subject;
import es.upm.miw.apaw_practice.domain.persistence_ports.university.SubjectPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("subjectPersistence")
public class SubjectPersistenceMongodb implements SubjectPersistence {

    private final SubjectRepository subjectRepository;
    private final ClassroomRepository classroomRepository;

    @Autowired
    public SubjectPersistenceMongodb(SubjectRepository subjectRepository, ClassroomRepository classroomRepository) {
        this.subjectRepository = subjectRepository;
        this.classroomRepository = classroomRepository;
    }

    @Override
    public Subject readByReference(Integer reference) {
        return this.subjectRepository
                .findByReference(reference)
                .orElseThrow(() -> new NotFoundException("Subject reference:" + reference))
                .toSubject();
    }

    @Override
    public void update(Subject subject) {
        SubjectEntity subjectEntity = this.subjectRepository
                .findByReference(subject.getReference())
                .orElseThrow(() -> new NotFoundException("Subject reference:" + subject.getReference()));
        Classroom classroom = subject.getClassroom();
        if (classroom != null) {
            this.classroomRepository
                    .findBySchoolAndNumber(classroom.getSchool(), classroom.getNumber())
                    .orElseThrow(() ->
                            new NotFoundException("Classroom school and number: " + classroom.getSchool() + ";" + classroom.getNumber()));
        }
        subjectEntity.fromSubject(subject);
        this.subjectRepository.save(subjectEntity);
    }
}
