package es.upm.miw.apaw.adapters.out.appointment.postgres;

import es.upm.miw.apaw.domain.model.appointment.AppointmentLocation;
import es.upm.miw.apaw.domain.ports.out.appointment.AppointmentLocationGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AppointmentLocationAdapter implements AppointmentLocationGateway {

    private final AppointmentLocationRepository appointmentLocationRepository;
    private final AppointmentRepository appointmentRepository;

    @Override
    public AppointmentLocation create(AppointmentLocation location) {
        return this.appointmentLocationRepository
                .save(new AppointmentLocationEntity(location))
                .toDomain();
    }

    @Override
    public Optional<AppointmentLocation> read(UUID id) {
        return this.appointmentLocationRepository.findById(id)
                .map(AppointmentLocationEntity::toDomain);
    }

    @Override
    public AppointmentLocation update(AppointmentLocation location) {
        return this.appointmentLocationRepository
                .save(new AppointmentLocationEntity(location))
                .toDomain();
    }

    @Override
    public void delete(UUID id) {
        this.appointmentLocationRepository.deleteById(id);
    }

    @Override
    public List<AppointmentLocation> findAll() {
        return this.appointmentLocationRepository.findAllByOrderByNameAsc().stream()
                .map(AppointmentLocationEntity::toDomain)
                .toList();
    }

    @Override
    public boolean existsByName(String name) {
        return this.appointmentLocationRepository.existsByName(name);
    }

    @Override
    public boolean isReferenced(UUID id) {
        return this.appointmentRepository.existsByLocationId(id);
    }
}
