package es.upm.miw.apaw_practice.domain.persistence_ports.shopping_center;

import es.upm.miw.apaw_practice.domain.models.shopping_center.Ticket;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.stream.Stream;

@Repository
public interface TicketPersistence {

    Stream<Ticket> readAll();

    void delete(String id);

    Ticket updateTotalPrice(String id, BigDecimal totalPrice);

    BigDecimal sumTotalPrice(String mainService);
}
