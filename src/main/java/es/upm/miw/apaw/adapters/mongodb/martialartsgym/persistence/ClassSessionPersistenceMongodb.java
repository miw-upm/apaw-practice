package es.upm.miw.apaw.adapters.mongodb.martialartsgym.persistence;

import es.upm.miw.apaw.adapters.mongodb.martialartsgym.daos.ClassSessionRepository;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.persistenceports.martialartsgym.ClassSessionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClassSessionPersistenceMongodb implements ClassSessionPersistence {

    private final ClassSessionRepository classSessionRepository;

    @Autowired
    public ClassSessionPersistenceMongodb(ClassSessionRepository classSessionRepository) {
        this.classSessionRepository = classSessionRepository;
    }

    @Override
    public void delete(Integer referenceCode) {
        int deletedCount = this.classSessionRepository.deleteByReferenceCode(referenceCode);
        if (deletedCount == 0) {
            throw new NotFoundException("ClassSession not found with referenceCode: " + referenceCode);
        }
    }
}
