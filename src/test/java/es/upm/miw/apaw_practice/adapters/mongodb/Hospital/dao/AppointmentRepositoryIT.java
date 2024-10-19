package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.AppointmentEntity;
import org.junit.jupiter.api.Test;
import es.upm.miw.apaw_practice.TestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class AppointmentRepositoryIT {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Test
    void testCreateAndFindAppointment() {
        AppointmentEntity appointment = new AppointmentEntity(
                "1", // Use String ID
                LocalDate.of(2024, 10, 10),
                "General Checkup"
        );
        appointmentRepository.save(appointment);
        Optional<AppointmentEntity> retrievedAppointment = appointmentRepository.findById("1");
        assertTrue(retrievedAppointment.isPresent());
        assertEquals("General Checkup", retrievedAppointment.get().getDescription());
    }

    @Test
    void testUpdateAppointment() {
        AppointmentEntity appointment = new AppointmentEntity(
                "2",
                LocalDate.of(2024, 11, 12),
                "Initial Consultation"
        );
        appointmentRepository.save(appointment);
        appointment.setDescription("Updated Consultation");
        appointmentRepository.save(appointment);
        Optional<AppointmentEntity> updatedAppointment = appointmentRepository.findById("2");
        assertTrue(updatedAppointment.isPresent());
        assertEquals("Updated Consultation", updatedAppointment.get().getDescription());
    }

    @Test
    void testDeleteAppointment() {
        AppointmentEntity appointment = new AppointmentEntity(
                "3",
                LocalDate.of(2024, 12, 25),
                "Christmas Appointment"
        );
        appointmentRepository.save(appointment);
        appointmentRepository.deleteById("3");
        Optional<AppointmentEntity> deletedAppointment = appointmentRepository.findById("3");
        assertFalse(deletedAppointment.isPresent());
    }
}
