package es.upm.miw.apaw.adapters.out.stucktaskdetector.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StuckTaskAlertRepository extends JpaRepository<StuckTaskAlertEntity, UUID> {
    boolean existsByReference(String reference);
}
