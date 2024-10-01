package es.upm.miw.apaw_practice.domain.models.basketball;

import java.time.LocalDateTime;

public class BasketMatch {

    int id;
    LocalDateTime date;
    String address;
    BasketScoreBoard scoreBoard;

    public BasketMatch(int id) {
        this.id = id;
        this.date = LocalDateTime.now();
        scoreBoard = new BasketScoreBoard();
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
}
