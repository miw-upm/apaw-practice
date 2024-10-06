package es.upm.miw.apaw_practice.adapters.mongodb.competition.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class OrganizationRepositoryIT {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Test
    void createAndReadOrganization() {
        assertTrue(this.organizationRepository.findAll().stream()
                .anyMatch(organization ->
                        "UEFA".equals(organization.getNameOrganization())
                ));
    }
}
