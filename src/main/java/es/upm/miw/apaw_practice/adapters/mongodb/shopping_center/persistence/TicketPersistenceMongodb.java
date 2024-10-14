package es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.daos.TicketRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.entities.TicketEntity;
import es.upm.miw.apaw_practice.domain.models.shopping_center.Ticket;
import es.upm.miw.apaw_practice.domain.persistence_ports.shopping_center.TicketPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("ticketPersistence")
public class TicketPersistenceMongodb implements TicketPersistence {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketPersistenceMongodb(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Stream<Ticket> readAll() {
        return this.ticketRepository.findAll().stream().map(TicketEntity::toTicket);
    }

    @Override
    public void delete(String id) {
        this.ticketRepository.deleteById(id);
    }
}
