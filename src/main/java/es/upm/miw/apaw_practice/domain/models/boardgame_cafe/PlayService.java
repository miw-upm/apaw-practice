package es.upm.miw.apaw_practice.domain.models.boardgame_cafe;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PlayService {
    private int playServiceId;
    private BigDecimal coverFee;
    private LocalDateTime sessionDate;
    private List<Game> selectedGames;

    public PlayService() {
        //empty for framework
    }

    public PlayService(int playServiceId, BigDecimal coverFee, LocalDateTime sessionDate, List<Game> selectedGames) {
        this.playServiceId = playServiceId;
        this.coverFee = coverFee;
        this.sessionDate = sessionDate;
        this.selectedGames = selectedGames;
    }

    public int getPlayServiceId() {
        return playServiceId;
    }

    public void setPlayServiceId(int playServiceId) {
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
    public String toString() {
        return "PlayService{" +
                "playServiceId=" + playServiceId +
                ", coverFee=" + coverFee +
                ", sessionDate=" + sessionDate +
                ", selectedGames=" + selectedGames +
                '}';
    }
}