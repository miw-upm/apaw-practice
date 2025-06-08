package es.upm.miw.apaw_practice.domain.services.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Director;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.DirectorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorService {

    private final DirectorPersistence directorPersistence;

    @Autowired
    public DirectorService(DirectorPersistence directorPersistence) {
        this.directorPersistence = directorPersistence;
    }

    public List<Director> findAll() {
        return directorPersistence.findAll();
    }

    public Director findByDni(String dni) {
        return directorPersistence.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Director not found: " + dni));
    }

    public Director create(Director director) {
        return directorPersistence.save(director);
    }
}