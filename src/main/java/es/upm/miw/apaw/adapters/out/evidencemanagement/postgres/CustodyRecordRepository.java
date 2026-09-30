package es.upm.miw.apaw.adapters.out.evidencemanagement.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustodyRecordRepository extends JpaRepository<CustodyRecordEntity, UUID> {
}
