package es.upm.miw.apaw_practice.domain.models.game_wow;

import java.util.Date;

public class Raid {

    private Date date;
    private String name;
    private String dificulty;
    private Integer playerNumber;
    private Boolean finish;
    private Boss boss;

    public Raid() {
        //empty for framework
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

    public Boss getBoss() {
        return boss;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }

    @Override
    public String toString() {
        return "Raid{" +
                "date=" + date +
                ", name='" + name + '\'' +
                ", dificulty='" + dificulty + '\'' +
                ", playerNumber=" + playerNumber +
                ", finish=" + finish +
                ", boss=" + boss +
                '}';
    }
}
