package es.upm.miw.apaw_practice.adapters.mongodb.competition.entities;

import es.upm.miw.apaw_practice.domain.models.competition.PlayerTeam;
import es.upm.miw.apaw_practice.domain.models.competition.TeamCompetition;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Document
public class TeamCompetitionEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String nameTeamCompetition;
    private Integer numberCompetitionWon;
    private String coachName;
    @DBRef
    private List<PlayerTeamEntity> playerTeamsEntity;

    public TeamCompetitionEntity() {
        // empty for framework
    }

    public TeamCompetitionEntity(String name, Integer numberCompetitionWon, String coachName, List<PlayerTeamEntity> playerTeamsEntity) {
        this.id = UUID.randomUUID().toString();
        this.nameTeamCompetition = name;
        this.numberCompetitionWon = numberCompetitionWon;
        this.coachName = coachName;
        this.playerTeamsEntity = playerTeamsEntity;
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

    public List<PlayerTeamEntity> getPlayerTeamsEntity() {
        return playerTeamsEntity;
    }

    public void setPlayerTeamsEntity(List<PlayerTeamEntity> playerTeams) {
        this.playerTeamsEntity = playerTeams;
    }

    public TeamCompetition toTeamCompetition() {
        TeamCompetition teamCompetition = new TeamCompetition();
        BeanUtils.copyProperties(this, teamCompetition, "playerTeamsEntity");
        List<PlayerTeam> playersTeam = this.playerTeamsEntity.stream()
                .map(PlayerTeamEntity::toPlayerTeam)
                .collect(Collectors.toList());
        teamCompetition.setPlayerTeams(playersTeam);
        return teamCompetition;
    }

    @Override
    public String toString() {
        return "TeamCompetitionEntity{" +
                "id='" + id + '\'' +
                ", nameTeamCompetition='" + nameTeamCompetition + '\'' +
                ", numberCompetitionWon=" + numberCompetitionWon +
                ", coachName='" + coachName + '\'' +
                ", playerTeams=" + playerTeamsEntity +
                '}';
    }
}
