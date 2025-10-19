package es.upm.miw.apaw.domain.persistenceports.martialartsgym;

import es.upm.miw.apaw.domain.models.martialartsgym.Dojo;
import org.springframework.stereotype.Repository;

@Repository
public interface DojoPersistence {
    Dojo create(Dojo dojo);
}
