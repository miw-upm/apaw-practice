package es.upm.miw.apaw_practice.adapters.mongodb.bank.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.bank.daos.InvestmentFundRepository;
import es.upm.miw.apaw_practice.domain.persistence_ports.bank.InvestmentFundPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("investmentFundPersistence")
public class InvestmentFundPersistenceMongodb implements InvestmentFundPersistence {

    private final InvestmentFundRepository investmentFundRepository;

    @Autowired
    public InvestmentFundPersistenceMongodb(InvestmentFundRepository investmentFundRepository) {
        this.investmentFundRepository = investmentFundRepository;
    }

    @Override
    public void delete(String name) {
       this.investmentFundRepository.deleteByName(name);
    }
}
