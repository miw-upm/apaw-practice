package es.upm.miw.apaw.domain.ports.out.secondlawchance;

import es.upm.miw.apaw.domain.model.secondlawchance.Debt;
import es.upm.miw.apaw.domain.model.secondlawchance.SharedDebtReport;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DebtGateway {
    Debt create(Debt debt);

    List<Debt> findAll();

    Optional<Debt> read(UUID id);

    Debt update(Debt debt);

    void delete(UUID id);

    boolean isReferenced(UUID id);

    boolean existsByContractNumber(String contractNumber);

    List<SharedDebtReport> findSharedReport();
}
