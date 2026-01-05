package es.upm.miw.apaw.domain.services.clinic;

import static org.assertj.core.api.Assertions.assertThat;

import es.upm.miw.apaw.adapters.mongodb.clinic.daos.ClinicSeeder;
import es.upm.miw.apaw.domain.models.clinic.Appointment;
import es.upm.miw.apaw.domain.models.clinic.Diagnosis;
import es.upm.miw.apaw.domain.models.clinic.Treatment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
class AppointmentServiceIT {

    public static final String ROUTINE_CHECK_UP = "Routine check-up";
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private ClinicSeeder clinicSeeder;

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

}