package es.upm.miw.apaw.adapters.mongodb.recruiting.daos;

import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.ApplicationEntity;
import es.upm.miw.apaw.domain.models.recruiting.enums.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ApplicationRepositoryIT {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Test
    void testFindAll() {
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
    void testFindById() {
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
    void testApplicationHasValidPositionRelation() {
        ApplicationEntity app = applicationRepository.findById(
                UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0031")
        ).orElseThrow();

        assertThat(app.getPositionEntity()).isNotNull();
        assertThat(app.getPositionEntity().getReference()).isEqualTo(1002);
        assertThat(app.getPositionEntity().getName()).contains("CPI consultant");
    }

    @Test
    void testStatusEnumPersistsCorrectly() {
        ApplicationEntity rejected = applicationRepository.findById(
                UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0033")
        ).orElseThrow();

        assertThat(rejected.getStatus()).isEqualTo(Status.Rejected);
    }

    @Test
    void testFindByStatusMethod() {
        List<ApplicationEntity> openApps = applicationRepository.findByStatus(Status.Hired);
        assertThat(openApps).hasSize(1);
        assertThat(openApps.getFirst().getPositionEntity().getReference()).isEqualTo(1003);
    }

    @Test
    void testFindByUserMethod() {
        List<ApplicationEntity> openApps = applicationRepository.findByUser(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"));
        assertThat(openApps).hasSize(1);
        assertThat(openApps.getFirst().getPositionEntity().getReference()).isEqualTo(1003);
        assertThat(openApps.getFirst().getPositionEntity().getNumVacancies()).isEqualTo(2);
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
}