package es.upm.miw.apaw.adapters.mongodb.warehouse.daos;

import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.OrderDetailEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface OrderDetailRepository extends MongoRepository<OrderDetailEntity, UUID> {

}
