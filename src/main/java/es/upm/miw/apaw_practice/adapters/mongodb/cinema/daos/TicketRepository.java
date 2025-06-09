package es.upm.miw.apaw_practice.adapters.mongodb.cinema.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.TicketEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface TicketRepository extends MongoRepository<TicketEntity, String> {
    Optional<TicketEntity> findByPurchaseDate(String purchaseDate);
}