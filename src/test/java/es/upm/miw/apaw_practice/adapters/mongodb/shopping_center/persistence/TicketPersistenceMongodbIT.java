package es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.shopping_center.Ticket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class TicketPersistenceMongodbIT {

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
        List<Ticket> oldTickets = this.ticketPersistenceMongodb.readAll().toList();
        int oldSize = oldTickets.size();
        assertNotNull(oldTickets.get(0).getId());
        this.ticketPersistenceMongodb.delete(oldTickets.get(0).getId());
        List<Ticket> newTickets = this.ticketPersistenceMongodb.readAll().toList();
        int newSize = newTickets.size();
        assertNotEquals(oldSize, newSize);
    }

    @Test
    void testUpdate() {
        Optional<Ticket> ticket = this.ticketPersistenceMongodb.readAll().findFirst();
        assertTrue(ticket.isPresent());
        BigDecimal newTotalPrice = new BigDecimal("99.99");
        this.ticketPersistenceMongodb.updateTotalPrice(ticket.get().getId(), newTotalPrice);
        Optional<Ticket> newTicket = this.ticketPersistenceMongodb.readAll().findFirst();
        assertTrue(newTicket.isPresent());
        assertEquals(new BigDecimal("99.99"), newTicket.get().getTotalPrice());
    }

    @Test
    void testSumTotalPrice() {
        BigDecimal sumTotalPrice = this.ticketPersistenceMongodb.sumTotalPrice("food");
        assertEquals(new BigDecimal("286.19"), sumTotalPrice);
    }

    @Test
    void testSumTotalPriceNoTickets() {
        BigDecimal sumTotalPrice = this.ticketPersistenceMongodb.sumTotalPrice("games");
        assertEquals(new BigDecimal("0"), sumTotalPrice);
    }
}
