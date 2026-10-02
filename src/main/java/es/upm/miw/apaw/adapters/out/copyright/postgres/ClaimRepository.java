package es.upm.miw.apaw.adapters.out.copyright.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClaimRepository extends JpaRepository<ClaimEntity, UUID> {
    boolean existsByNumber(String number);
}
