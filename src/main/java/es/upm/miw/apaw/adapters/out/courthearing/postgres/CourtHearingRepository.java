package es.upm.miw.apaw.adapters.out.courthearing.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourtHearingRepository extends JpaRepository<CourtHearingEntity, UUID> {
    boolean existsByCourtId(UUID courtId);
}