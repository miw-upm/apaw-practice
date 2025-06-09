package es.upm.miw.apaw_practice.adapters.mongodb.cinema.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.cinema.daos.DirectorRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.DirectorEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.cinema.Director;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.DirectorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
                .map(DirectorEntity::toDirector)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Director> findByDni(String dni) {
        return this.directorRepository.findByDni(dni)
                .map(DirectorEntity::toDirector);
    }

    @Override
    public Optional<Director> findById(String id) {
        return this.directorRepository.findById(id)
                .map(DirectorEntity::toDirector);
    }

    @Override
    public Director create(Director director) {
        DirectorEntity entity = DirectorEntity.fromDirector(director);
        return this.directorRepository.save(entity).toDirector();
    }

    @Override
    public Director update(String dni, Director director) {
        DirectorEntity entity = this.directorRepository.findByDni(dni)
                .orElseThrow(() -> new NotFoundException("Director dni: " + dni));
        entity.setName(director.getName());
        // Conversión segura de birthdate de String a LocalDate
        if (director.getBirthdate() != null && !director.getBirthdate().isEmpty()) {
            try {
                entity.setBirthdate(LocalDate.parse(director.getBirthdate()));
            } catch (DateTimeParseException e) {
                entity.setBirthdate(null);
            }
        } else {
            entity.setBirthdate(null);
        }
        entity.setStyle(director.getStyle());
        return this.directorRepository.save(entity).toDirector();
    }

    @Override
    public void delete(String dni) {
        DirectorEntity entity = this.directorRepository.findByDni(dni)
                .orElseThrow(() -> new NotFoundException("Director dni: " + dni));
        this.directorRepository.delete(entity);
    }
}