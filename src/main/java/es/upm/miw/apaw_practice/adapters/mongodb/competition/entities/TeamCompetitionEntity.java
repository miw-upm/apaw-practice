package es.upm.miw.apaw_practice.adapters.mongodb.competition.entities;

import es.upm.miw.apaw_practice.domain.models.competition.PlayerTeam;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.UUID;

@Document
public class TeamCompetitionEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String nameTeamCompetition;
    private Integer numberCompetitionWon;
    private String coachName;
    @DBRef
    private List<PlayerTeam> playerTeams;

    public TeamCompetitionEntity() {
        // empty for framework
    }

    public TeamCompetitionEntity(String name, Integer numberCompetitionWon, String coachName, List<PlayerTeam> playerTeams) {
        this.id = UUID.randomUUID().toString();
        this.nameTeamCompetition = name;
        this.numberCompetitionWon = numberCompetitionWon;
        this.coachName = coachName;
        this.playerTeams = playerTeams;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "TeamCompetitionEntity{" +
                "id='" + id + '\'' +
                ", nameTeamCompetition='" + nameTeamCompetition + '\'' +
                ", numberCompetitionWon=" + numberCompetitionWon +
                ", coachName='" + coachName + '\'' +
                ", playerTeams=" + playerTeams +
                '}';
    }
}
