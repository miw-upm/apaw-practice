package es.upm.miw.apaw.domain.services.recruiting;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.recruiting.Application;
import es.upm.miw.apaw.domain.models.recruiting.Attendee;
import es.upm.miw.apaw.domain.models.recruiting.Meeting;
import es.upm.miw.apaw.domain.models.recruiting.Position;
import es.upm.miw.apaw.domain.models.recruiting.enums.Status;
import es.upm.miw.apaw.domain.persistenceports.recruiting.ApplicationPersistence;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class ApplicationServiceTest {

    @Autowired
    private ApplicationService applicationService;

    @MockitoBean
    private ApplicationPersistence applicationPersistence;

    // --- UPDATE testing ---------------------------------------------------------------

    @Test
    void testUpdateMeeting() {
        UUID applicationId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0030");

        Application application = Application.builder()
                .id(applicationId)
                .status(Status.Open)
                .created(LocalDate.now())
                .referral(true)
                .user(UserDto.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                        .build())
                .position(Position.builder()
                        .name("ABAP developer")
                        .description("ABAP developer for HR. At least 5 years of experience in OO and payroll implementation.")
                        .annualSalary(new BigDecimal("52000.00"))
                        .bonusSalary(new BigDecimal("5200.00"))
                        .numVacancies(3)
                        .build())
                .meetingList(List.of())
                .build();

        BDDMockito.given(this.applicationPersistence.readById(applicationId)).willReturn(application);

        List<Meeting> newMeetings = List.of(
                Meeting.builder()
                        .date(LocalDateTime.of(2025, 10, 20, 9, 0))
                        .url("https://meet.company.com/new-meeting-1")
                        .attendees(List.of(
                                Attendee.builder()
                                        .emailAddress("alice.johnson@test.com")
                                        .fullName("Alice Johnson")
                                        .phoneNumber("+34600111222")
                                        .build(),
                                Attendee.builder()
                                        .emailAddress("alvaro.zamarro@test.com")
                                        .fullName("Álvaro Zamarro")
                                        .phoneNumber("+34612333444")
                                        .build()
                        ))
                        .build(),
                Meeting.builder()
                        .date(LocalDateTime.of(2025, 10, 21, 11, 30))
                        .url("https://meet.company.com/new-meeting-2")
                        .attendees(List.of(
                                Attendee.builder()
                                        .emailAddress("tarek.awwad@test.com")
                                        .fullName("Tarek Awwad")
                                        .phoneNumber("+41676768919")
                                        .build(),
                                Attendee.builder()
                                        .emailAddress("micha.riechert@test.com")
                                        .fullName("Micha Riechert")
                                        .phoneNumber("+43699129878")
                                        .build()
                        ))
                        .build()
        );

        BDDMockito.given(this.applicationPersistence.update(any(Application.class)))
                .willAnswer(invocation -> invocation.getArgument(0));

        Application updated = this.applicationService.updateMeetings(applicationId, newMeetings);

        assertThat(updated).isNotNull();
        assertThat(updated.getId()).isEqualTo(applicationId);
        assertThat(updated.getMeetingList()).hasSize(2);
        assertThat(updated.getMeetingList().getFirst().getUrl())
                .isEqualTo("https://meet.company.com/new-meeting-1");
        assertThat(updated.getPosition().getName()).isEqualTo("ABAP developer");

        // Meetings assertions
        assertThat(updated.getMeetingList())
                .isNotNull()
                .hasSize(2);

        // Meeting 1
        Meeting meeting1 = updated.getMeetingList().get(0);
        assertThat(meeting1.getUrl()).isEqualTo("https://meet.company.com/new-meeting-1");
        assertThat(meeting1.getDate()).isEqualTo(LocalDateTime.of(2025, 10, 20, 9, 0));
        assertThat(meeting1.getAttendees())
                .isNotNull()
                .hasSize(2)
                .extracting(Attendee::getEmailAddress)
                .containsExactly("alice.johnson@test.com", "alvaro.zamarro@test.com");

        // Meeting 1 - individual attendee checks
        Attendee attendee1 = meeting1.getAttendees().getFirst();
        assertThat(attendee1.getFullName()).isEqualTo("Alice Johnson");
        assertThat(attendee1.getPhoneNumber()).isEqualTo("+34600111222");

        Attendee attendee2 = meeting1.getAttendees().get(1);
        assertThat(attendee2.getFullName()).isEqualTo("Álvaro Zamarro");
        assertThat(attendee2.getPhoneNumber()).isEqualTo("+34612333444");

        // Meeting 2
        Meeting meeting2 = updated.getMeetingList().get(1);
        assertThat(meeting2.getUrl()).isEqualTo("https://meet.company.com/new-meeting-2");
        assertThat(meeting2.getDate()).isEqualTo(LocalDateTime.of(2025, 10, 21, 11, 30));
        assertThat(meeting2.getAttendees())
                .isNotNull()
                .hasSize(2)
                .extracting(Attendee::getEmailAddress)
                .containsExactly("tarek.awwad@test.com", "micha.riechert@test.com");

        Attendee attendee3 = meeting2.getAttendees().getFirst();
        assertThat(attendee3.getFullName()).isEqualTo("Tarek Awwad");
        assertThat(attendee3.getPhoneNumber()).isEqualTo("+41676768919");

        Attendee attendee4 = meeting2.getAttendees().get(1);
        assertThat(attendee4.getFullName()).isEqualTo("Micha Riechert");
        assertThat(attendee4.getPhoneNumber()).isEqualTo("+43699129878");

        verify(this.applicationPersistence, times(1)).readById(applicationId);
        verify(this.applicationPersistence, times(1)).update(any(Application.class));
    }

    // --- SEARCHES testing -------------------------------------------------------------

    // Testing Search 1 #1269
    @Test
    void testFindAccumulatedAnnualSalary() {
        String fullName = "Iker Álvarez";
        BigDecimal accumulatedSalary = new BigDecimal("157000.00");

        BDDMockito.given(this.applicationPersistence.findAccumulatedAnnualSalaryByFullName(fullName))
                .willReturn(accumulatedSalary);

        BigDecimal result = this.applicationService.findAccumulatedAnnualSalaryByFullName(fullName);

        assertThat(result)
                .isNotNull()
                .isEqualByComparingTo(accumulatedSalary);

        verify(this.applicationPersistence, times(1))
                .findAccumulatedAnnualSalaryByFullName(fullName);
    }

    // Testing Search 2 #1270

    @Test
    void testFindUniqueUrlsByPositionName() {
        String name = "CPI consultant";
        List<String> expectedUrls = List.of(
                "//url-for-meeting-4",
                "//url-for-meeting-5",
                "//url-for-meeting-6"
        );

        BDDMockito.given(this.applicationPersistence.findUniqueUrlsByPositionName(name)).willReturn(expectedUrls);

        List<String> result = this.applicationService.findUniqueUrlsByPositionName(name);

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .hasSize(3)
                .containsExactlyInAnyOrderElementsOf(expectedUrls);

        verify(this.applicationPersistence, times(1)).findUniqueUrlsByPositionName(name);
    }
}