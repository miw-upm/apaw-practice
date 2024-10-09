package es.upm.miw.apaw_practice.domain.services.bank;

import es.upm.miw.apaw_practice.domain.persistence_ports.bank.InvestmentFundPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvestmentFundService {

    private final InvestmentFundPersistence investmentFundPersistence;

    @Autowired
    public InvestmentFundService(InvestmentFundPersistence investmentFundPersistence) {
        this.investmentFundPersistence = investmentFundPersistence;
    }

    public void delete(String name) {
        this.investmentFundPersistence.delete(name);
    }

}
