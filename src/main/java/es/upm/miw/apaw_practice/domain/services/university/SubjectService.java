package es.upm.miw.apaw_practice.domain.services.university;

import es.upm.miw.apaw_practice.domain.models.university.Classroom;
import es.upm.miw.apaw_practice.domain.models.university.Subject;
import es.upm.miw.apaw_practice.domain.models.university.SubjectCreditsUpdating;
import es.upm.miw.apaw_practice.domain.persistence_ports.university.SubjectPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class SubjectService {

    private final SubjectPersistence subjectPersistence;

    @Autowired
    public SubjectService(SubjectPersistence subjectPersistence) {
        this.subjectPersistence = subjectPersistence;
    }

    public void updateClassroom(Integer reference, Classroom classroom) {
        Subject subject = this.subjectPersistence.readByReference(reference);
        subject.setClassroom(classroom);
        this.subjectPersistence.update(subject);
    }

    public void updateCredits(Stream<SubjectCreditsUpdating> subjectCreditsUpdating) {
        subjectCreditsUpdating.map(subjectNewCredits -> {
                    Subject subject = this.subjectPersistence.readByReference(subjectNewCredits.getReference());
                    subject.setCredits(subjectNewCredits.getCredits());
                    return subject;
                })
                .forEach(subject -> this.subjectPersistence.update(subject.getReference(), subject));
    }
}
