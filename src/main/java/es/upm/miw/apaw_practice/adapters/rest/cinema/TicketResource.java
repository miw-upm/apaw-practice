package es.upm.miw.apaw_practice.adapters.rest.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Ticket;
import es.upm.miw.apaw_practice.domain.services.cinema.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("ticketResourceCinema")
@RequestMapping(TicketResource.TICKETS)
public class TicketResource {
    public static final String TICKETS = "/cinema/tickets";

    private final TicketService ticketService;

    @Autowired
    public TicketResource(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Ticket> findAll() {
        return this.ticketService.findAll();
    }

    @GetMapping("/{id}")
    public Ticket findById(@PathVariable String id) {
        return this.ticketService.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket id: " + id + " not found"));
    }

    @PostMapping
    public Ticket create(@RequestBody Ticket ticket) {
        return this.ticketService.create(ticket);
    }

    @PutMapping("/{id}")
    public Ticket update(@PathVariable String id, @RequestBody Ticket ticket) {
        return this.ticketService.update(id, ticket);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.ticketService.delete(id);
    }
}