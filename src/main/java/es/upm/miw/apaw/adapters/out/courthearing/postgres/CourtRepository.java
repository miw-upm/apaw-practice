package es.upm.miw.apaw.adapters.out.courthearing.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CourtRepository extends JpaRepository<CourtEntity, UUID> {
    boolean existsByName(String name);

    boolean existsByPhone(String phone);

    List<CourtEntity> findAllByOrderByNameAsc();
}