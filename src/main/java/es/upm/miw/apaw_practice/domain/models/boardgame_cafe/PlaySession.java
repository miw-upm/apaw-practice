package es.upm.miw.apaw_practice.domain.models.boardgame_cafe;

import java.time.LocalDateTime;
import java.util.List;

public class PlaySession {
    private Integer playSessionId;
    private Integer groupSize;
    private LocalDateTime sessionDate;
    private List<Game> selectedGames;

    public PlaySession() {
        //empty for framework
    }

    public PlaySession(Integer playSessionId, Integer groupSize, LocalDateTime sessionDate, List<Game> selectedGames) {
        this.playSessionId = playSessionId;
        this.groupSize = groupSize;
        this.sessionDate = sessionDate;
        this.selectedGames = selectedGames;
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

    public List<Game> getSelectedGames() {
        return selectedGames;
    }

    public void setSelectedGames(List<Game> selectedGames) {
        this.selectedGames = selectedGames;
    }

    @Override
    public String toString() {
        return "PlaySession{" +
                "playSessionId=" + playSessionId +
                ", groupSize=" + groupSize +
                ", sessionDate=" + sessionDate +
                ", selectedGames=" + selectedGames +
                '}';
    }
}