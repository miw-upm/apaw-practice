package es.upm.miw.apaw_practice.domain.models.competition;

import java.util.List;

public class TeamCompetition {

    private String nameTeamCompetition;
    private Integer numberCompetitionWon;
    private String coachName;
    private List<Competition> competitions;

    public TeamCompetition() {
        // empty for framework
    }

    public TeamCompetition(String nameTeamCompetition, Integer numberCompetitionWon, String coachName, List<Competition> competitions) {
        this.nameTeamCompetition = nameTeamCompetition;
        this.numberCompetitionWon = numberCompetitionWon;
        this.coachName = coachName;
        this.competitions = competitions;
    }

    public String getNameTeamCompetition() {
        return nameTeamCompetition;
    }

    public void setNameTeamCompetition(String nameTeamCompetition) {
        this.nameTeamCompetition = nameTeamCompetition;
    }

    public Integer getNumberCompetitionWon() {
        return numberCompetitionWon;
    }

    public void setNumberCompetitionWon(Integer numberCompetitionWon) {
        this.numberCompetitionWon = numberCompetitionWon;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public List<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
    }

    @Override
    public String toString() {
        return "TeamCompetition{" +
                "nameTeamCompetition='" + nameTeamCompetition + '\'' +
                ", numberCompetitionWon=" + numberCompetitionWon +
                ", coachName='" + coachName + '\'' +
                ", competitions=" + competitions +
                '}';
    }
}
