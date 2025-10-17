package es.upm.miw.apaw.domain.persistenceports.fighters;

import es.upm.miw.apaw.domain.models.fighters.Coach;

public interface CoachPersistence {
    Coach readByFullName(String fullName);
}
