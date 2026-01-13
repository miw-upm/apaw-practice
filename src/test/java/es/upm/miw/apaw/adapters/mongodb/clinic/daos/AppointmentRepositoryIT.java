package es.upm.miw.apaw.adapters.mongodb.clinic.daos;

import es.upm.miw.apaw.adapters.mongodb.clinic.entities.AppointmentEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class AppointmentRepositoryIT {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ClinicSeeder clinicSeeder;

    @BeforeEach
    void resetDb() {
        clinicSeeder.deleteAll();
        clinicSeeder.seedDatabase();
    }

    @Test
    void testFindAppointmentsByDiagnosisCode_ok() {
        String code = "001";
        List<AppointmentEntity> appointments = this.appointmentRepository.findByDiagnosisCode(code);
        assertThat(appointments).isNotEmpty();
        assertThat(appointments.getFirst().getDiagnoses()).anyMatch(diagnosis -> diagnosis.getCode().equals(code));
    }

    @Test
    void testFindAppointmentsByDiagnosisCode_notFound() {
        String invalidCode = "INVALID_CODE";
        List<AppointmentEntity> appointments = this.appointmentRepository.findByDiagnosisCode(invalidCode);
        assertThat(appointments).isEmpty();
    }
}