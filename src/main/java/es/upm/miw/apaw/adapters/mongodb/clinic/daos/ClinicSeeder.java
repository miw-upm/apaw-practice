package es.upm.miw.apaw.adapters.mongodb.clinic.daos;

import es.upm.miw.apaw.adapters.mongodb.clinic.entities.AnimalEntity;
import es.upm.miw.apaw.adapters.mongodb.clinic.entities.DiagnosisEntity;
import es.upm.miw.apaw.adapters.mongodb.clinic.entities.DoctorEntity;
import es.upm.miw.apaw.adapters.mongodb.clinic.entities.TreatmentEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.boot.CommandLineRunner; // La importación ya estaba bien.
// Nota: La importación 'es.upm.miw.apaw.adapters.mongodb.clinic.daos.ClinicSeeder;' es redundante y puede eliminarse.

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
// import java.util.List; // Ya no es necesaria si solo usas Arrays.asList()
import java.util.UUID;

@Repository
@Profile({"dev", "test"})
@Log4j2
// 1. Implementar la interfaz CommandLineRunner para la ejecución automática al inicio del test.
public class ClinicSeeder implements CommandLineRunner {

    // Microchip numbers y Licencias usados para asegurar consistencia en las relaciones
    public static final Long MICROCHIP_CHISPA = 900000000000001L;
    public static final Long MICROCHIP_TOBY = 900000000000002L;
    public static final Long LICENSE_DR_SMITH = 10101010101L;
    public static final String DIAGNOSIS_CODE_FLU = "FLU-001";
    public static final String TREATMENT_CODE_VAC = "VAC-001";

    private final DoctorRepository doctorRepository;
    private final AnimalRepository animalRepository;
    private final DiagnosisRepository diagnosisRepository;
    private final TreatmentRepository treatmentRepository;

    @Autowired
    public ClinicSeeder(DoctorRepository doctorRepository, AnimalRepository animalRepository,
                        DiagnosisRepository diagnosisRepository, TreatmentRepository treatmentRepository) {
        this.doctorRepository = doctorRepository;
        this.animalRepository = animalRepository;
        this.diagnosisRepository = diagnosisRepository;
        this.treatmentRepository = treatmentRepository;
    }

    // 2. Implementar el método run(), que se ejecuta automáticamente.
    @Override
    public void run(String... args) {
        this.deleteAll();
        this.seedDatabase();
    }

    public void seedDatabase() {
        log.warn("------- Clinic Initial Load -----------");

        // 1. Crear Doctores
        DoctorEntity[] doctors = {
                DoctorEntity.builder().licenseNumber(LICENSE_DR_SMITH)
                        .name("Dr. Smith").specialty("General").userId(UUID.randomUUID()).build(),
                DoctorEntity.builder().licenseNumber(10101010102L)
                        .name("Dr. Jones").specialty("Cirujano").userId(UUID.randomUUID()).build()
        };
        this.doctorRepository.saveAll(Arrays.asList(doctors));

        // 2. Crear Animales (con clave foránea a Doctor)
        AnimalEntity[] animals = {
                AnimalEntity.builder().microchipNumber(MICROCHIP_CHISPA)
                        .petName("Chispa").weightKilos(5.2).vaccinated(true).doctorLicenseNumber(LICENSE_DR_SMITH).build(),
                AnimalEntity.builder().microchipNumber(MICROCHIP_TOBY)
                        .petName("Toby").weightKilos(25.8).vaccinated(false).doctorLicenseNumber(LICENSE_DR_SMITH).build()
        };
        this.animalRepository.saveAll(Arrays.asList(animals));

        // 3. Crear Diagnósticos (con clave foránea a Animal)
        DiagnosisEntity[] diagnoses = {
                DiagnosisEntity.builder().code(DIAGNOSIS_CODE_FLU)
                        .diagnosisName("Gripe Canina").diagnosisDate(LocalDateTime.now().minusDays(5))
                        .animalMicrochipNumber(MICROCHIP_CHISPA).build(),
                DiagnosisEntity.builder().code("KID-001")
                        .diagnosisName("Fallo Renal").diagnosisDate(LocalDateTime.now().minusDays(1))
                        .animalMicrochipNumber(MICROCHIP_TOBY).build()
        };
        this.diagnosisRepository.saveAll(Arrays.asList(diagnoses));

        // 4. Crear Tratamientos (con clave foránea a Diagnosis)
        TreatmentEntity[] treatments = {
                TreatmentEntity.builder().treatmentCode(TREATMENT_CODE_VAC)
                        .procedureName("Vacuna anual").totalCost(new BigDecimal("50.00"))
                        .diagnosisCode(DIAGNOSIS_CODE_FLU).build(),
                TreatmentEntity.builder().treatmentCode("VIT-001")
                        .procedureName("Suplementos vitamínicos").totalCost(new BigDecimal("15.50"))
                        .diagnosisCode(DIAGNOSIS_CODE_FLU).build()
        };
        this.treatmentRepository.saveAll(Arrays.asList(treatments));

        log.warn("        ------- clinic");
    }

    public void deleteAll() {
        this.treatmentRepository.deleteAll();
        this.diagnosisRepository.deleteAll();
        this.animalRepository.deleteAll();
        this.doctorRepository.deleteAll();
    }
}