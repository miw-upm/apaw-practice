package es.upm.miw.apaw_practice.domain.services.shopping_center;

import es.upm.miw.apaw_practice.domain.models.shopping_center.Ticket;
import es.upm.miw.apaw_practice.domain.persistence_ports.shopping_center.TicketPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TicketService {

    private final TicketPersistence ticketPersistence;

    @Autowired
    public TicketService(TicketPersistence ticketPersistence) {
        this.ticketPersistence = ticketPersistence;
    }

    public void delete(String id) {
        this.ticketPersistence.delete(id);
    }

    public Ticket updateTotalPrice(String id, BigDecimal totalPrice) {
        return this.ticketPersistence.updateTotalPrice(id, totalPrice);
    }
}
