package es.upm.miw.apaw.domain.services.clinic;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.clinic.Appointment;
import es.upm.miw.apaw.domain.persistenceports.clinic.AppointmentPersistence;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AppointmentService {

    private final AppointmentPersistence appointmentPersistence;
    private final PetService petService;
    private final VeterinarianService veterinarianService;

    public AppointmentService(
            AppointmentPersistence appointmentPersistence,
            PetService petService,
            VeterinarianService veterinarianService) {
        this.appointmentPersistence = appointmentPersistence;
        this.petService = petService;
        this.veterinarianService = veterinarianService;
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
}