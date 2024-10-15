package es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.daos.TicketRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.entities.TicketEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.shopping_center.Ticket;
import es.upm.miw.apaw_practice.domain.persistence_ports.shopping_center.TicketPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
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

    @Override
    public Ticket updateTotalPrice(String id, BigDecimal totalPrice) {
        TicketEntity ticketEntity = this.ticketRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Ticket id:" + id));
        ticketEntity.setTotalPrice(totalPrice);
        this.ticketRepository.save(ticketEntity);
        return ticketEntity.toTicket();
    }
}
