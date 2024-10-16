package es.upm.miw.apaw_practice.domain.services.shopping_center;

import es.upm.miw.apaw_practice.domain.models.shopping_center.Ticket;
import es.upm.miw.apaw_practice.domain.persistence_ports.shopping_center.TicketPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.stream.Stream;

@Service
public class TicketService {

    private final TicketPersistence ticketPersistence;

    @Autowired
    public TicketService(TicketPersistence ticketPersistence) {
        this.ticketPersistence = ticketPersistence;
    }

    public Stream<Ticket> findAllTickets() {
        return this.ticketPersistence.readAll();
    }

    public void delete(String id) {
        this.ticketPersistence.delete(id);
    }

    public Ticket updateTotalPrice(String id, BigDecimal totalPrice) {
        return this.ticketPersistence.updateTotalPrice(id, totalPrice);
    }

    public BigDecimal sumTotalPrice(String mainService) {
        return this.ticketPersistence.sumTotalPrice(mainService);
    }
}
