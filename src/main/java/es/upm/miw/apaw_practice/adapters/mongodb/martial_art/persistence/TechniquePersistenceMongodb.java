package es.upm.miw.apaw_practice.adapters.mongodb.martial_art.persistence;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.daos.InstructorRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.daos.StyleRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.daos.TechniqueRepository;
import es.upm.miw.apaw_practice.domain.models.martial_art.Technique;
import es.upm.miw.apaw_practice.domain.persistence_ports.martial_art.TechniquePersistence;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.entities.TechniqueEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

public class TechniquePersistenceMongodb implements TechniquePersistence{

    private final TechniqueRepository techniqueRepository;

    @Autowired
    public TechniquePersistenceMongodb(
            TechniqueRepository techniqueRepository
            ) {
        this.techniqueRepository = techniqueRepository;
    }
    @Override
    public Stream<Technique> readAll() {
        return this.techniqueRepository.findAll().stream()
                .map(TechniqueEntity::toTechnique);
    }
    @Override
    public Technique create(Technique technique) {
        TechniqueEntity techniqueEntity = new TechniqueEntity(
                technique.getName(),
                technique.getDuration(),
                technique.getIsAdvanced(),
                technique.getDifficulty()
        );
        return this.techniqueRepository
                .save(techniqueEntity)
                .toTechnique();
    }
    @Override
    public Technique read(String name) {
        return this.techniqueRepository
                .findByName(name)
                .orElseThrow(() -> new NotFoundException("Technique name: " + name))
                .toTechnique();
    }

    @Override
    public void delete(String name) {
        TechniqueEntity techniqueEntity = this.techniqueRepository
                .findByName(name)
                .orElseThrow(() -> new NotFoundException("Technique name: " + name));
        this.techniqueRepository.delete(techniqueEntity);
    }
}
