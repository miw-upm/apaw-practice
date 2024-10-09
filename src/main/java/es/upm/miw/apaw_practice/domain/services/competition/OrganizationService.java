package es.upm.miw.apaw_practice.domain.services.competition;

import es.upm.miw.apaw_practice.domain.models.competition.Organization;
import es.upm.miw.apaw_practice.domain.persistence_ports.competition.OrganizationPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrganizationService {

    private final OrganizationPersistence organizationPersistence;

    @Autowired
    public OrganizationService(OrganizationPersistence organizationPersistence) {
        this.organizationPersistence = organizationPersistence;
    }

    public Organization updateInternational(String id) {
        Organization organization = this.organizationPersistence.readById(id);
        organization.setInternational(!organization.isInternational());
        return this.organizationPersistence.updateOrganization(organization);
    }

    public Organization createOrganization(Organization organization) {
        return this.organizationPersistence.createOrganization(organization);
    }

    public BigDecimal getSumSalaryPlayerTeamsByNameOrganization(String nameOrganization) {
        return this.organizationPersistence.sumSalaryPlayerTeamsByNameOrganization(nameOrganization);
    }
}
