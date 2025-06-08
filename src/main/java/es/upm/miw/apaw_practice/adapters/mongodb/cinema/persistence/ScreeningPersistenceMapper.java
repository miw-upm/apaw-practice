package es.upm.miw.apaw_practice.adapters.mongodb.cinema.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.ScreeningEntity;
import es.upm.miw.apaw_practice.domain.models.cinema.Screening;
import java.util.List;
import java.util.stream.Collectors;

public class ScreeningPersistenceMapper {

    public static Screening toScreening(ScreeningEntity entity) {
        return new Screening(
                entity.getScreeningTime(),
                entity.getThreeDFormat(),
                entity.getAvailableSeats(),
                entity.getTickets() == null ? null :
                        entity.getTickets().stream()
                                .map(TicketPersistenceMapper::toTicket)
                                .collect(Collectors.toList())
        );
    }

    public static ScreeningEntity toEntity(Screening screening) {
        return new ScreeningEntity(
                screening.getScreeningTime(),
                screening.getThreeDFormat(),
                screening.getAvailableSeats(),
                screening.getTickets() == null ? null :
                        screening.getTickets().stream()
                                .map(TicketPersistenceMapper::toEntity)
                                .collect(Collectors.toList())
        );
    }
}