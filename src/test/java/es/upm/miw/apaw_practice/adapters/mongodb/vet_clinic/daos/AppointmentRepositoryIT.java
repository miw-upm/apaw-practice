package es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import java.time.LocalTime;

@TestConfig
public class AppointmentRepositoryIT {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Test
    void findAppointmentByDateAndHour(){
        assertTrue(appointmentRepository.findAppointmentByDateAndHour(LocalDate.of(2019, 3, 15),
                LocalTime.of(16, 15)).isPresent());
    }
}
