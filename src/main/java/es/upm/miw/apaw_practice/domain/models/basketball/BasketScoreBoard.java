package es.upm.miw.apaw_practice.domain.models.basketball;

import java.time.LocalTime;
import java.util.Map;

public class BasketScoreBoard {

    LocalTime time;
    Map<Integer,Integer> pointsByTeam;
    Map<BasketPlayer,Integer> foultsByPlayer;

    public BasketScoreBoard() {
        this.time = LocalTime.of(0,40);
    }

    public LocalTime getTime() {
        return time;
    }
    public void setTime(LocalTime time) {
        this.time = time;
    }
    public Map<Integer,Integer> getPointsByTeam() {
        return pointsByTeam;
    }
    public void setPointsByTeam(Map<Integer,Integer> pointsByTeam) {
        this.pointsByTeam = pointsByTeam;
    }
    public Map<BasketPlayer,Integer> getFoultsByPlayer() {
        return foultsByPlayer;
    }
    public void setFoultsByPlayer(Map<BasketPlayer,Integer> foultsByPlayer) {
        this.foultsByPlayer = foultsByPlayer;
    }
}