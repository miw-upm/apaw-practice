package es.upm.miw.apaw.adapters.mongodb.recruiting.daos;

import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.ApplicationEntity;
import es.upm.miw.apaw.domain.models.recruiting.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ApplicationRepositoryIT {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private RecruitingSeeder recruitingSeeder;

    @BeforeEach
    void setUp() {
        recruitingSeeder.deleteAll();
        recruitingSeeder.seedDatabase();
    }

    @Test
    void testFindAll_SeededApplicationsExist() {
        List<ApplicationEntity> applications = this.applicationRepository.findAll();

        assertThat(applications)
                .hasSize(4)
                .extracting(ApplicationEntity::getStatus)
                .contains(Status.Open, Status.Hired);

        assertThat(applications)
                .extracting(a -> a.getPositionEntity().getReference())
                .contains(1001);
    }

    @Test
    void testFindById_ExistingSeededApplication() {
        UUID id = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0030");

        Optional<ApplicationEntity> optionalApp = this.applicationRepository.findById(id);

        assertThat(optionalApp).isPresent();
        ApplicationEntity application = optionalApp.get();

        assertThat(application.getStatus()).isEqualTo(Status.Open);
        assertThat(application.getReferral()).isTrue();
        assertThat(application.getPositionEntity().getReference()).isEqualTo(1001);
        assertThat(application.getMeetingList()).hasSize(2);
        assertThat(application.getMeetingList().getFirst().getUrl()).contains("url-for-meeting");
    }

    @Test
    void testFindByStatus() {
        List<ApplicationEntity> hiredApplications = this.applicationRepository.findAll().stream()
                .filter(app -> app.getStatus() == Status.Hired)
                .toList();

        assertThat(hiredApplications).isNotEmpty();
        assertThat(hiredApplications.getFirst().getPositionEntity().getReference()).isEqualTo(1003);
    }

    @Test
    void testApplicationHasValidPositionRelation() {
        ApplicationEntity app = applicationRepository.findById(
                UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0031")
        ).orElseThrow();

        assertThat(app.getPositionEntity()).isNotNull();
        assertThat(app.getPositionEntity().getReference()).isEqualTo(1002);
        assertThat(app.getPositionEntity().getName()).contains("CPI consultant");
    }

    @Test
    void testApplicationHasMeetingsLinked() {
        ApplicationEntity hiredApp = applicationRepository.findById(
                UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0032")
        ).orElseThrow();

        assertThat(hiredApp.getMeetingList()).isNotEmpty();
        assertThat(hiredApp.getMeetingList()).hasSize(3);
        assertThat(hiredApp.getMeetingList())
                .extracting(m -> m.getUrl())
                .anyMatch(url -> url.contains("meeting-4"));
    }

    @Test
    void testDeleteApplicationDoesNotRemoveRelatedEntities() {
        UUID appId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0030");
        applicationRepository.deleteById(appId);

        assertThat(applicationRepository.findById(appId)).isEmpty();

        // Position and meetings must exist as only Application is deleted at this test level
        List<ApplicationEntity> remaining = applicationRepository.findAll();
        assertThat(remaining).hasSize(3);
    }

    @Test
    void testFindAllByOrderByCreatedAsc_ReturnsSortedApplications() {
        List<ApplicationEntity> sortedApps = applicationRepository.findAllByOrderByCreatedAsc();

        List<LocalDate> creationDates = sortedApps.stream()
                .map(ApplicationEntity::getCreated)
                .toList();

        assertThat(creationDates).isSortedAccordingTo(LocalDate::compareTo);
    }

    @Test
    void testStatusEnumPersistsCorrectly() {
        ApplicationEntity rejected = applicationRepository.findById(
                UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0033")
        ).orElseThrow();

        assertThat(rejected.getStatus()).isEqualTo(Status.Rejected);
    }

    @Test
    void testUserCanHaveMultipleApplications() {
        UUID userId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000");

        List<ApplicationEntity> userApps = applicationRepository.findAll().stream()
                .filter(app -> app.getUser().equals(userId))
                .toList();

        assertThat(userApps).hasSize(2); // According to Seeder, this user has 2 applications.
    }

    @Test
    void testFindByStatusMethod() {
        List<ApplicationEntity> openApps = applicationRepository.findByStatus(Status.Open);
        assertThat(openApps).hasSize(1);
        assertThat(openApps.getFirst().getPositionEntity().getReference()).isEqualTo(1001);
    }

    @Test
    void testFindByUserMethod() {
        List<ApplicationEntity> openApps = applicationRepository.findByUser(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"));
        assertThat(openApps).hasSize(1);
        assertThat(openApps.getFirst().getPositionEntity().getReference()).isEqualTo(1003);
        assertThat(openApps.getFirst().getPositionEntity().getNumVacancies()).isEqualTo(2);
    }
}