package es.upm.miw.apaw.adapters.out.judicialcourt.postgres;

import es.upm.miw.apaw.domain.model.judicialcourt.LawyerCourtStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface JudicialCourtRepository extends JpaRepository<JudicialCourtEntity, UUID> {
    boolean existsByTypeId(UUID id);

    @Query("SELECT new es.upm.miw.apaw.domain.model.judicialcourt.LawyerCourtStat(l, COUNT(DISTINCT jc.id)) " +
            "FROM JudicialCourtEntity jc JOIN jc.lawyerIds l " +
            "GROUP BY l " +
            "ORDER BY COUNT(DISTINCT jc.id) DESC")
    List<LawyerCourtStat> findLawyerCourtStats();
}
