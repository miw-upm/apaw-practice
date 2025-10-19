package es.upm.miw.apaw.domain.persistenceports.music;

import es.upm.miw.apaw.domain.models.music.Style;

public interface StylePersistence {
    void patch(String genre, Style style);
}
