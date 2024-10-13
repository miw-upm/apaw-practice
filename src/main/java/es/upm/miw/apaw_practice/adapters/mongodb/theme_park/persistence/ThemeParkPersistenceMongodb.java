package es.upm.miw.apaw_practice.adapters.mongodb.theme_park.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.daos.ThemeParkRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.entities.ThemeParkEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.theme_park.ThemePark;
import es.upm.miw.apaw_practice.domain.persistence_ports.theme_park.ThemeParkPersistence;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("themeParkPersistence")
public class ThemeParkPersistenceMongodb implements ThemeParkPersistence {

    private final ThemeParkRepository themeParkRepository;

    @Autowired
    public ThemeParkPersistenceMongodb(ThemeParkRepository themeParkRepository) {
        this.themeParkRepository = themeParkRepository;
    }

    @Override
    public Stream<ThemePark> readAll(){
        return this.themeParkRepository.findAll().stream()
                .map(ThemeParkEntity::toThemePark);
    }

    @Override
    public ThemePark readById(String id) {
        return this.themeParkRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Theme Park with id " + id + " not found"))
                .toThemePark();
    }

    @Override
    public ThemePark updateThemePark(ThemePark themePark) {
        ThemeParkEntity themeParkEntity = this.themeParkRepository
                .findById(themePark.getId())
                .orElseThrow(() -> new NotFoundException("Theme Park with id " + themePark.getId() + " not found"));

        BeanUtils.copyProperties(themePark, themeParkEntity, "id");
        return this.themeParkRepository.save(themeParkEntity)
                .toThemePark();
    }
}
