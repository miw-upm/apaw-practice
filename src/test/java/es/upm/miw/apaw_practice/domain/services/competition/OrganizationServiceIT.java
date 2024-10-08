package es.upm.miw.apaw_practice.domain.services.competition;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.daos.OrganizationRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.entities.OrganizationEntity;
import es.upm.miw.apaw_practice.domain.models.competition.Organization;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class OrganizationServiceIT {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Test
    void testUpdateInternational() {
        Optional<OrganizationEntity> organizationEntity = this.organizationRepository.findByNameOrganization("UEFA");
        assertTrue(organizationEntity.isPresent());
        boolean oldIsInternational = organizationEntity.get().isInternational();
        this.organizationService.updateInternational(organizationEntity.get().getId());

        Optional<OrganizationEntity> newOrganizationEntity = this.organizationRepository.findByNameOrganization("UEFA");
        assertTrue(newOrganizationEntity.isPresent());

        assertEquals(oldIsInternational, !newOrganizationEntity.get().isInternational());
    }

    @Test
    void testCreateOrganization() {
         this.organizationService.createOrganization(new Organization("F.S. Barcelona", LocalDateTime.now(), false));

        Optional<OrganizationEntity> newOrganizationEntity = this.organizationRepository.findByNameOrganization("F.S. Barcelona");
        assertTrue(newOrganizationEntity.isPresent());
    }
}
