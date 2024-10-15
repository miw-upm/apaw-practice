package es.upm.miw.apaw_practice.domain.models.basketball;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BasketSeason {

    private int id;
    private int startYear;
    private int endYear;
    private String league;
    private List<BasketMatch> basketMatches;

    public BasketSeason() {
    }

    public BasketSeason(int id, int startYear, int endYear, String league, List<BasketMatch> basketMatches) {
        this.id = id;
        this.startYear = startYear;
        this.endYear = endYear;
        this.league = league;
        this.basketMatches = basketMatches;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public List<BasketMatch> getBasketMatches() {
        return basketMatches;
    }

    public void setBasketMatches(List<BasketMatch> basketMatches) {
        this.basketMatches = basketMatches;
    }

    public void doDefault() {
        if (Objects.isNull(basketMatches)) {
            this.basketMatches = Collections.emptyList();
        }
    }
}
