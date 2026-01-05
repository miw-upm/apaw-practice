package es.upm.miw.apaw.adapters.mongodb.clinic.daos;

import es.upm.miw.apaw.adapters.mongodb.clinic.entities.PetEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

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
}