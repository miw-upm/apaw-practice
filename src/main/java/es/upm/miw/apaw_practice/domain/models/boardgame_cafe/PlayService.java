package es.upm.miw.apaw_practice.domain.models.boardgame_cafe;

import java.time.LocalDateTime;
import java.util.List;

public class PlayService {
    private Integer playServiceId;
    private Integer groupSize;
    private LocalDateTime sessionDate;
    private List<Game> selectedGames;

    public PlayService() {
        //empty for framework
    }

    public PlayService(Integer playServiceId, Integer groupSize, LocalDateTime sessionDate, List<Game> selectedGames) {
        this.playServiceId = playServiceId;
        this.groupSize = groupSize;
        this.sessionDate = sessionDate;
        this.selectedGames = selectedGames;
    }

    public Integer getPlayServiceId() {
        return playServiceId;
    }

    public void setPlayServiceId(Integer playServiceId) {
        this.playServiceId = playServiceId;
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
                ", groupSize=" + groupSize +
                ", sessionDate=" + sessionDate +
                ", selectedGames=" + selectedGames +
                '}';
    }
}