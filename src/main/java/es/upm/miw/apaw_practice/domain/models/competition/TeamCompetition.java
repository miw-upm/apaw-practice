package es.upm.miw.apaw_practice.domain.models.competition;

import java.util.ArrayList;
import java.util.List;

public class TeamCompetition {

    private String nameTeamCompetition;
    private Integer numberCompetitionWon;
    private String coachName;
    private List<PlayerTeam> playerTeams;
    private List<TeamCompetition> children;

    public TeamCompetition() {
        this.children = new ArrayList<>();
    }

    public TeamCompetition(String nameTeamCompetition, Integer numberCompetitionWon, String coachName, List<TeamCompetition> children) {
        this.nameTeamCompetition = nameTeamCompetition;
        this.numberCompetitionWon = numberCompetitionWon;
        this.coachName = coachName;
        this.playerTeams = new ArrayList<>();
        this.children = children;
    }

    public TeamCompetition(String nameTeamCompetition, Integer numberCompetitionWon, String coachName) {
        this.nameTeamCompetition = nameTeamCompetition;
        this.numberCompetitionWon = numberCompetitionWon;
        this.coachName = coachName;
        this.playerTeams = new ArrayList<>();
        this.children = null;
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

    public List<PlayerTeam> getPlayerTeams() {
        return playerTeams;
    }

    public void setPlayerTeams(List<PlayerTeam> playerTeams) {
        this.playerTeams = playerTeams;
    }

    public boolean isComposite() {
        return this.children != null;
    }

    public void add(TeamCompetition teamCompetition) {
        if (isComposite()) {
            this.children.add(teamCompetition);
        } else {
            throw new UnsupportedOperationException("Cannot add to a leaf");
        }
    }

    public void remove(TeamCompetition teamCompetition) {
        if (isComposite()) {
            this.children.remove(teamCompetition);
        } else {
            throw new UnsupportedOperationException("Cannot remove from a leaf");
        }
    }

    public List<TeamCompetition> getChildren() {
        if (isComposite()) {
            return children;
        } else {
            throw new UnsupportedOperationException("Cannot get children from a leaf");
        }
    }

    public List<PlayerTeam> getAllPlayers() {
        List<PlayerTeam> allPlayers = new ArrayList<>(playerTeams);
        if (isComposite()) {
            for (TeamCompetition child : children) {
                allPlayers.addAll(child.getAllPlayers());
            }
        }
        return allPlayers;
    }

    @Override
    public String toString() {
        return "TeamCompetition{" +
                "nameTeamCompetition='" + nameTeamCompetition + '\'' +
                ", numberCompetitionWon=" + numberCompetitionWon +
                ", coachName='" + coachName + '\'' +
                ", playerTeams=" + playerTeams +
                ", children=" + children +
                '}';
    }
}
