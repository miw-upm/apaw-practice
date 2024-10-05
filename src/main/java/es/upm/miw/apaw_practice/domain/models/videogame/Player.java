package es.upm.miw.apaw_practice.domain.models.videogame;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public class Player {
    private String player;
    private int age;
    private boolean experiencePlayer;
    private LocalDate birthday;
    private Console console;

    private  Player(){
        //empty for framework
    }

    public Player(String player, int age, boolean experiencePlayer, LocalDate birthday, Console console){
        this.player = player;
        this.age = age;
        this.experiencePlayer = experiencePlayer;
        this.birthday = birthday;
        this.console = console;
    }

    public String getPlayer() {
        return player;
    }
    public void setPlayer(String player) {
        this.player = player;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public boolean getExperiencePlayer() {
        return experiencePlayer;
    }
    public void setExperiencePlayer(boolean experiencePlayer) {
        this.experiencePlayer = experiencePlayer;
    }
    public LocalDate getBirthday() {
        return birthday;
    }
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    public Console getConsole() {
        return console;
    }
    public void setConsole(Console console) {
        this.console = console;
    }

    @Override
    public String toString() {
        return "Player{" +
                "player='" + player + '\'' +
                ", age=" + age +
                ", experiencePlayer=" + experiencePlayer +
                ", birthday=" + birthday +
                ", console=" + console +
                '}';
    }
}
