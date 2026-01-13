package es.upm.miw.apaw.domain.services.clinic;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import es.upm.miw.apaw.adapters.mongodb.clinic.daos.ClinicSeeder;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.clinic.Gender;
import es.upm.miw.apaw.domain.models.clinic.Pet;
import es.upm.miw.apaw.domain.models.clinic.Species;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
class PetServiceIT {

    @Autowired
    private PetService petService;
    @Autowired
    private ClinicSeeder clinicSeeder;

    @BeforeEach
    void resetDb() {
        clinicSeeder.deleteAll();
        clinicSeeder.seedDatabase();
    }

    @Test
    void testReadByMicrochip_ok() {
        Pet pet = this.petService.readByMicrochip(ClinicSeeder.MICROCHIP_CHISPA);
        assertThat(pet).isNotNull();
        assertThat(pet.getMicrochipNumber()).isEqualTo(ClinicSeeder.MICROCHIP_CHISPA);
    }

    @Test
    void testReadByMicrochip_notFound() {
        assertThrows(NotFoundException.class, () -> this.petService.readByMicrochip(999999L));
    }

    @Test
    void testAssignAppointment() {
        UUID appointmentId = UUID.randomUUID();
        this.petService.assignAppointment(ClinicSeeder.MICROCHIP_CHISPA, appointmentId);
        Pet pet = this.petService.readByMicrochip(ClinicSeeder.MICROCHIP_CHISPA);
        assertThat(pet.getAppointments()).isNotNull();
        assertThat(pet.getAppointments()).anyMatch(appointment -> appointment.getId().equals(appointmentId));
    }

    @Test
    void testUpdatePet() {
        Pet updatedPet = Pet.builder()
                .name("Chispa Updated")
                .species(Species.CAT)
                .gender(Gender.MALE)
                .build();

        Pet result = this.petService.update(ClinicSeeder.MICROCHIP_CHISPA, updatedPet);
        assertThat(result.getName()).isEqualTo("Chispa Updated");
    }

    @Test
    void testFindMicrochipNumbersByLicenseNumber() {
        List<Long> microchipNumbers = this.petService.findMicrochipNumbersByLicenseNumber(ClinicSeeder.LICENSE_DR_SMITH);

        assertThat(microchipNumbers).isNotEmpty();
        assertThat(microchipNumbers).containsExactlyInAnyOrder(ClinicSeeder.MICROCHIP_CHISPA);
    }

    @Test
    void testFindMicrochipNumbersByLicenseNumber_not_found() {
        assertThrows(NotFoundException.class, ()-> this.petService.findMicrochipNumbersByLicenseNumber(99999999999L));
    }

}