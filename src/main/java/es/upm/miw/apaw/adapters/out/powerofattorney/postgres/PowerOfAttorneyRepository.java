package es.upm.miw.apaw.adapters.out.powerofattorney.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface PowerOfAttorneyRepository extends JpaRepository<PowerOfAttorneyEntity, UUID>, JpaSpecificationExecutor<PowerOfAttorneyEntity> {

    boolean existsByProtocolNumber(String protocolNumber);

    boolean existsByPrincipal_Id(UUID partyId);

    boolean existsByAttorney_Id(UUID partyId);

}
