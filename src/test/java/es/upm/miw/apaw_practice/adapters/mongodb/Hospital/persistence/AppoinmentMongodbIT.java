package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.persistence;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos.AppointmentRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.AppointmentEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.persistence.AppointmentPersistenceMongodb;
import es.upm.miw.apaw_practice.domain.models.Hospital.Appointment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AppoinmentMongodbIT {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentPersistenceMongodb appointmentPersistenceMongodb;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes the mocks
    }

    @Test
    void testFindAll() {
        // Arrange
        AppointmentEntity appointmentEntity1 = new AppointmentEntity(
                1,
                LocalDate.of(2024, 10, 10),
                LocalTime.of(10, 30),
                "Room 101",
                "12345678A",
                "87654321B"
        );

        AppointmentEntity appointmentEntity2 = new AppointmentEntity(
                2,
                LocalDate.of(2024, 11, 12),
                LocalTime.of(14, 0),
                "Room 102",
                "12345678A",
                "87654321B"
        );

        // Mock the repository method to return the appointment entities
        when(appointmentRepository.findAll()).thenReturn(Arrays.asList(appointmentEntity1, appointmentEntity2));

        // Act
        List<Appointment> appointments = appointmentPersistenceMongodb.findAll();

        // Assert
        assertEquals(2, appointments.size());
        assertEquals("Room 101", appointments.get(0).getLocation());
        assertEquals("Room 102", appointments.get(1).getLocation());
        // Add more assertions if you need to check other properties
    }
}
