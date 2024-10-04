package es.upm.miw.apaw_practice.adapters.mongodb.competition.entities;

import es.upm.miw.apaw_practice.domain.models.competition.Organization;
import es.upm.miw.apaw_practice.domain.models.competition.TeamCompetition;
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
    private List<TeamCompetition> teamCompetitions;
    @DBRef
    private Organization organization;

    public CompetitionEntity() {
        // empty for framework
    }

    public CompetitionEntity(String id, String nameCompetition, LocalDate startDate, LocalDate endDate, List<TeamCompetition> teamCompetitions, Organization organization) {
        this.id = UUID.randomUUID().toString();
        this.nameCompetition = nameCompetition;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teamCompetitions = teamCompetitions;
        this.organization = organization;
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

    public List<TeamCompetition> getTeamCompetitions() {
        return teamCompetitions;
    }

    public void setTeamCompetitions(List<TeamCompetition> teamCompetitions) {
        this.teamCompetitions = teamCompetitions;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        return "CompetitionEntity{" +
                "id='" + id + '\'' +
                ", nameCompetition='" + nameCompetition + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", teamCompetitions=" + teamCompetitions +
                ", organization=" + organization +
                '}';
    }
}
