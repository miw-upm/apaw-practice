package es.upm.miw.apaw_practice.adapters.rest.cinema.dto;

import es.upm.miw.apaw_practice.domain.models.cinema.Ticket;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TicketDtoMapper {
    private TicketDtoMapper() {}

    public static TicketDto toDto(Ticket ticket) {
        return new TicketDto(
                ticket.getTicketPrice(),
                ticket.getPurchaseDate().toString(),
                ticket.getVip(),
                ticket.getTotalPrice()
        );
    }

    public static Ticket toDomain(TicketDto dto) {
        return new Ticket(
                dto.getTicketPrice(),
                LocalDateTime.parse(dto.getPurchaseDate()),
                dto.getVip(),
                dto.getTotalPrice()
        );
    }
}