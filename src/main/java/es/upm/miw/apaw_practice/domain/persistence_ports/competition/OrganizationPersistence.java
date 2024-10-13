package es.upm.miw.apaw_practice.domain.persistence_ports.competition;

import es.upm.miw.apaw_practice.domain.models.competition.Organization;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.stream.Stream;

@Repository
public interface OrganizationPersistence {
    Organization readById(String id);

    Organization updateOrganization(Organization organization);

    Stream<Organization> readAll();

    Organization createOrganization(Organization organization);

    BigDecimal sumSalaryPlayerTeamsByNameOrganization(String nameOrganization);
}
