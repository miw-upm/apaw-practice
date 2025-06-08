package es.upm.miw.apaw_practice.domain.persistence_ports.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Screening;

import java.util.List;
import java.util.Optional;

public interface ScreeningPersistence {
    List<Screening> findAll();
    Optional<Screening> findByScreeningTime(String screeningTime);
    Screening save(Screening screening);
    // Agrega aquí otros métodos según tus necesidades
}