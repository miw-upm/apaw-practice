package es.upm.miw.apaw_practice.domain.models.videogame;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Objects;

public class Player {
    private String playerName;
    private int age;
    private boolean experiencePlayer;
    private LocalDate birthday;
    private Console console;

    private  Player(){
        //empty for framework
    }

    public Player(String playerName, int age, boolean experiencePlayer, LocalDate birthday, Console console){
        this.playerName = playerName;
        this.age = age;
        this.experiencePlayer = experiencePlayer;
        this.birthday = birthday;
        this.console = console;
    }

    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String player) {
        this.playerName = playerName;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(playerName, player.playerName) && Objects.equals(age, player.age) && Objects.equals(experiencePlayer, player.experiencePlayer) && Objects.equals(birthday, player.birthday) && Objects.equals(console, player.console);
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerName='" + playerName + '\'' +
                ", age=" + age +
                ", experiencePlayer=" + experiencePlayer +
                ", birthday=" + birthday +
                ", console=" + console +
                '}';
    }
}
