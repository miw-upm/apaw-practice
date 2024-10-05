package es.upm.miw.apaw_practice.adapters.mongodb.competition.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Document
public class CompetitionEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String nameCompetition;
    private LocalDate startDate;
    private LocalDate endDate;
    @DBRef
    private List<TeamCompetitionEntity> teamCompetitionsEntity;
    @DBRef
    private OrganizationEntity organizationEntity;

    public CompetitionEntity() {
        // empty for framework
    }

    public CompetitionEntity(String nameCompetition, LocalDate startDate, LocalDate endDate, List<TeamCompetitionEntity> teamCompetitionsEntity, OrganizationEntity organizationEntity) {
        this.id = UUID.randomUUID().toString();
        this.nameCompetition = nameCompetition;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teamCompetitionsEntity = teamCompetitionsEntity;
        this.organizationEntity = organizationEntity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameCompetition() {
        return nameCompetition;
    }

    public void setNameCompetition(String nameCompetition) {
        this.nameCompetition = nameCompetition;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<TeamCompetitionEntity> getTeamCompetitionsEntity() {
        return teamCompetitionsEntity;
    }

    public void setTeamCompetitionsEntity(List<TeamCompetitionEntity> teamCompetitionsEntity) {
        this.teamCompetitionsEntity = teamCompetitionsEntity;
    }

    public OrganizationEntity getOrganizationEntity() {
        return organizationEntity;
    }

    public void setOrganizationEntity(OrganizationEntity organizationEntity) {
        this.organizationEntity = organizationEntity;
    }

    @Override
    public String toString() {
        return "CompetitionEntity{" +
                "id='" + id + '\'' +
                ", nameCompetition='" + nameCompetition + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", teamCompetitions=" + teamCompetitionsEntity +
                ", organization=" + organizationEntity +
                '}';
    }
}
