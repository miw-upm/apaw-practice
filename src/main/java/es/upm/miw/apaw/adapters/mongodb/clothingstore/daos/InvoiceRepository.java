package es.upm.miw.apaw.adapters.mongodb.clothingstore.daos;

import es.upm.miw.apaw.adapters.mongodb.clothingstore.entities.InvoiceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InvoiceRepository extends MongoRepository<InvoiceEntity, String> {
}
