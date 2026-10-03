package es.upm.miw.apaw.adapters.out.probate.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HeirRepository extends JpaRepository<HeirEntity, UUID> {
    List<HeirEntity> findAllByOrderByNationalIdAsc();

    boolean existsByNationalId(String nationalId);
}