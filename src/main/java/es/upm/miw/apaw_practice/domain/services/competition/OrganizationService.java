package es.upm.miw.apaw_practice.domain.services.competition;

import es.upm.miw.apaw_practice.domain.models.competition.Organization;
import es.upm.miw.apaw_practice.domain.persistence_ports.competition.OrganizationPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
