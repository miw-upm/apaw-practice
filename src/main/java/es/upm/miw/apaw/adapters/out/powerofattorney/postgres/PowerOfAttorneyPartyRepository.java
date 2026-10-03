package es.upm.miw.apaw.adapters.out.powerofattorney.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PowerOfAttorneyPartyRepository extends JpaRepository<PowerOfAttorneyPartyEntity, UUID> {
}
