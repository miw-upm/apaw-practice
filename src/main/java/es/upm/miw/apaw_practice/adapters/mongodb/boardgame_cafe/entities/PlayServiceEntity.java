package es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Document
public class PlayServiceEntity {
    @Id
    private Integer playServiceId;
    private BigDecimal coverFee;
    private LocalDateTime sessionDate;
    @DBRef
    private List<GameEntity> selectedGamesEntities;

    public PlayServiceEntity() {
        //empty for framework
    }

    public PlayServiceEntity(Integer playServiceId, BigDecimal coverFee, LocalDateTime sessionDate, List<GameEntity> selectedGamesEntities) {
        this.playServiceId = playServiceId;
        this.coverFee = coverFee;
        this.sessionDate = sessionDate;
        this.selectedGamesEntities = selectedGamesEntities;
    }

    public Integer getPlayServiceId() {
        return playServiceId;
    }

    public void setPlayServiceId(Integer playServiceId) {
        this.playServiceId = playServiceId;
    }

    public BigDecimal getCoverFee() {
        return coverFee;
    }

    public void setCoverFee(BigDecimal coverFee) {
        this.coverFee = coverFee;
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
        return playServiceId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (playServiceId.equals(((PlayServiceEntity) obj).playServiceId));
    }

    @Override
    public String toString() {
        return "PlayServiceEntity{" +
                "playServiceId=" + playServiceId +
                ", coverFee=" + coverFee +
                ", sessionDate=" + sessionDate +
                ", selectedGamesEntities=" + selectedGamesEntities +
                '}';
    }
}