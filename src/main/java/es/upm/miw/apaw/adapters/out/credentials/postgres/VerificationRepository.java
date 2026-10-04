package es.upm.miw.apaw.adapters.out.credentials.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VerificationRepository extends JpaRepository<VerificationEntity, UUID> {

    List<VerificationEntity> findAllByOrderByCreatedAtAscIdAsc();
}