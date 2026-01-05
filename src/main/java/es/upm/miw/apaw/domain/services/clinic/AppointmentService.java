package es.upm.miw.apaw.domain.services.clinic;

import es.upm.miw.apaw.domain.models.clinic.Appointment;
import es.upm.miw.apaw.domain.persistenceports.clinic.AppointmentPersistence;
import org.springframework.stereotype.Service;

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
}