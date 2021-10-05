package es.upm.miw.apaw_practice.domain.models.game_wow;

import java.util.Date;

public class Raid {

    private Date date;
    private String name;
    private String dificulty;
    private int playerNumber;
    private boolean finish;
    private Boss boss;

    public Raid() {
        //empty for framework
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
