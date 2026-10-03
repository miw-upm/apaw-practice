package es.upm.miw.apaw.adapters.out.appointment.postgres;

import es.upm.miw.apaw.domain.model.appointment.AppointmentCityReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, UUID>,
        JpaSpecificationExecutor<AppointmentEntity> {

    boolean existsByLocationId(UUID locationId);

    @Query("""
            select new es.upm.miw.apaw.domain.model.appointment.AppointmentCityReport(
                a.clientId, loc.city, count(a)
            )
            from AppointmentEntity a
            join a.location loc
            group by a.clientId, loc.city
            order by count(a) desc
            """)
    List<AppointmentCityReport> findCityReport();
}
