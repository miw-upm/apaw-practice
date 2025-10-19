package es.upm.miw.apaw.domain.services.martialartsgym;

import es.upm.miw.apaw.domain.persistenceports.martialartsgym.ClassSessionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassSessionService {

    private final ClassSessionPersistence classSessionPersistence;

    @Autowired
    public ClassSessionService(ClassSessionPersistence classSessionPersistence) {
        this.classSessionPersistence = classSessionPersistence;
    }

    public void delete(Integer referenceCode) {
        this.classSessionPersistence.delete(referenceCode);
    }
}
