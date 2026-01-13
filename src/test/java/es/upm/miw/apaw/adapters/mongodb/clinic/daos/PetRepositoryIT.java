package es.upm.miw.apaw.adapters.mongodb.clinic.daos;

import es.upm.miw.apaw.adapters.mongodb.clinic.entities.PetEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class PetRepositoryIT {

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private ClinicSeeder clinicSeeder;

    @BeforeEach
    void resetDb() {
        clinicSeeder.deleteAll();
        clinicSeeder.seedDatabase();
    }

    @Test
    void testFindByMicrochipNumber() {
        Optional<PetEntity> pet = this.petRepository.findByMicrochipNumber(ClinicSeeder.MICROCHIP_CHISPA);
        assertTrue(pet.isPresent());
        assertThat(pet.get().getMicrochipNumber()).isEqualTo(ClinicSeeder.MICROCHIP_CHISPA);
        assertThat(pet.get().getName()).isEqualTo("Chispa");
    }

    @Test
    void testFindByAppointmentsIn() {
        List<UUID> appointmentIds = List.of(ClinicSeeder.ID_APPOINTMENT_GRIPE, ClinicSeeder.ID_APPOINTMENT_REVISION);
        List<PetEntity> pets = this.petRepository.findByAppointmentsIn(appointmentIds);
        assertThat(pets).isNotEmpty();
        assertThat(pets).hasSize(2);
        assertThat(pets.stream().map(PetEntity::getMicrochipNumber))
                .containsExactlyInAnyOrder(ClinicSeeder.MICROCHIP_CHISPA, ClinicSeeder.MICROCHIP_TOBY);
    }
}