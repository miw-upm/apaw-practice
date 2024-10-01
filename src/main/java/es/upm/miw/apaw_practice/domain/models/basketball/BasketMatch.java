package es.upm.miw.apaw_practice.domain.models.basketball;

import java.time.LocalDateTime;
import java.util.List;

public class BasketMatch {

    private int id;
    private LocalDateTime date;
    private String address;
    private BasketScoreBoard scoreBoard;
    private List<BasketPlayer> players;

    public BasketMatch(int id, List<BasketPlayer> players) {
        this.id = id;
        this.date = LocalDateTime.now();
        scoreBoard = new BasketScoreBoard();
        this.players = players;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BasketScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    public void setScoreBoard(BasketScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public List<BasketPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<BasketPlayer> players) {
        this.players = players;
    }
}
