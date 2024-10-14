package es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.shopping_center.Ticket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class TicketPersistenceMongodbIT {

    @Autowired
    private TicketPersistenceMongodb ticketPersistenceMongodb;

    @Test
    void testReadAll() {
        Optional<Ticket> ticket = this.ticketPersistenceMongodb.readAll().findFirst();
        assertTrue(ticket.isPresent());
        assertNotNull(ticket.get().getId());
    }

    @Test
    void testDelete() {
        List<Ticket> oldTickets = this.ticketPersistenceMongodb.readAll().collect(Collectors.toList());
        int oldSize = oldTickets.size();
        assertNotNull(oldTickets.get(0).getId());
        this.ticketPersistenceMongodb.delete(oldTickets.get(0).getId());
        List<Ticket> newTickets = this.ticketPersistenceMongodb.readAll().collect(Collectors.toList());
        int newSize = newTickets.size();
        assertNotEquals(oldSize, newSize);
    }
}
