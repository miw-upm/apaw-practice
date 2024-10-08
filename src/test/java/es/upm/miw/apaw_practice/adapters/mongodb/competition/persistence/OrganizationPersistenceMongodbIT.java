package es.upm.miw.apaw_practice.adapters.mongodb.competition.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.entities.OrganizationEntity;
import es.upm.miw.apaw_practice.domain.models.competition.Organization;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

@TestConfig
class OrganizationPersistenceMongodbIT {

    @Autowired
    private OrganizationPersistenceMongodb organizationPersistenceMongodb;

    @Test
    void testReadById() {
        Optional<Organization> organization = this.organizationPersistenceMongodb.readAll()
                .filter(org -> "UEFA".equals(org.getNameOrganization()))
                .findFirst();
        assertTrue(organization.isPresent());
    }

    @Test
    void testUpdateOrganization() {
        Optional<Organization> organization = this.organizationPersistenceMongodb.readAll()
                .filter(org -> "UEFA".equals(org.getNameOrganization()))
                .findFirst();
        assertTrue(organization.isPresent());
        LocalDateTime newLocalDateTime = LocalDateTime.of(1999, 9, 9, 12, 12);
        organization.get().setCreationDateOrganization(newLocalDateTime);
        this.organizationPersistenceMongodb.updateOrganization(organization.get());

        Optional<Organization> newOrganization = this.organizationPersistenceMongodb.readAll()
                .filter(org -> "UEFA".equals(org.getNameOrganization()))
                .findFirst();
        assertTrue(newOrganization.isPresent());
        assertEquals(newLocalDateTime, newOrganization.get().getCreationDateOrganization());
    }

    @Test
    void testCreateOrganization() {
        this.organizationPersistenceMongodb.createOrganization(new Organization("F.S. Barcelona", LocalDateTime.now(), false));

        Optional<Organization> newOrganizationEntity = this.organizationPersistenceMongodb
                .readAll()
                .filter(comp -> comp.getNameOrganization().equals("F.S. Barcelona"))
                .findFirst();
        assertTrue(newOrganizationEntity.isPresent());
    }
}
