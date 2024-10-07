package es.upm.miw.apaw_practice.adapters.mongodb.bank;

import es.upm.miw.apaw_practice.adapters.mongodb.bank.daos.BankAccountRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.daos.BranchOfficeRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.daos.ClientRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.daos.InvestmentFundRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.entities.InvestmentFundEntity;
import es.upm.miw.apaw_practice.domain.models.bank.InvestmentFund;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
public class BankSeederService {

    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private BranchOfficeRepository branchOfficeRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private InvestmentFundRepository investmentFundRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Bank Initial Load -----------");
        InvestmentFundEntity[] investmentFunds = {
                new InvestmentFundEntity(new InvestmentFund("FundA", new BigDecimal("1000.0"), 32)),
                new InvestmentFundEntity(new InvestmentFund("FundB", new BigDecimal("2000.0"), 2)),
                new InvestmentFundEntity(new InvestmentFund("FundC", new BigDecimal("3000.0"), 10)),
                new InvestmentFundEntity(new InvestmentFund("FundD", new BigDecimal("1500.0"), 300)),
                new InvestmentFundEntity(new InvestmentFund("FundE", new BigDecimal("0.0"), 3))

        };
        this.investmentFundRepository.saveAll(Arrays.asList(investmentFunds));
    }

}
