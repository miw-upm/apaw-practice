package es.upm.miw.apaw_practice.adapters.mongodb.bank.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.bank.daos.BankAccountRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.daos.BranchOfficeRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.daos.ClientRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.entities.BankAccountEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.entities.BranchOfficeEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.entities.ClientEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.bank.BranchOffice;
import es.upm.miw.apaw_practice.domain.models.bank.Client;
import es.upm.miw.apaw_practice.domain.persistence_ports.bank.BranchOfficePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository("branchOfficePersistence")
public class BranchOfficePersistenceMongodb implements BranchOfficePersistence {

    private final BranchOfficeRepository branchOfficeRepository;
    private final ClientRepository clientRepository;
    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public BranchOfficePersistenceMongodb(
            BranchOfficeRepository branchOfficeRepository,
            ClientRepository clientRepository,
            BankAccountRepository bankAccountRepository
    ) {
        this.branchOfficeRepository = branchOfficeRepository;
        this.clientRepository = clientRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public BranchOffice create(BranchOffice branchOffice) {
        List<String> dniList = branchOffice.getClients().stream()
                .map(Client::getDni)
                .toList();
        List<ClientEntity> clients = this.clientRepository.findByDniIn(dniList);
        return this.branchOfficeRepository
                .save(new BranchOfficeEntity(branchOffice.getBuildingName(), branchOffice.getEmployees(), branchOffice.getAtmNumber(), clients))
                .toBranchOffice();
    }

    @Override
    public BigDecimal getAssociatedBalance(String buildingName) {
        BranchOfficeEntity branchOfficeEntity = this.branchOfficeRepository.findByBuildingName(buildingName)
                .orElseThrow(() -> new NotFoundException("Branch office building name:" + buildingName));
        return this.bankAccountRepository.findAll().stream()
                .filter(bankAccount -> branchOfficeEntity.getClients().contains(bankAccount.getClient()))
                .map(BankAccountEntity::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
