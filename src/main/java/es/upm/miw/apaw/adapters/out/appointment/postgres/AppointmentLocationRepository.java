package es.upm.miw.apaw.adapters.out.appointment.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AppointmentLocationRepository extends JpaRepository<AppointmentLocationEntity, UUID> {
    List<AppointmentLocationEntity> findAllByOrderByNameAsc();
    boolean existsByName(String name);
}
