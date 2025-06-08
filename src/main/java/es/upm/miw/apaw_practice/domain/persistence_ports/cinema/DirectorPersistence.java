package es.upm.miw.apaw_practice.domain.persistence_ports.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Director;

import java.util.List;
import java.util.Optional;

public interface DirectorPersistence {
    List<Director> findAll();
    Optional<Director> findByDni(String dni);
    Director save(Director director);
    // Agrega aquí otros métodos según tus necesidades
}