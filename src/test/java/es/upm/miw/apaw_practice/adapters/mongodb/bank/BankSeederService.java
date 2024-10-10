package es.upm.miw.apaw_practice.adapters.mongodb.bank;

import es.upm.miw.apaw_practice.adapters.mongodb.bank.daos.BankAccountRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.daos.BranchOfficeRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.daos.ClientRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.daos.InvestmentFundRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.entities.BankAccountEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.entities.BranchOfficeEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.entities.ClientEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.entities.InvestmentFundEntity;
import es.upm.miw.apaw_practice.domain.models.bank.InvestmentFund;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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
                new InvestmentFundEntity(new InvestmentFund("FundE", new BigDecimal("0.0"), 3)),
                new InvestmentFundEntity(new InvestmentFund("FundF", new BigDecimal("10000.0"), 1000))
        };
        this.investmentFundRepository.saveAll(Arrays.asList(investmentFunds));
        ClientEntity[] clients = {
                new ClientEntity("11111111A", "Client1", "Client1", 111111111, "email1@example.com", List.of(investmentFunds[0])),
                new ClientEntity("22222222B", "Client2", "Client2", 222222222, "email2@example.com", List.of(investmentFunds[1])),
                new ClientEntity("33333333C", "Client3", "Client3", 333333333, "email3@example.com", List.of(investmentFunds[2], investmentFunds[3])),
                new ClientEntity("44444444D", "Client4", "Client4", 444444444, "email4@example.com", null),
                new ClientEntity("55555555E", "Client5", "Client5", 555555555, "email5@example.com", List.of(investmentFunds[4],investmentFunds[5]))
        };
        this.clientRepository.saveAll(Arrays.asList(clients));
        BankAccountEntity[] bankAccounts = {
                new BankAccountEntity("IBAN1", new BigDecimal("100.0"), LocalDate.of(2023, 12, 1), false, clients[0]),
                new BankAccountEntity("IBAN2", new BigDecimal("40000.0"), LocalDate.of(2002, 4, 6), true, clients[1]),
                new BankAccountEntity("IBAN3", new BigDecimal("3.5"), LocalDate.of(1990, 1, 3), true, clients[2]),
                new BankAccountEntity("IBAN4", new BigDecimal("10.0"), LocalDate.of(2000, 7, 25), false, clients[3]),
                new BankAccountEntity("IBAN5", new BigDecimal("-100.0"), LocalDate.of(2024, 3, 12), true, clients[4])
        };
        this.bankAccountRepository.saveAll(Arrays.asList(bankAccounts));
        BranchOfficeEntity[] branchOffices = {
                new BranchOfficeEntity("Building1", 200, 5, List.of(clients[0],clients[1])),
                new BranchOfficeEntity("Building2", 150, 3, List.of(clients[2],clients[3],clients[4])),
                new BranchOfficeEntity("Building3", 100, 2, List.of(clients[3],clients[4])),
                new BranchOfficeEntity("Building4", 20, 1, List.of(clients[3])),
                new BranchOfficeEntity("Building5", 220, 6, List.of(clients[0],clients[1],clients[2],clients[3]))
        };
        this.branchOfficeRepository.saveAll(Arrays.asList(branchOffices));
    }

    public void deleteAll() {
        this.branchOfficeRepository.deleteAll();
        this.bankAccountRepository.deleteAll();
        this.clientRepository.deleteAll();
        this.investmentFundRepository.deleteAll();
    }

}
