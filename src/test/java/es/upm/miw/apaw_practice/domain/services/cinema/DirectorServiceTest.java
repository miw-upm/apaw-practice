package es.upm.miw.apaw_practice.domain.services.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Director;
import es.upm.miw.apaw_practice.domain.models.cinema.Ticket;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectorServiceTest {

    @Test
    void testDirectorBuilderAndTicketPrice() {
        // Director.Builder SIN argumentos
        Director director = new Director.Builder()
                .id("dir-1")
                .name("Christopher Nolan")
                .birthdate("1970-07-30")
                .build();

        assertEquals("Christopher Nolan", director.getName());

        // Ticket: price se pasa por el constructor, NO por setter
        Ticket ticket1 = new Ticket("ticket-1", "screening-1", new BigDecimal("12.50"), "A01");
        Ticket ticket2 = new Ticket("ticket-2", "screening-2", new BigDecimal("10.00"), "B02");

        assertEquals(new BigDecimal("12.50"), ticket1.getPrice());
        assertEquals(new BigDecimal("10.00"), ticket2.getPrice());
    }
}