package es.upm.miw.apaw_practice.adapters.mongodb.football.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class MatchEntity {
    @Id
    private String id;
    private LocalDateTime date;
    private String weather;
    @DBRef
    private PrincipalRefereeEntity principalRefereeEntity;
    private List<PlayerEntity> playerEntities;
    private Integer round;

    public MatchEntity() {
        //Empty for framework
    }

    public MatchEntity(LocalDateTime date, String weather, Integer round, PrincipalRefereeEntity principalRefereeEntity, List<PlayerEntity> playerEntities) {
        this.id = UUID.randomUUID().toString();
        this.date = date;
        this.weather = weather;
        this.round = round;
        this.principalRefereeEntity = principalRefereeEntity;
        this.playerEntities = playerEntities;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PrincipalRefereeEntity getPrincipalRefereeEntity() {
        return principalRefereeEntity;
    }

    public void setPrincipalRefereeEntity(PrincipalRefereeEntity principalRefereeEntity) {
        this.principalRefereeEntity = principalRefereeEntity;
    }

    public List<PlayerEntity> getPlayerEntities() {
        return playerEntities;
    }

    public void setPlayerEntities(List<PlayerEntity> playerEntities) {
        this.playerEntities = playerEntities;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((MatchEntity) obj).id));
    }

    @Override
    public String toString() {
        return "Match{" +
                "date=" + date +
                ", weather=" + weather +
                ", round=" + round +
                '}';
    }
}
