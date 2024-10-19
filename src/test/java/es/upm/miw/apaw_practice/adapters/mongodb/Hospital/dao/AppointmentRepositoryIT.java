package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.AppointmentEntity;
import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
@DataMongoTest
public class AppointmentRepositoryIT {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Test
    void testCreateAndFindAppointment() {
        AppointmentEntity appointment = new AppointmentEntity(
                1,
                LocalDate.of(2024, 10, 10),
                LocalTime.of(10, 30),
                "Room 101",
                "12345678A",
                "87654321B"
        );
        appointmentRepository.save(appointment);
        Optional<AppointmentEntity> retrievedAppointment = appointmentRepository.findById(appointment.getId());
        assertTrue(retrievedAppointment.isPresent());
        assertEquals("Room 101", retrievedAppointment.get().getLocation());
    }

    @Test
    void testUpdateAppointment() {
        AppointmentEntity appointment = new AppointmentEntity(
                2,
                LocalDate.of(2024, 11, 12),
                LocalTime.of(14, 0),
                "Room 102",
                "12345678A",
                "87654321B"
        );
        appointmentRepository.save(appointment);
        appointment.setLocation("Room 203");
        appointmentRepository.save(appointment);
        Optional<AppointmentEntity> updatedAppointment = appointmentRepository.findById(appointment.getId());
        assertTrue(updatedAppointment.isPresent());
        assertEquals("Room 203", updatedAppointment.get().getLocation());
    }

    @Test
    void testDeleteAppointment() {
        AppointmentEntity appointment = new AppointmentEntity(
                3,
                LocalDate.of(2024, 12, 25),
                LocalTime.of(16, 0),
                "Room 103",
                "12345678A",
                "87654321B"
        );
        appointmentRepository.save(appointment);
        appointmentRepository.deleteById(appointment.getId());
        Optional<AppointmentEntity> deletedAppointment = appointmentRepository.findById(appointment.getId());
        assertFalse(deletedAppointment.isPresent());
    }
}
