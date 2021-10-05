package es.upm.miw.apaw_practice.adapters.mongodb.hotel.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos.DirectorRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.DirectorEntity;
import es.upm.miw.apaw_practice.domain.persistence_ports.hotel.DirectorPersistence;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository("directorPersistence")
public class DirectorPersistenceMongodb implements DirectorPersistence {

    private final DirectorRepository directorRepository;

    public DirectorPersistenceMongodb(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @Override
    public List<String> getEmails() {
        return this.directorRepository.findAll().stream()
                .map(DirectorEntity::getEmail)
                .collect(Collectors.toList());
    }
}
