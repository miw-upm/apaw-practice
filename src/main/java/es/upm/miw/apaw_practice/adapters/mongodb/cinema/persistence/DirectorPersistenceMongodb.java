package es.upm.miw.apaw_practice.adapters.mongodb.cinema.persistence;

import es.upm.miw.apaw_practice.domain.models.cinema.Director;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.DirectorPersistence;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.daos.DirectorRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.DirectorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("directorPersistence")
public class DirectorPersistenceMongodb implements DirectorPersistence {

    private final DirectorRepository directorRepository;

    @Autowired
    public DirectorPersistenceMongodb(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @Override
    public List<Director> findAll() {
        return this.directorRepository.findAll()
                .stream()
                .map(DirectorPersistenceMapper::toDirector)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Director> findByDni(String dni) {
        return this.directorRepository.findAll().stream()
                .filter(entity -> entity.getDni().equals(dni))
                .findFirst()
                .map(DirectorPersistenceMapper::toDirector);
    }

    @Override
    public Director save(Director director) {
        DirectorEntity entity = this.directorRepository.save(DirectorPersistenceMapper.toEntity(director));
        return DirectorPersistenceMapper.toDirector(entity);
    }
}