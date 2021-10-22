package es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities.DiagnosisEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities.PetEntity;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Appointment;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Diagnosis;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Pet;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Vet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class AppointmentPersistanceMongodbIT {
    @Autowired
    private AppointmentPersistanceMongodb appointmentPersistance;

    @Test
    void createAndReadTest() {
        List<Vet> vets = new ArrayList<>();
        Vet vet = Vet.builder()
                .vetNumber(606)
                .name("vet606")
                .surname("surname606")
                .build();
        vets.add(vet);
        List<Diagnosis> diagnosis = new ArrayList<>();
        Pet pet = new Pet(1203, 20, "Pet1203", "Owner1203", diagnosis);
        Appointment appointmentCreation = new Appointment(LocalDate.of(2021, 10, 10),
                LocalTime.of(16, 10), false, pet, vets);
        this.appointmentPersistance.create(appointmentCreation);
        Appointment appointmentDB = this.appointmentPersistance.read(LocalDate.of(2021, 10, 10),
                LocalTime.of(16, 10));
        assertEquals(LocalDate.of(2021, 10, 10), appointmentDB.getDate());
        assertEquals(LocalTime.of(16, 10), appointmentDB.getHour());
        assertEquals(false, appointmentDB.getConsumed());
    }

    @Test
    void createAndUpdateTest() {
        List<Vet> vets = new ArrayList<>();
        Vet vet = Vet.builder()
                .vetNumber(66)
                .name("vet66")
                .surname("surname66")
                .build();
        vets.add(vet);
        List<Diagnosis> diagnosis = new ArrayList<>();
        Pet pet = new Pet(123, 2, "Pet123", "Owner123", diagnosis);
        Appointment appointmentCreation = new Appointment(LocalDate.of(2021, 1, 1),
                LocalTime.of(16, 0), false, pet, vets);
        Appointment appointmentDB = this.appointmentPersistance.create(appointmentCreation);
        appointmentDB.setConsumed(true);
        this.appointmentPersistance.update(LocalDate.of(2021, 1, 1),
                LocalTime.of(16, 0), appointmentDB);
        appointmentDB = this.appointmentPersistance.read(LocalDate.of(2021, 1, 1),
                LocalTime.of(16, 0));
        assertEquals(true, appointmentDB.getConsumed());
    }

    @Test
    void findByConsumedTest() {
        Stream<Appointment> appointments = this.appointmentPersistance.findByConsumed(false);
        assertEquals(appointments.count(), 2);
    }
}
