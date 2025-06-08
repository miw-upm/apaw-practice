package es.upm.miw.apaw_practice.domain.services.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Ticket;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.TicketPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketPersistence ticketPersistence;

    @Autowired
    public TicketService(TicketPersistence ticketPersistence) {
        this.ticketPersistence = ticketPersistence;
    }

    public List<Ticket> findAll() {
        return ticketPersistence.findAll();
    }

    public Ticket findByPurchaseDate(String purchaseDate) {
        return ticketPersistence.findByPurchaseDate(purchaseDate)
                .orElseThrow(() -> new RuntimeException("Ticket not found: " + purchaseDate));
    }

    public Ticket create(Ticket ticket) {
        return ticketPersistence.save(ticket);
    }
}