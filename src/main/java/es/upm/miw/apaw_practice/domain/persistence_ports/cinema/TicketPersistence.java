package es.upm.miw.apaw_practice.domain.persistence_ports.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketPersistence {
    List<Ticket> findAll();
    Optional<Ticket> findById(String id);
    Ticket create(Ticket ticket);
    Ticket update(String id, Ticket ticket);
    void delete(String id);
}