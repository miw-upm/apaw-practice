package es.upm.miw.apaw.domain.persistenceports.martialartsgym;

import org.springframework.stereotype.Repository;

@Repository
public interface ClassSessionPersistence {
    void delete(Integer referenceCode);
}
