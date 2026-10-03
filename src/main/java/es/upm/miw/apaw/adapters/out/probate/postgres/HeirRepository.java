package es.upm.miw.apaw.adapters.out.probate.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HeirRepository extends JpaRepository<HeirEntity, UUID> {
    boolean existsByNationalId(String nationalId);
}