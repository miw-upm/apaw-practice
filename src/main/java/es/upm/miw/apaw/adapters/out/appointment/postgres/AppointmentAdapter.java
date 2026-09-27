package es.upm.miw.apaw.adapters.out.appointment.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.appointment.Appointment;
import es.upm.miw.apaw.domain.model.appointment.AppointmentCityReport;
import es.upm.miw.apaw.domain.model.appointment.AppointmentFindCriteria;
import es.upm.miw.apaw.domain.ports.out.appointment.AppointmentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AppointmentAdapter implements AppointmentGateway {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentLocationRepository appointmentLocationRepository;

    @Override
    public Appointment create(Appointment appointment) {
        AppointmentEntity entity = new AppointmentEntity(appointment);
        if (appointment.getLocation() != null) {
            entity.setLocation(this.appointmentLocationRepository
                    .getReferenceById(appointment.getLocation().getId()));
        }
        this.appointmentRepository.save(entity);
        return appointment;
    }

    @Override
    public List<AppointmentCityReport> findCityReport() {
        return this.appointmentRepository.findCityReport();
    }

    @Override
    public List<Appointment> find(AppointmentFindCriteria criteria) {
        return this.appointmentRepository
                .findAll(this.buildSpecification(criteria), Sort.by("scheduledDate"))
                .stream()
                .map(this::toDomainSummary)
                .toList();
    }

    private Appointment toDomainSummary(AppointmentEntity entity) {
        Appointment appointment = new Appointment();
        BeanUtils.copyProperties(entity, appointment, "location", "clientId");
        appointment.setClient(UserSnapshot.builder().id(entity.getClientId()).build());
        return appointment;
    }

    private Specification<AppointmentEntity> buildSpecification(AppointmentFindCriteria criteria) {
        Specification<AppointmentEntity> spec = (root, query, builder) -> builder.conjunction();
        if (criteria.hasStatus()) {
            spec = spec.and((root, query, builder) ->
                    builder.equal(root.get("status"), criteria.getStatus()));
        }
        if (criteria.hasUpcoming()) {
            spec = spec.and((root, query, builder) -> criteria.getUpcoming()
                    ? builder.greaterThan(root.get("scheduledDate"), LocalDateTime.now())
                    : builder.lessThanOrEqualTo(root.get("scheduledDate"), LocalDateTime.now()));
        }
        if (criteria.hasCity()) {
            spec = spec.and((root, query, builder) -> {
                query.distinct(true);
                return builder.equal(root.join("location").get("city"), criteria.getCity());
            });
        }
        return spec;
    }
}
