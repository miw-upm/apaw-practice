package es.upm.miw.apaw_practice.adapters.mongodb.competition.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.competition.daos.CompetitionRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.daos.OrganizationRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.entities.CompetitionEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.entities.OrganizationEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.competition.Organization;
import es.upm.miw.apaw_practice.domain.persistence_ports.competition.OrganizationPersistence;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository("organizationPersistence")
public class OrganizationPersistenceMongodb implements OrganizationPersistence {

    private final OrganizationRepository organizationRepository;
    private final CompetitionRepository competitionRepository;

    @Autowired
    public OrganizationPersistenceMongodb(OrganizationRepository organizationRepository, CompetitionRepository competitionRepository) {
        this.organizationRepository = organizationRepository;
        this.competitionRepository = competitionRepository;
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

    @Override
    public Organization createOrganization(Organization organization) {
        return this.organizationRepository.save(new OrganizationEntity(organization)).toOrganization();
    }

    @Override
    public BigDecimal sumSalaryPlayerTeamsByNameOrganization(String nameOrganization) {
        OrganizationEntity organizationEntity = this.organizationRepository
                .findByNameOrganization(nameOrganization)
                .orElseThrow(() -> new NotFoundException("Organization with name " + nameOrganization + " not found"));

        List<CompetitionEntity> competitions = this.competitionRepository.findAll().stream()
                .filter(comp -> comp.getOrganizationEntity().getId().equals(organizationEntity.getId()))
                .toList();

        return competitions.stream()
                .flatMap(comp -> comp.getTeamCompetitionsEntity().stream())
                .flatMap(team -> team.getPlayerTeamsEntity().stream())
                .map(player -> Optional.ofNullable(player.getSalary()).orElse(BigDecimal.ZERO))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
