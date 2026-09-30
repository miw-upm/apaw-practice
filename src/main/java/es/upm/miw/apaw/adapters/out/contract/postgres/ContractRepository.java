package es.upm.miw.apaw.adapters.out.contract.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContractRepository extends JpaRepository<ContractEntity, UUID> {
    boolean existsByClauses_Id(UUID id);
}
