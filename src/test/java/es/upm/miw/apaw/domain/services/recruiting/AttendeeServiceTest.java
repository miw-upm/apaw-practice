package es.upm.miw.apaw.domain.services.recruiting;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.recruiting.Attendee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class AttendeeServiceTest {

    @Autowired
    private AttendeeService attendeeService;

    // --- GET testing ------------------------------------------------------------------

    @Test
    void testReadByEmailAddressFound() {
        String email = "markus.urbanietz@test.com";

        Attendee attendee = attendeeService.read(email);

        assertThat(attendee).isNotNull();
        assertThat(attendee.getEmailAddress()).isEqualTo(email);
        assertThat(attendee.getFullName()).isEqualTo("Markus Urbanietz");
        assertThat(attendee.getPhoneNumber()).isEqualTo("+4112345123");
        assertThat(attendee.getUser()).isNotNull();
    }

    @Test
    void testReadByEmailAddressNotFound() {
        assertThrows(NotFoundException.class,
                () -> attendeeService.read("unknown@example.com"));
    }

    // --- DELETE testing ---------------------------------------------------------------

    @Test
    void testDeleteAttendeeWithMeetings() {
        String email = "andrea.schulz@test.com"; // Active in meetings (meeting-3, meeting-6)

        attendeeService.delete(email);

        assertThrows(NotFoundException.class, () -> attendeeService.read(email));
    }

    @Test
    void testDeleteAttendeeWithoutMeetings() {
        String email = "karolyn.sanz@test.com"; // No active in meetings at all

        attendeeService.delete(email);

        assertThrows(NotFoundException.class, () -> attendeeService.read(email));
    }

    @Test
    void testDeleteAttendeeNotFound() {
        assertThrows(NotFoundException.class,
                () -> attendeeService.delete("unknown@example.com"));
    }
}