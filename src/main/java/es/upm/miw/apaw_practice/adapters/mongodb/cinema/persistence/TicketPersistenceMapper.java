package es.upm.miw.apaw_practice.adapters.mongodb.cinema.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.TicketEntity;
import es.upm.miw.apaw_practice.domain.models.cinema.Ticket;

public class TicketPersistenceMapper {

    public static Ticket toTicket(TicketEntity entity) {
        return new Ticket(
                entity.getTicketPrice(),
                entity.getPurchaseDate(),
                entity.getVip(),
                entity.getTotalPrice()
        );
    }

    public static TicketEntity toEntity(Ticket ticket) {
        return new TicketEntity(
                ticket.getTicketPrice(),
                ticket.getPurchaseDate(),
                ticket.getVip(),
                ticket.getTotalPrice()
        );
    }
}