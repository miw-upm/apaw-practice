package es.upm.miw.apaw_practice.adapters.rest.shopping_center;

import es.upm.miw.apaw_practice.domain.models.shopping_center.Ticket;
import es.upm.miw.apaw_practice.domain.services.shopping_center.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.stream.Stream;

@RestController
@RequestMapping(TicketResource.TICKETS)
public class TicketResource {
    static final String TICKETS = "/shopping_center/tags";

    static final String ID_ID = "/{id}";

    private final TicketService ticketService;

    @Autowired
    public TicketResource(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public Stream<Ticket> findAllTickets() {
        return this.ticketService.findAllTickets();
    }

    @DeleteMapping(ID_ID)
    public void deleteTicket(@PathVariable String id) {
        this.ticketService.delete(id);
    }

    @PatchMapping(ID_ID)
    public void updateTotalPrice(@PathVariable String id, @RequestBody BigDecimal totalPrice) {
        this.ticketService.updateTotalPrice(id, totalPrice);
    }
}
