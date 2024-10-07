package es.upm.miw.apaw_practice.adapters.mongodb.competition.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.competition.daos.OrganizationRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.entities.OrganizationEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.competition.Organization;
import es.upm.miw.apaw_practice.domain.persistence_ports.competition.OrganizationPersistence;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("organizationPersistence")
public class OrganizationPersistenceMongodb implements OrganizationPersistence {

    private final OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationPersistenceMongodb(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }


    @Override
    public Organization readById(String id) {
        return this.organizationRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Organization with id " + id + " not found"))
                .toOrganization();
    }

    @Override
    public Organization updateOrganization(Organization organization) {
        OrganizationEntity organizationEntity = this.organizationRepository
                .findByNameOrganization(organization.getNameOrganization())
                .orElseThrow(() -> new NotFoundException("Organization with name " + organization.getNameOrganization() + " not found"));

        BeanUtils.copyProperties(organization, organizationEntity, "id");
        return this.organizationRepository.save(organizationEntity).toOrganization();
    }

    @Override
    public Stream<Organization> readAll() {
        return this.organizationRepository.findAll().stream()
                .map(OrganizationEntity::toOrganization);
    }
}
