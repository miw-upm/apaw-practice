package es.upm.miw.apaw.adapters.out.appointment.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, UUID>,
        JpaSpecificationExecutor<AppointmentEntity> {
    boolean existsByLocationId(UUID locationId);
}
