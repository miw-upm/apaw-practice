package es.upm.miw.apaw.domain.services.clinic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import es.upm.miw.apaw.adapters.mongodb.clinic.daos.ClinicSeeder;
import es.upm.miw.apaw.adapters.mongodb.clinic.daos.VeterinarianRepository;
import es.upm.miw.apaw.adapters.mongodb.clinic.entities.VeterinarianEntity;
import es.upm.miw.apaw.adapters.mongodb.clinic.persistence.AppointmentPersistenceMongodb;
import es.upm.miw.apaw.adapters.restclients.UserRestClientImpl;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.clinic.Appointment;
import es.upm.miw.apaw.domain.models.clinic.Diagnosis;
import es.upm.miw.apaw.domain.models.clinic.Treatment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
class AppointmentServiceIT {

    public static final String ROUTINE_CHECK_UP = "Routine check-up";
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private ClinicSeeder clinicSeeder;
    @Autowired
    private AppointmentPersistenceMongodb appointmentPersistence;
    @Autowired
    private VeterinarianRepository veterinarianRepository;
    @MockitoBean
    private UserRestClientImpl userRestClient;

    @BeforeEach
    void resetDb() {
        clinicSeeder.deleteAll();
        clinicSeeder.seedDatabase();
    }

    @Test
    void testCreateAppointment() {
        Appointment createdAppointment = getCreatedAppointment();
        assertThat(createdAppointment.getId()).isNotNull();
        assertThat(createdAppointment.getReason()).isEqualTo(ROUTINE_CHECK_UP);
    }

    private Appointment getCreatedAppointment() {
        Diagnosis diagnosis = Diagnosis.builder()
                .code("001")
                .diagnosisDate(LocalDateTime.now())
                .notes("nota 01")
                .severityLevel(1)
                .treatments(
                        List.of(
                                Treatment.builder().treatmentCode("01").description("descript")
                                        .totalCost(new BigDecimal(250)).build()))
                .build();
        List<Diagnosis> diagnoses = new ArrayList<>();
        diagnoses.add(diagnosis);

        Appointment newAppointment = Appointment.builder()
                .appointmentDate(LocalDateTime.now().plusDays(1))
                .reason(ROUTINE_CHECK_UP)
                .diagnoses(diagnoses)
                .build();

        return this.appointmentService.create(
                newAppointment, ClinicSeeder.LICENSE_DR_SMITH, ClinicSeeder.MICROCHIP_CHISPA);
    }

    @Test
    void testReadById() {
        Appointment createdAppointment = this.getCreatedAppointment();
        Appointment appointment = this.appointmentService.readById(createdAppointment.getId());
        assertThat(appointment.getReason()).isEqualTo(ROUTINE_CHECK_UP);
    }

    @Test
    void testReadByIdNotFound() {
        assertThrows(NotFoundException.class, () ->
                this.appointmentService.readById(UUID.randomUUID()));
    }

    @Test
    void testUpdateAppointmentDate() {
        Appointment createdAppointment = this.getCreatedAppointment();
        LocalDateTime newDate = LocalDateTime.now().plusDays(5);

        Appointment updatedAppointment = this.appointmentService.updateAppointmentDate(createdAppointment.getId(), newDate);

        assertThat(updatedAppointment.getId()).isEqualTo(createdAppointment.getId());
        assertThat(updatedAppointment.getAppointmentDate()).isEqualTo(newDate);
    }

    @Test
    void testUpdateAppointmentDateNotFound() {
        LocalDateTime newDate = LocalDateTime.now().plusDays(5);

        assertThrows(NotFoundException.class, () ->
                this.appointmentService.updateAppointmentDate(UUID.randomUUID(), newDate));
    }

    @Test
    void testFindMobilesByDiagnosisCode_ok() {
        String code = "001";
        Appointment appointment1 = Appointment.builder()
                .id(UUID.randomUUID())
                .appointmentDate(LocalDateTime.now()).reason("Reason 1")
                .diagnoses(
                        List.of(Diagnosis.builder().code(code).build())).build();
        Appointment appointment2 = Appointment.builder()
                .id(UUID.randomUUID())
                .appointmentDate(LocalDateTime.now()).reason("Reason 2")
                .diagnoses(
                        List.of(Diagnosis.builder().code(code).build()))
                .build();
        appointment1 = appointmentPersistence.save(appointment1);
        appointment2 = appointmentPersistence.save(appointment2);

        VeterinarianEntity veterinarian1 = VeterinarianEntity.builder()
                .userId(UUID.randomUUID())
                .appointments(List.of(appointment1.getId()))
                .build();
        VeterinarianEntity veterinarian2 = VeterinarianEntity.builder()
                .userId(UUID.randomUUID())
                .appointments(List.of(appointment2.getId()))
                .build();
        veterinarianRepository.save(veterinarian1);
        veterinarianRepository.save(veterinarian2);

        when(userRestClient.readById(veterinarian1.getUserId()))
                .thenReturn(UserDto.builder().id(veterinarian1.getUserId()).mobile("123456789").build());
        when(userRestClient.readById(veterinarian2.getUserId()))
                .thenReturn(UserDto.builder().id(veterinarian2.getUserId()).mobile("123456789").build());

        List<String> mobiles = appointmentService.findMobilesByDiagnosisCode(code);

        assertThat(mobiles).isNotEmpty();
        assertThat(mobiles).containsExactlyInAnyOrder("123456789");
    }

    @Test
    void testFindMobilesByDiagnosisCode_emptyAppointments() {
        String code = "INVALID_CODE";

        List<String> mobiles = appointmentService.findMobilesByDiagnosisCode(code);

        assertThat(mobiles).isEmpty();
    }

}