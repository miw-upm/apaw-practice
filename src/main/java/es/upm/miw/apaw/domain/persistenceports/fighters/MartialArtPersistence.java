package es.upm.miw.apaw.domain.persistenceports.fighters;

import es.upm.miw.apaw.domain.models.fighters.MartialArt;

public interface MartialArtPersistence {
    MartialArt readByDiscipline(String discipline);
    MartialArt update(MartialArt martialArt);
}
