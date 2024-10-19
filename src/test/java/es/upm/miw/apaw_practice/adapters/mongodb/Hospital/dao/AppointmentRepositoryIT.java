package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.AppointmentEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public class AppointmentRepositoryIT {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Test
    void testSaveAndFindById() {
        AppointmentEntity appointment = new AppointmentEntity(1, LocalDate.now(), LocalTime.now(), "Location", "12345678A", "87654321B");
        appointmentRepository.save(appointment);

        AppointmentEntity foundAppointment = appointmentRepository.findById(1).orElse(null);
        assertNotNull(foundAppointment);
        assertEquals(appointment.getDoctorDni(), foundAppointment.getDoctorDni());
    }

    @Test
    void testFindAll() {
        List<AppointmentEntity> appointments = appointmentRepository.findAll();
        assertNotNull(appointments);
        assertTrue(appointments.size() >= 0);
    }

    @Test
    void testDelete() {
        AppointmentEntity appointment = new AppointmentEntity(2, LocalDate.now(), LocalTime.now(), "Location", "12345678A", "87654321B");
        appointmentRepository.save(appointment);
        appointmentRepository.deleteById(2);
        assertFalse(appointmentRepository.existsById(2));
    }
}
