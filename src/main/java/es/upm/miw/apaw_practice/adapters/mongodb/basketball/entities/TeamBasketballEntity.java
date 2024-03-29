package es.upm.miw.apaw_practice.adapters.mongodb.basketball.entities;

import es.upm.miw.apaw_practice.domain.models.basketball.Pavilion;
import es.upm.miw.apaw_practice.domain.models.basketball.Player;
import es.upm.miw.apaw_practice.domain.models.basketball.Team;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document
public class TeamBasketballEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String alias;
    private Integer numPlayers;
    private String coach;
    private List<PavilionEntity> pavilionEntities;
    private List<PlayerEntity> playerEntities;

    public TeamBasketballEntity(){
        
    }
    public TeamBasketballEntity(String alias, Integer numPlayers, String coach, List<PavilionEntity> pavilionEntities, List<PlayerEntity> playerEntities) {
        this.id = UUID.randomUUID().toString();
        this.alias = alias;
        this.numPlayers = numPlayers;
        this.coach = coach;
        this.pavilionEntities = pavilionEntities;
        this.playerEntities = playerEntities;
    }

    public String getId() {
        return id;
    }
    
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(Integer numPlayers) {
        this.numPlayers = numPlayers;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public List<PavilionEntity> getPavilionEntities() {
        return pavilionEntities;
    }

    public void setPavilionEntities(List<PavilionEntity> pavilionEntities) {
        this.pavilionEntities = pavilionEntities;
    }

    public List<PlayerEntity> getPlayerEntities() {
        return playerEntities;
    }

    public void setPlayerEntities(List<PlayerEntity> playerEntities) {
        this.playerEntities = playerEntities;
    }

    public Team toTeam() {
        List<Player> players = this.playerEntities.stream()
                .map(PlayerEntity::toPlayer)
                .toList();

        List<Pavilion> pavilions = this.pavilionEntities.stream()
                .map(PavilionEntity::toPavilion)
                .toList();

        return new Team(this.alias, this.numPlayers, this.coach, players, pavilions);
    }

    @Override
    public int hashCode() {
        return this.alias.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (alias.equals(((TeamBasketballEntity) obj).alias));
    }

    @Override
    public String toString() {
        return "TeamEntity{" +
                "id='" + id + '\'' +
                ", alias='" + alias + '\'' +
                ", numPlayers='" + numPlayers + '\'' +
                ", coach=" + coach +
                ", pavilionEntities=" + pavilionEntities +
                ", playerEntities=" + playerEntities +
                '}';
    }
}