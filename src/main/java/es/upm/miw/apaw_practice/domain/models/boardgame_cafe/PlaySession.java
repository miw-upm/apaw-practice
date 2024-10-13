package es.upm.miw.apaw_practice.domain.models.boardgame_cafe;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class PlaySession {
    private Integer playSessionId;
    private Integer groupSize;
    private LocalDateTime sessionDate;
    private List<Game> selectedGames;
    private List<Customer> customers;

    public PlaySession() {
        //empty for framework
    }

    public PlaySession(Integer playSessionId, Integer groupSize, LocalDateTime sessionDate, List<Game> selectedGames, List<Customer> customers) {
        this.playSessionId = playSessionId;
        this.groupSize = groupSize;
        this.sessionDate = sessionDate;
        this.selectedGames = selectedGames;
        this.customers = customers;
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

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaySession that = (PlaySession) o;
        return Objects.equals(playSessionId, that.playSessionId) &&
                Objects.equals(groupSize, that.groupSize) &&
                Objects.equals(sessionDate, that.sessionDate) &&
                Objects.equals(selectedGames, that.selectedGames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playSessionId, groupSize, sessionDate, selectedGames);
    }

    @Override
    public String toString() {
        return "PlaySession{" +
                "playSessionId=" + playSessionId +
                ", groupSize=" + groupSize +
                ", sessionDate=" + sessionDate +
                ", selectedGames=" + selectedGames +
                ", customers=" + customers +
                '}';
    }
}