package es.upm.miw.apaw.adapters.mongodb.university.persistence;

import es.upm.miw.apaw.adapters.mongodb.university.daos.SubjectRepository;
import es.upm.miw.apaw.adapters.mongodb.university.entities.SubjectEntity;
import es.upm.miw.apaw.domain.models.university.Subject;
import es.upm.miw.apaw.domain.persistenceports.university.SubjectPersistence;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("subjectPersistence")
public class SubjectPersistenceMongodb implements SubjectPersistence {
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectPersistenceMongodb(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Subject create(Subject subject) {
        return this.subjectRepository
                .save(new SubjectEntity(subject))
                .toSubject();
    }

    @Override
    public boolean existName(String name) {
        return this.subjectRepository
                .findByName(name)
                .isPresent();
    }
}
