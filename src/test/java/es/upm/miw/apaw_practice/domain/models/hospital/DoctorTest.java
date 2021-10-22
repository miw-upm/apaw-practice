package es.upm.miw.apaw_practice.domain.models.hospital;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class DoctorTest {

    @Test
    void testBuilder() {
        Doctor doctor = Doctor.builder()
                .nick("Adrian")
                .surname("Callejo")
                .activeSince(LocalDate.of(2016, 11, 11))
                .build();
        assertEquals("Adrian", doctor.getNick());
        assertEquals("Callejo", doctor.getSurname());
        assertEquals(LocalDate.of(2016, 11, 11), doctor.getActiveSince());
    }
}
