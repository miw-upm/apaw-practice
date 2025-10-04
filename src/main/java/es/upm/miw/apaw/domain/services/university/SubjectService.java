package es.upm.miw.apaw.domain.services.university;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.models.university.Subject;
import es.upm.miw.apaw.domain.persistenceports.shop.ArticlePersistence;
import es.upm.miw.apaw.domain.persistenceports.university.SubjectPersistence;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {
    private final SubjectPersistence subjectPersistence;

    @Autowired
    public SubjectService(SubjectPersistence subjectPersistence) {
        this.subjectPersistence = subjectPersistence;
    }

    public Subject create(Subject subject) {
        this.assertNameNotExists(subject.getName());
        return this.subjectPersistence.create(subject);
    }

    public void assertNameNotExists(String name) {
        if (this.subjectPersistence.existName(name)) {
            throw new ConflictException("Name exist: " + name);
        }
    }
}
