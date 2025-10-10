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

import java.time.LocalDate;
import java.util.Comparator;
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

    private UUID existingId;

    @BeforeEach
    void setUp() {
        recruitingSeeder.deleteAll();
        recruitingSeeder.seedDatabase();

        existingId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0030");
    }

    @Test
    void testReadByIdSuccess() {
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

    @Test
    void testReadAllApplicationsAreOrderedByCreationDate() {
        List<ApplicationEntity> allApps = applicationRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(ApplicationEntity::getCreated).reversed())
                .toList();

        assertThat(allApps)
                .isNotEmpty()
                .extracting(ApplicationEntity::getCreated)
                .containsExactly(
                        LocalDate.now(),
                        LocalDate.now().minusDays(4),
                        LocalDate.now().minusDays(5),
                        LocalDate.now().minusDays(7)
                );
    }
}