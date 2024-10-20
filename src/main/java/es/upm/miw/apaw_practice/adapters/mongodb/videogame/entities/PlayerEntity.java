package es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities;

import es.upm.miw.apaw_practice.domain.models.videogame.Player;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document
public class PlayerEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String playerName;
    private Integer age;
    private Boolean experiencePlayer;
    private LocalDate birthday;
    @DBRef
    private ConsoleEntity consoleEntity;

    public PlayerEntity() {
        //empty for framework
    }
    public PlayerEntity(String playerName, Integer age, Boolean experiencePlayer, LocalDate birthday, ConsoleEntity consoleEntity){
        this.id = UUID.randomUUID().toString();
        this.playerName = playerName;
        this.age = age;
        this.experiencePlayer = experiencePlayer;
        this.birthday = birthday;
        this.consoleEntity = consoleEntity;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public Boolean getExperiencePlayer() {
        return experiencePlayer;
    }
    public void setExperiencePlayer(Boolean experiencePlayer) {
        this.experiencePlayer = experiencePlayer;
    }
    public LocalDate getBirthday() {
        return birthday;
    }
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    public ConsoleEntity getConsoleEntity() {
        return consoleEntity;
    }
    public void setConsoleEntity(ConsoleEntity consoleEntity) {
        this.consoleEntity = consoleEntity;
    }

    public void fromPlayer(Player player){
        BeanUtils.copyProperties(this, player);
    }

    @Override
    public int hashCode() {
        return playerName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (playerName.equals(((PlayerEntity) obj).playerName));
    }

    public Player toPlayer(){
        Player player = new Player();
        BeanUtils.copyProperties(this, player,"consoleEntity");
        player.setConsole(this.consoleEntity.toConsole());
        return player;
    }

    @Override
    public String toString() {
        return "PlayerEntity{" +
                "id='" + id + '\'' +
                "playerName='" + playerName + '\'' +
                ", age=" + age +
                ", experiencePlayer=" + experiencePlayer +
                ", birthday=" + birthday +
                ", consoleEntity=" + consoleEntity +
                '}';
    }
}