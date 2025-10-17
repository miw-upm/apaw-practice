package es.upm.miw.apaw.adapters.mongodb.recruiting.persistence;

import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.RecruitingSeeder;
import es.upm.miw.apaw.adapters.mongodb.recruiting.persistance.ApplicationPersistenceMongodb;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.recruiting.Application;
import es.upm.miw.apaw.domain.models.recruiting.Attendee;
import es.upm.miw.apaw.domain.models.recruiting.Meeting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class ApplicationPersistenceMongodbIT {

    @Autowired
    private ApplicationPersistenceMongodb applicationPersistence;

    @Autowired
    private RecruitingSeeder recruitingSeeder;

    @BeforeEach
    void resetDb() {
        recruitingSeeder.deleteAll();
        recruitingSeeder.seedDatabase();
    }

    @Test
    void testReadByIdSuccess() {
        UUID existingId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0030");

        Application application = applicationPersistence.readById(existingId);

        assertThat(application).isNotNull();
        assertThat(application.getId()).isEqualTo(existingId);
        assertThat(application.getMeetingList()).isNotEmpty();
    }

    @Test
    void testReadByIdNotFound() {
        UUID randomId = UUID.randomUUID();

        assertThrows(NotFoundException.class, () -> applicationPersistence.readById(randomId));
    }

    @Test
    void testUpdateMeetings() {
        UUID existingId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0030");

        Application application = applicationPersistence.readById(existingId);

        List<Meeting> meetings = application.getMeetingList();
        for (int i = 0; i < meetings.size(); i++) {
            Meeting meeting = meetings.get(i);
            meeting.setUrl("updated-global-url-" + i);

            List<Attendee> newAttendees = List.of(
                    Attendee.builder()
                            .fullName("Global Test Attendee " + i)
                            .emailAddress("global.attendee" + i + "@test.com")
                            .phoneNumber("+349000000" + i)
                            .build()
            );
            meeting.setAttendees(newAttendees);
        }

        Application updated = applicationPersistence.update(application);

        assertThat(updated.getId()).isEqualTo(existingId);
        assertThat(updated.getStatus()).isEqualTo(application.getStatus());
        assertThat(updated.getReferral()).isEqualTo(application.getReferral());

        assertThat(updated.getPosition()).isNotNull();
        assertThat(updated.getPosition().getReference()).isEqualTo(1001);
        assertThat(updated.getPosition().getName()).contains("ABAP developer");
        assertThat(updated.getPosition().getAnnualSalary()).isEqualByComparingTo(new BigDecimal("52000.00"));
        assertThat(updated.getPosition().getBonusSalary()).isEqualByComparingTo(new BigDecimal("5200.00"));
        assertThat(updated.getPosition().getNumVacancies()).isEqualTo(3);

        assertThat(updated.getMeetingList())
                .isNotEmpty()
                .hasSize(application.getMeetingList().size());

        for (int i = 0; i < updated.getMeetingList().size(); i++) {
            var meeting = updated.getMeetingList().get(i);
            assertThat(meeting.getUrl()).isEqualTo("updated-global-url-" + i);
            assertThat(meeting.getAttendees())
                    .isNotEmpty()
                    .hasSize(1);

            var attendee = meeting.getAttendees().getFirst();
            assertThat(attendee.getFullName()).isEqualTo("Global Test Attendee " + i);
            assertThat(attendee.getEmailAddress()).isEqualTo("global.attendee" + i + "@test.com");
            assertThat(attendee.getPhoneNumber()).isEqualTo("+349000000" + i);
        }
    }

    @Test
    void testUpdateNonExistingApplicationThrows() {
        Application fakeApp = Application.builder().id(UUID.randomUUID()).build();

        assertThrows(NotFoundException.class, () -> applicationPersistence.update(fakeApp));
    }

    // --- SEARCHES endpoints test ------------------------------------------------------

    // Testing Search 1 #1269

    @Test
    void testFindAccumulatedAnnualSalary1() {
        String attendeeName = "Markus Urbanietz";

        assertThat(applicationPersistence.findAccumulatedAnnualSalaryByFullName(attendeeName))
                .isEqualByComparingTo(new BigDecimal("176000.00"));
    }

    @Test
    void testFindAccumulatedAnnualSalaryZero() {
        String attendeeName = "Karolyn Sanz";
        // No meeting -> 0

        assertThat(applicationPersistence.findAccumulatedAnnualSalaryByFullName(attendeeName))
                .isEqualByComparingTo(new BigDecimal("0.00"));
    }

    @Test
    void testFindAccumulatedAnnualSalaryNotFound() {
        String attendeeName = "Not-in Seeder";

        assertThat(applicationPersistence.findAccumulatedAnnualSalaryByFullName(attendeeName))
                .isEqualByComparingTo(new BigDecimal("0.00"));
    }

    // Testing Search 2 #1270

    @Test
    void testFindUniqueURLByName() {
        String positionName = "CPI Consultant";
        List<String> urls = applicationPersistence.findUniqueUrlsByPositionName(positionName);
        assertThat(urls).isNotEmpty()
                .contains("//url-for-meeting-4", "//url-for-meeting-5", "//url-for-meeting-6")
                .hasSize(3);
    }

    @Test
    void testFindUniqueURLByNameNoURL() {
        String positionName = "Manager HCM";
        List<String> urls = applicationPersistence.findUniqueUrlsByPositionName(positionName);
        assertThat(urls).isEmpty();
    }

    @Test
    void testFindUniqueURLByNameNotFound() {
        String positionName = "PositionNotFound";
        List<String> urls = applicationPersistence.findUniqueUrlsByPositionName(positionName);
        assertThat(urls).isEmpty();
    }
}