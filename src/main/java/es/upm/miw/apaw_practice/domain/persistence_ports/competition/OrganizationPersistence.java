package es.upm.miw.apaw_practice.domain.persistence_ports.competition;

import es.upm.miw.apaw_practice.domain.models.competition.Organization;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface OrganizationPersistence {
    Organization readById(String id);

    Organization updateOrganization(Organization organization);

    Stream<Organization> readAll();
}
