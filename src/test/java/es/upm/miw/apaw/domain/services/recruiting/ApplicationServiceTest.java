package es.upm.miw.apaw.domain.services.recruiting;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.recruiting.Application;
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
                .build();

        BDDMockito.given(this.applicationPersistence.readById(applicationId)).willReturn(application);

        List<Meeting> newMeetings = List.of(
                Meeting.builder()
                        .date(LocalDateTime.of(2025, 10, 20, 9, 0))
                        .url("https://meet.company.com/new-meeting-1")
                        .build(),
                Meeting.builder()
                        .date(LocalDateTime.of(2025, 10, 21, 11, 30))
                        .url("https://meet.company.com/new-meeting-2")
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
}