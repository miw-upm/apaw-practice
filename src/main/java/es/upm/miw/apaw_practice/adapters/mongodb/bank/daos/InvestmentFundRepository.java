package es.upm.miw.apaw_practice.adapters.mongodb.bank.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.bank.entities.InvestmentFundEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface InvestmentFundRepository extends MongoRepository<InvestmentFundEntity, String> {

    Optional<InvestmentFundEntity> deleteByName(String name);

    Optional<InvestmentFundEntity> findByName(String name);
}
