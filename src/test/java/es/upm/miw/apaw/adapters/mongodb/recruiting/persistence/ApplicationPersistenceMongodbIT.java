package es.upm.miw.apaw.adapters.mongodb.recruiting.persistence;

import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.ApplicationRepository;
import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.RecruitingSeeder;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.ApplicationEntity;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.MeetingEntity;
import es.upm.miw.apaw.adapters.mongodb.recruiting.persistance.ApplicationPersistenceMongodb;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.recruiting.Application;
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
    private ApplicationRepository applicationRepository;

    @Autowired
    private RecruitingSeeder recruitingSeeder;

    @BeforeEach
    void resetDb() {
        recruitingSeeder.deleteAll();
        recruitingSeeder.seedDatabase();
    }

    @Test
    void testReadAll() {
        List<Application> allApps = applicationPersistence.readAll();

        assertThat(allApps)
                .isNotEmpty()
                .size().isEqualTo(5);
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
    void testUpdateApplicationMeetingList() {
        UUID existingId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0030");

        Application application = applicationPersistence.readById(existingId);

        ApplicationEntity entityInDb = applicationRepository.findById(existingId).orElseThrow();
        List<MeetingEntity> meetingsInDb = entityInDb.getMeetingList();

        assertThat(meetingsInDb)
                .isNotEmpty()
                .allMatch(meeting -> meeting.getId() != null);

        // Change the URL for the meetings of the application
        List<String> updatedUrls = meetingsInDb.stream()
                .map(m -> "updated-url-" + m.getId())
                .toList();

        for (int i = 0; i < application.getMeetingList().size(); i++) {
            application.getMeetingList().get(i).setUrl(updatedUrls.get(i));
        }

        // Save the changes by Update
        Application updated = applicationPersistence.update(application);

        // Verifications
        assertThat(updated.getMeetingList())
                .isNotEmpty()
                .allMatch(meeting -> meeting.getUrl().startsWith("updated-url-"));

        ApplicationEntity reloaded = applicationRepository.findById(existingId).orElseThrow();
        assertThat(reloaded.getMeetingList())
                .isNotEmpty()
                .allMatch(meeting -> meeting.getUrl().startsWith("updated-url-"));
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