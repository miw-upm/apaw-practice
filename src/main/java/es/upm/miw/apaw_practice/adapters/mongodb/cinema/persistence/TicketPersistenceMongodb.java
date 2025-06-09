package es.upm.miw.apaw_practice.adapters.mongodb.cinema.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.cinema.daos.TicketRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.TicketEntity;
import es.upm.miw.apaw_practice.domain.models.cinema.Ticket;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.TicketPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("ticketPersistence")
public class TicketPersistenceMongodb implements TicketPersistence {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketPersistenceMongodb(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> findAll() {
        return this.ticketRepository.findAll()
                .stream()
                .map(TicketEntity::toTicket)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Ticket> findById(String id) {
        return this.ticketRepository.findById(id)
                .map(TicketEntity::toTicket);
    }

    @Override
    public Ticket create(Ticket ticket) {
        TicketEntity entity = TicketEntity.fromTicket(ticket);
        return this.ticketRepository.save(entity).toTicket();
    }

    @Override
    public Ticket update(String id, Ticket ticket) {
        TicketEntity entity = this.ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket id: " + id + " not found"));
        entity.updateFromTicket(ticket);
        return this.ticketRepository.save(entity).toTicket();
    }

    @Override
    public void delete(String id) {
        this.ticketRepository.deleteById(id);
    }
}