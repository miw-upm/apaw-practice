package es.upm.miw.apaw.adapters.mongodb.clinic.daos;


import es.upm.miw.apaw.adapters.mongodb.clinic.entities.*;
import es.upm.miw.apaw.domain.models.clinic.Gender;
import es.upm.miw.apaw.domain.models.clinic.Species;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
@Profile({"dev", "test"})
@Log4j2
public class ClinicSeeder {

    public static final Long MICROCHIP_CHISPA = 900000000000001L;
    public static final Long MICROCHIP_TOBY = 900000000000002L;
    public static final Long LICENSE_DR_SMITH = 10101010101L;
    public static final Long LICENSE_DR_JONES = 10101010102L;
    public static final UUID USER_UUID_DR_SMITH = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001");
    public static final UUID USER_UUID_DR_JONES = UUID.randomUUID();
    public static final UUID ID_APPOINTMENT_GRIPE = UUID.randomUUID();
    public static final UUID ID_APPOINTMENT_REVISION = UUID.randomUUID();

    private final VeterinarianRepository veterinarianRepository;
    private final PetRepository petRepository;
    private final AppointmentRepository appointmentRepository;

    public ClinicSeeder(VeterinarianRepository veterinarianRepository, PetRepository petRepository,
                        AppointmentRepository appointmentRepository) {
        this.veterinarianRepository = veterinarianRepository;
        this.petRepository = petRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public void seedDatabase() {
        log.warn("------- Clinic Initial Load -----------");

        saveVeterinarians();
        savePets();
        saveAppointments();

        log.warn("------- Clinic Data Loaded -----------");
    }

    private void saveVeterinarians() {
        var veterinarians = List.of(
                VeterinarianEntity.builder()
                        .licenseNumber(LICENSE_DR_SMITH)
                        .active(true)
                        .createdAt(LocalDateTime.now().minusYears(5))
                        .userId(USER_UUID_DR_SMITH)
                        .appointments(List.of(ID_APPOINTMENT_GRIPE))
                        .build(),
                VeterinarianEntity.builder()
                        .licenseNumber(LICENSE_DR_JONES)
                        .active(true)
                        .createdAt(LocalDateTime.now().minusYears(3))
                        .userId(USER_UUID_DR_JONES)
                        .appointments(List.of(ID_APPOINTMENT_REVISION))
                        .build()
        );
        this.veterinarianRepository.saveAll(veterinarians);
    }

    private void savePets() {
        var pets = List.of(
                PetEntity.builder()
                        .microchipNumber(MICROCHIP_CHISPA)
                        .name("Chispa")
                        .species(Species.DOG)
                        .gender(Gender.FEMALE)
                        .build(),
                PetEntity.builder()
                        .microchipNumber(MICROCHIP_TOBY)
                        .name("Toby")
                        .species(Species.CAT)
                        .gender(Gender.MALE)
                        .build()
        );
        this.petRepository.saveAll(pets);
    }

    private void saveAppointments() {
        var appointments = List.of(
                AppointmentEntity.builder()
                        .id(ID_APPOINTMENT_GRIPE)
                        .appointmentDate(LocalDateTime.now().plusDays(1))
                        .reason("Consulta general")
                        .diagnoses(List.of(
                                DiagnosisEntity.builder()
                                        .code("FLU-001")
                                        .diagnosisDate(LocalDateTime.now().minusDays(5))
                                        .severityLevel(2)
                                        .notes("Gripe leve")
                                        .treatments(List.of(
                                                TreatmentEntity.builder()
                                                        .treatmentCode("VAC-001")
                                                        .description("Flu vaccine")
                                                        .totalCost(new BigDecimal("50.00"))
                                                        .medications(List.of("Paracetamol", "Ibuprofen"))
                                                        .build()
                                        ))
                                        .build()
                        ))
                        .build(),
                AppointmentEntity.builder()
                        .id(ID_APPOINTMENT_REVISION)
                        .appointmentDate(LocalDateTime.now().plusDays(3))
                        .reason("Revisión")
                        .diagnoses(List.of(
                                DiagnosisEntity.builder()
                                        .code("KID-001")
                                        .diagnosisDate(LocalDateTime.now().minusDays(2))
                                        .severityLevel(3)
                                        .notes("Renal problem")
                                        .treatments(List.of(
                                                TreatmentEntity.builder()
                                                        .treatmentCode("SUP-001")
                                                        .description("Supplements vitamins")
                                                        .totalCost(new BigDecimal("30.00"))
                                                        .medications(List.of("Vitamin D", "Calcium"))
                                                        .build()
                                        ))
                                        .build()
                        ))
                        .build()
        );
        this.appointmentRepository.saveAll(appointments);
    }

    public void deleteAll() {
        this.petRepository.deleteAll();
        this.veterinarianRepository.deleteAll();
        this.appointmentRepository.deleteAll();
    }
}