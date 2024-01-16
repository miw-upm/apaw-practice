package es.upm.miw.apaw_practice.domain.services.school;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.school.Subject;
import es.upm.miw.apaw_practice.domain.persistence_ports.school.StudentPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.school.SubjectPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectPersistence subjectPersistence;
    private final StudentPersistence studentPersistence;

    @Autowired
    public SubjectService(SubjectPersistence subjectPersistence, StudentPersistence studentPersistence) {
        this.subjectPersistence = subjectPersistence;
        this.studentPersistence = studentPersistence;
    }

    public void assertTitleNotExist(String title) {
        if (this.subjectPersistence.existTitle(title)) {
            throw new ConflictException("Title does not exist: " + title);
        }
    }

    public Subject create(Subject subject) {
        this.assertTitleNotExist(subject.getTitle());
        return this.subjectPersistence.create(subject);
    }

    public Subject read(String title) {
        return this.subjectPersistence.read(title);
    }

    public void delete(String title) {
        this.subjectPersistence.delete(title);
    }

    public List<String> searchUniqueDescriptionBySmartBoard(Boolean smartBoard) {
        return this.studentPersistence.readAll()
                .filter(student -> student.hasClassroomSmartBoard().equals(smartBoard))
                .flatMap(student -> student.getSubjects().stream())
                .distinct()
                .map(Subject::getDescription)
                .toList();
    }

    public List<String> searchUniqueDescriptionByEmail(String email) {
        return this.studentPersistence.readAll()
                .filter(student -> student.getEmail().equals(email))
                .flatMap(student -> student.getSubjects().stream())
                .distinct()
                .map(Subject::getDescription)
                .toList();
    }
}
