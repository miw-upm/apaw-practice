package es.upm.miw.apaw_practice.domain.services.martial_art;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.martial_art.Style;
import es.upm.miw.apaw_practice.domain.persistence_ports.martial_art.StylePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StyleService {
    private final StylePersistence stylePersistence;

    @Autowired
    public StyleService(StylePersistence stylePersistence) {
        this.stylePersistence = stylePersistence;
    }

    public Style create(Style style) {
        this.assertNameNotExists(style.getName());
        return this.stylePersistence.create(style);
    }

    private void assertNameNotExists(String name) {
        if (this.stylePersistence.existsByName(name)) {
            throw new ConflictException("name exists: " + name);
        }
    }

    public Style read(String name) {
        return this.stylePersistence.read(name);
    }

    public void delete(String name) {
        this.stylePersistence.delete(name);
    }

    public Style update(String name, Style style) {
        return this.stylePersistence.update(name, style);
    }
}
