package es.upm.miw.apaw_practice.adapters.mongodb.martial_art.persistence;

import es.upm.miw.apaw_practice.domain.models.martial_art.MartialArtsClass;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.daos.TechniqueRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.daos.MartialArtsClassRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.entities.TechniqueEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.entities.MartialArtsClassEntity;
import es.upm.miw.apaw_practice.domain.persistence_ports.martial_art.MartialArtsClassPersistence;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;
@Repository("martialArtsClassPersistence")
public class MartialArtsClassPersistenceMongodb implements MartialArtsClassPersistence{

    private final MartialArtsClassRepository martialArtsClassRepository;
    private final TechniqueRepository techniqueRepository;

    @Autowired
    public MartialArtsClassPersistenceMongodb(
            MartialArtsClassRepository martialArtsClassRepository,
            TechniqueRepository techniqueRepository
    ) {
        this.martialArtsClassRepository = martialArtsClassRepository;
        this.techniqueRepository = techniqueRepository;
    }

    @Override
    public Stream<MartialArtsClass> readAll() {
        return this.martialArtsClassRepository
                .findAll().stream()
                .map(MartialArtsClassEntity::toMartialArtsClass);
    }

    @Override
    public MartialArtsClass create(MartialArtsClass martialArtsClass) {
        List<TechniqueEntity> techniqueEntities = this.techniqueRepository
                .findAll().stream()
                .toList();
        return this.martialArtsClassRepository
                .save(new MartialArtsClassEntity(martialArtsClass.getName(), martialArtsClass.getStartDate(), martialArtsClass.getAcademy(), techniqueEntities))
                .toMartialArtsClass();
    }

    @Override
    public MartialArtsClass update(String name, MartialArtsClass martialArtsClass) {
        MartialArtsClassEntity martialArtsClassEntity = this.martialArtsClassRepository
                .findByName(name)
                .orElseThrow(() -> new NotFoundException("MartialArtsClass name: " + name));
       // martialArtsClassEntity.(hotel);
        return this.martialArtsClassRepository
                .save(martialArtsClassEntity)
                .toMartialArtsClass();
    }

    @Override
    public MartialArtsClass read(String name) {
        return this.martialArtsClassRepository
                .findByName(name)
                .orElseThrow(() -> new NotFoundException("MartialArtsClass name: " + name))
                .toMartialArtsClass();
    }

    @Override
    public boolean existsByName(String name) {
        return this.martialArtsClassRepository
                .findByName(name)
                .isPresent();
    }

    @Override
    public void delete(String name) {
        this.martialArtsClassRepository.deleteByname(name);
    }
}
