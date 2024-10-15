package es.upm.miw.apaw_practice.adapters.mongodb.martial_art.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.daos.StyleRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.entities.StyleEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.martial_art.Style;
import es.upm.miw.apaw_practice.domain.persistence_ports.martial_art.StylePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.stream.Stream;


@Repository("stylePersistence")

public class StylePersistenceMongodb implements StylePersistence {

    private final StyleRepository styleRepository;

    @Autowired
    public StylePersistenceMongodb(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public Stream<Style> readAll() {
        return null;
      /*
        return this.styleRepository
                .findAll().stream()
                .map(StyleRepository::toStyle);*/
    }

    @Override
    public Style create(Style style) {
        return this.styleRepository
                .save(new StyleEntity(
                        style.getName(),
                        style.getDescription(),
                        style.getOriginCountry(),
                        style.getPopularity()
                )).toStyle();
    }

    @Override
    public Style update(String name, Style style) {
        StyleEntity styleRepository = this.styleRepository
                .findByName(name)
                .orElseThrow(() -> new NotFoundException("Style name: " + name));
        styleRepository.fromStyle(style);
        return this.styleRepository
                .save(styleRepository)
                .toStyle();
    }

    @Override
    public Style read(String name) {
        return this.styleRepository
                .findByName(name)
                .orElseThrow(() -> new NotFoundException("Style name: " + name))
                .toStyle();
    }

    @Override
    public boolean existsByName(String name) {
        return this.styleRepository
                .findByName(name)
                .isPresent();
    }

    @Override
    public void delete(String name) {
        this.styleRepository.deleteByname(name);
    }
}
