package es.upm.miw.apaw_practice.domain.models.basketball;

import java.time.LocalTime;
import java.util.List;

public class BasketScoreBoard {

    private int id;
    private LocalTime time;
    private int quarterNum;
    private List<BasketMatch> basketMatches;

    public BasketScoreBoard() {
        this.time = LocalTime.of(0,40);
    }

    public LocalTime getTime() {
        return time;
    }
    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuarterNum() {
        return quarterNum;
    }

    public void setQuarterNum(int quarterNum) {
        this.quarterNum = quarterNum;
    }

    public List<BasketMatch> getBasketMatches() {
        return basketMatches;
    }

    public void setBasketMatches(List<BasketMatch> basketMatches) {
        this.basketMatches = basketMatches;
    }
}
