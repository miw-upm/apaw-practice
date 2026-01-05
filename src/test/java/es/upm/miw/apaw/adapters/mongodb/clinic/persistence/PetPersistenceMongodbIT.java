package es.upm.miw.apaw.adapters.mongodb.clinic.persistence;

import es.upm.miw.apaw.adapters.mongodb.clinic.daos.ClinicSeeder;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.clinic.Appointment;
import es.upm.miw.apaw.domain.models.clinic.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class PetPersistenceMongodbIT {

    @Autowired
    private PetPersistenceMongodb petPersistence;

    @Autowired
    private ClinicSeeder clinicSeeder;

    @BeforeEach
    void resetDb() {
        clinicSeeder.deleteAll();
        clinicSeeder.seedDatabase();
    }

    @Test
    void testFindByMicrochip_ok() {
        Long microchip = ClinicSeeder.MICROCHIP_CHISPA;
        Optional<Pet> pet = this.petPersistence.findByMicrochipNumber(microchip);
        assertThat(pet).isPresent();
        assertThat(pet.get().getName()).isEqualTo("Chispa");
    }

    @Test
    void testFindByMicrochip_notFound() {
        Optional<Pet> pet = this.petPersistence.findByMicrochipNumber(999999L);
        assertThat(pet).isEmpty();
    }

    @Test
    void testAddAppointments_ok() {
        Long microchip = ClinicSeeder.MICROCHIP_CHISPA;
        UUID appointmentId = UUID.randomUUID();

        this.petPersistence.addAppointments(microchip, appointmentId);

        Pet pet = this.petPersistence.findByMicrochipNumber(microchip).orElseThrow();
        assertThat(pet.getAppointments()).isNotNull();
        assertThat(pet.getAppointments().stream().map(Appointment::getId)).contains(appointmentId);
    }

    @Test
    void testAddAppointments_notFound() {
        UUID appointmentId = UUID.randomUUID();
        assertThrows(NotFoundException.class, () -> this.petPersistence.addAppointments(999999L, appointmentId));
    }

}