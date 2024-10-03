package es.upm.miw.apaw_practice.domain.models.competition;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Competition {

    private String nameCompetition;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<TeamCompetition> teamCompetitions;
    private Organization organization;

    public Competition() {
        // empty for framework
    }

    public Competition(String nameCompetition, LocalDate startDate, LocalDate endDate, List<TeamCompetition> teamCompetitions, Organization organization) {
        this.nameCompetition = nameCompetition;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teamCompetitions = teamCompetitions;
        this.organization = organization;
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

    public List<TeamCompetition> getTeamCompetitions() {
        return teamCompetitions;
    }

    public void setTeamCompetitions(List<TeamCompetition> teamCompetitions) {
        this.teamCompetitions = teamCompetitions;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "nameCompetition='" + nameCompetition + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", teamCompetitions=" + teamCompetitions +
                ", organization=" + organization +
                '}';
    }
}
