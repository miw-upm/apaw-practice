package es.upm.miw.apaw.domain.services.clinic;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.clinic.Appointment;
import es.upm.miw.apaw.domain.models.clinic.Veterinarian;
import es.upm.miw.apaw.domain.persistenceports.clinic.AppointmentPersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final AppointmentPersistence appointmentPersistence;
    private final PetService petService;
    private final VeterinarianService veterinarianService;
    private final UserRestClient userRestClient;

    public AppointmentService(
            AppointmentPersistence appointmentPersistence,
            PetService petService,
            VeterinarianService veterinarianService,
            UserRestClient userRestClient) {
        this.appointmentPersistence = appointmentPersistence;
        this.petService = petService;
        this.veterinarianService = veterinarianService;
        this.userRestClient = userRestClient;
    }

    public Appointment create(Appointment appointment, Long licenceVeterinarian, Long userPetMicrochip) {
        appointment.setId(UUID.randomUUID());
        Appointment save = this.appointmentPersistence.save(appointment);
        petService.assignAppointment(userPetMicrochip, save.getId());
        veterinarianService.assignAppointment(licenceVeterinarian, save.getId());
        return save;
    }

    public Appointment updateAppointmentDate(UUID id, LocalDateTime newDate) {
        Appointment appointment = this.readById(id);
        appointment.setAppointmentDate(newDate);
        return this.appointmentPersistence.save(appointment);
    }

    public Appointment readById(UUID id) {
        return this.appointmentPersistence.findById(id)
                .orElseThrow(() -> new NotFoundException("Appointment not found: " + id));
    }

    public List<String> findMobilesByDiagnosisCode(String code) {
        List<Appointment> appointments = this.appointmentPersistence.findByDiagnosisCode(code);
        if (appointments.isEmpty()) {
            return List.of();
        }

        List<UUID> appointmentIds = appointments.stream()
                .map(Appointment::getId)
                .collect(Collectors.toList());

        List<Veterinarian> veterinarians = veterinarianService.findByAppointmentIds(appointmentIds);

        return veterinarians.stream()
                .map(vet -> userRestClient.readById(vet.getUser().getId()))
                .map(UserDto::getMobile)
                .filter(Objects::nonNull)
                .distinct()
                .toList();
    }
}