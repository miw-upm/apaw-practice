package es.upm.miw.apaw.domain.ports.out.contract;

import es.upm.miw.apaw.domain.model.contract.Contract;
import es.upm.miw.apaw.domain.model.contract.ContractExpirationReport;
import es.upm.miw.apaw.domain.model.contract.ContractFindCriteria;

import java.time.LocalDate;
import java.util.List;

public interface ContractGateway {
    Contract create(Contract contract);

    List<ContractExpirationReport> findExpirationReport(LocalDate today, LocalDate limitDate);

    List<Contract> find(ContractFindCriteria criteria);
}
