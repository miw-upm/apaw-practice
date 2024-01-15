package es.upm.miw.apaw_practice.adapters.mongodb.ticket_bus.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.ticket_bus.entities.TicketEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository("ticketTRepository")

public interface TicketRepository extends MongoRepository<TicketEntity,String> {
    void deleteByArrive(String arrive);

    @Query("{ 'price' : { $gt : ?0 } }")
    List<TicketEntity> findTicketsByPriceGreaterThan(BigDecimal price);
}
