package es.upm.miw.apaw_practice.domain.persistence_ports.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketPersistence {
    List<Ticket> findAll();
    Optional<Ticket> findByPurchaseDate(String purchaseDate);
    Ticket save(Ticket ticket);
    // Agrega aquí otros métodos según tus necesidades
}