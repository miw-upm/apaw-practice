package es.upm.miw.apaw_practice.adapters.mongodb.bank.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.bank.daos.BranchOfficeRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.daos.ClientRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.entities.BranchOfficeEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.entities.ClientEntity;
import es.upm.miw.apaw_practice.domain.models.bank.BranchOffice;
import es.upm.miw.apaw_practice.domain.models.bank.Client;
import es.upm.miw.apaw_practice.domain.persistence_ports.bank.BranchOfficePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("branchOfficePersistence")
public class BranchOfficePersistenceMongodb implements BranchOfficePersistence {

    private final BranchOfficeRepository branchOfficeRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public BranchOfficePersistenceMongodb(BranchOfficeRepository branchOfficeRepository, ClientRepository clientRepository) {
        this.branchOfficeRepository = branchOfficeRepository;
        this.clientRepository = clientRepository;
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

}
