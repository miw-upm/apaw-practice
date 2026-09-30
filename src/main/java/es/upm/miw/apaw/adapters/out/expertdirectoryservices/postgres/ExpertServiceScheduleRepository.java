package es.upm.miw.apaw.adapters.out.expertdirectoryservices.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExpertServiceScheduleRepository extends JpaRepository<ExpertServiceScheduleEntity, UUID> {
}