package es.upm.miw.apaw.adapters.out.secondlawchance.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DebtRepository extends JpaRepository<DebtEntity, UUID> {
    List<DebtEntity> findAllByOrderByIssueDateAscCreditorNameAscIdAsc();

    boolean existsByContractNumber(String contractNumber);
}
