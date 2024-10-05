package es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities;

import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Game;
import org.springframework.data.annotation.Id;
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
    private List<Game> selectedGames;

    public PlayServiceEntity() {
        //empty for framework
    }

    public PlayServiceEntity(Integer playServiceId, BigDecimal coverFee, LocalDateTime sessionDate, List<Game> selectedGames) {
        this.playServiceId = playServiceId;
        this.coverFee = coverFee;
        this.sessionDate = sessionDate;
        this.selectedGames = selectedGames;
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

    public List<Game> getSelectedGames() {
        return selectedGames;
    }

    public void setSelectedGames(List<Game> selectedGames) {
        this.selectedGames = selectedGames;
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
                ", selectedGames=" + selectedGames +
                '}';
    }
}