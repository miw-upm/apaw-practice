package es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
public class PlaySessionEntity {
    @Id
    private Integer playSessionId;
    private Integer groupSize;
    private LocalDateTime sessionDate;
    @DBRef
    private List<GameEntity> selectedGamesEntities;

    public PlaySessionEntity() {
        //empty for framework
    }

    public PlaySessionEntity(Integer playSessionId, Integer groupSize, LocalDateTime sessionDate, List<GameEntity> selectedGamesEntities) {
        this.playSessionId = playSessionId;
        this.groupSize = groupSize;
        this.sessionDate = sessionDate;
        this.selectedGamesEntities = selectedGamesEntities;
    }

    public Integer getPlaySessionId() {
        return playSessionId;
    }

    public void setPlaySessionId(Integer playSessionId) {
        this.playSessionId = playSessionId;
    }

    public Integer getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(Integer groupSize) {
        this.groupSize = groupSize;
    }

    public LocalDateTime getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDateTime sessionDate) {
        this.sessionDate = sessionDate;
    }

    public List<GameEntity> getSelectedGamesEntities() {
        return selectedGamesEntities;
    }

    public void setSelectedGames(List<GameEntity> selectedGamesEntities) {
        this.selectedGamesEntities = selectedGamesEntities;
    }

    @Override
    public int hashCode() {
        return playSessionId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (playSessionId.equals(((PlaySessionEntity) obj).playSessionId));
    }

    @Override
    public String toString() {
        return "PlaySessionEntity{" +
                "playSessionId=" + playSessionId +
                ", groupSize=" + groupSize +
                ", sessionDate=" + sessionDate +
                ", selectedGamesEntities=" + selectedGamesEntities +
                '}';
    }
}