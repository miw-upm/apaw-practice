package es.upm.miw.apaw_practice.domain.services.bank;

import es.upm.miw.apaw_practice.domain.models.bank.BranchOffice;
import es.upm.miw.apaw_practice.domain.persistence_ports.bank.BranchOfficePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchOfficeService {

    private final BranchOfficePersistence branchOfficePersistence;

    @Autowired
    public BranchOfficeService(BranchOfficePersistence branchOfficePersistence) {
        this.branchOfficePersistence = branchOfficePersistence;
    }

    public BranchOffice create(BranchOffice branchOffice) {
        return this.branchOfficePersistence.create(branchOffice);
    }

}
