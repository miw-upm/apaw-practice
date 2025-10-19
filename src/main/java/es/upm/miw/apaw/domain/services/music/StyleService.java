package es.upm.miw.apaw.domain.services.music;

import es.upm.miw.apaw.domain.models.music.Style;
import es.upm.miw.apaw.domain.persistenceports.music.StylePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StyleService {

    private final StylePersistence stylePersistence;

    @Autowired
    public StyleService(StylePersistence stylePersistence) {
        this.stylePersistence = stylePersistence;
    }

    public void patch(String genre, Style style) {
        this.stylePersistence.patch(genre, style);
    }
}
