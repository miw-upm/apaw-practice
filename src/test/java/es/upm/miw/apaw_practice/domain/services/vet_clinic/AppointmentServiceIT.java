package es.upm.miw.apaw_practice.domain.services.vet_clinic;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities.AppointmentEntity;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Appointment;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Diagnosis;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Pet;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Vet;
import es.upm.miw.apaw_practice.domain.persistence_ports.vet_clinic.AppointmentPersistance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class AppointmentServiceIT {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private AppointmentPersistance appointmentPersistance;

    @Test
    void updateConsumed() {
        List<Vet> vets = new ArrayList<>();
        Pet pet = new Pet();
        List<Appointment> appointmentList = List.of(
                new Appointment(LocalDate.of(2021, 12, 4),
                        LocalTime.of(16, 45), true, pet, vets),
                new Appointment(LocalDate.of(2021, 2, 16), LocalTime.of(9, 00),
                        false, pet, vets)
        );
        this.appointmentService.updateConsumed(appointmentList.stream());
        assertEquals(true,
                this.appointmentPersistance
                .read(LocalDate.of(2021, 12, 4), LocalTime.of(16, 45))
                .getConsumed());
        assertEquals(false,
                this.appointmentPersistance
                        .read(LocalDate.of(2021, 2, 16), LocalTime.of(9, 00))
                        .getConsumed());
        appointmentList = List.of(
                new Appointment(LocalDate.of(2021, 12, 4),
                        LocalTime.of(16, 45), false, pet, vets),
                new Appointment(LocalDate.of(2021, 2, 16), LocalTime.of(9, 00),
                        true, pet, vets)
        );
        this.appointmentService.updateConsumed(appointmentList.stream());
    }

    @Test
    void findByConsumedTest() {
        Stream<Appointment> appointments = this.appointmentService.findByConsumed(false);
        assertEquals(false, appointments.findFirst().get().getConsumed());
    }
}
