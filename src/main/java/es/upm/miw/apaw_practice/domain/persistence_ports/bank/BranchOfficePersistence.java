package es.upm.miw.apaw_practice.domain.persistence_ports.bank;

import es.upm.miw.apaw_practice.domain.models.bank.BranchOffice;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface BranchOfficePersistence {

    BranchOffice create(BranchOffice branchOffice);

    BigDecimal getAssociatedBalance(String buildingName);
}
