package es.upm.miw.apaw_practice.domain.persistence_ports.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Screening;

import java.util.List;
import java.util.Optional;

public interface ScreeningPersistence {
    List<Screening> findAll();
    Optional<Screening> findById(String id);
    Screening create(Screening screening);
    Screening update(String id, Screening screening);
    void delete(String id);
}