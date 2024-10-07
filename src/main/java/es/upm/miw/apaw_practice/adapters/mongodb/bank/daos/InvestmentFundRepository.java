package es.upm.miw.apaw_practice.adapters.mongodb.bank.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.bank.entities.InvestmentFundEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InvestmentFundRepository extends MongoRepository<InvestmentFundEntity, String> {
}
