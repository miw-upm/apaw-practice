package es.upm.miw.apaw_practice.adapters.mongodb.company.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.company.entities.ExpenseBillEntity;
import es.upm.miw.apaw_practice.domain.models.company.ExpenseBill;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExpenseBillRepository extends MongoRepository<ExpenseBillEntity, String> {



}
