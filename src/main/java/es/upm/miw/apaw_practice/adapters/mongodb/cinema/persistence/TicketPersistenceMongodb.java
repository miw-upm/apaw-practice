package es.upm.miw.apaw_practice.adapters.mongodb.cinema.persistence;

import es.upm.miw.apaw_practice.domain.models.cinema.Ticket;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.TicketPersistence;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.daos.TicketRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.TicketEntity;
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
                .map(TicketPersistenceMapper::toTicket)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Ticket> findByPurchaseDate(String purchaseDate) {
        return this.ticketRepository.findAll().stream()
                .filter(entity -> entity.getPurchaseDate().equals(purchaseDate))
                .findFirst()
                .map(TicketPersistenceMapper::toTicket);
    }

    @Override
    public Ticket save(Ticket ticket) {
        TicketEntity entity = this.ticketRepository.save(TicketPersistenceMapper.toEntity(ticket));
        return TicketPersistenceMapper.toTicket(entity);
    }
}