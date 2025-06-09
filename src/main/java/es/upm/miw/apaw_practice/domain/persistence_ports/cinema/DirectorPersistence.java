package es.upm.miw.apaw_practice.domain.persistence_ports.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Director;

import java.util.List;
import java.util.Optional;

public interface DirectorPersistence {
    List<Director> findAll();
    Optional<Director> findByDni(String dni);
    Optional<Director> findById(String id); // <-- Añade esto
    Director create(Director director);
    Director update(String dni, Director director);
    void delete(String dni);
}