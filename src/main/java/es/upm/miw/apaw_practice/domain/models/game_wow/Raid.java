package es.upm.miw.apaw_practice.domain.models.game_wow;

import java.util.Date;
import java.util.List;

public class Raid {

    private String id;
    private Date date;
    private String name;
    private String dificulty;
    private Integer playerNumber;
    private Boolean finish;
    private List<Boss> bossList;

    public Raid() {
        //empty for framework
    }

    public Raid(Date date, String name, String dificulty, Integer playerNumber, Boolean finish, List<Boss> bossList) {
        this.date = date;
        this.name = name;
        this.dificulty = dificulty;
        this.playerNumber = playerNumber;
        this.finish = finish;
        this.bossList = bossList;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDificulty() {
        return dificulty;
    }

    public void setDificulty(String dificulty) {
        this.dificulty = dificulty;
    }

    public Integer getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(Integer playerNumber) {
        this.playerNumber = playerNumber;
    }

    public Boolean getFinish() {
        return finish;
    }

    public void setFinish(Boolean finish) {
        this.finish = finish;
    }

    public List<Boss> getBossList() {
        return bossList;
    }

    public void setBossList(List<Boss> bossList) {
        this.bossList = bossList;
    }

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    @Override
    public String toString() {
        return "Raid{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", dificulty='" + dificulty + '\'' +
                ", playerNumber=" + playerNumber +
                ", finish=" + finish +
                ", bossList=" + bossList +
                '}';
    }
}
