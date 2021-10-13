package es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import nonapi.io.github.classgraph.json.Id;

import java.util.Date;
import java.util.List;

@Document
public class RaidEntity {

    @Id
    private String id;
    private Date date;
    private String name;
    private String dificulty;
    private Integer playerNumber;
    private Boolean finish;
    @DBRef
    private List<BossEntity> bossListEntities;

    public RaidEntity() {
        //empty for framework
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<BossEntity> getBossListEntities() {
        return bossListEntities;
    }

    public void setBossListEntities(List<BossEntity> bossListEntities) {
        this.bossListEntities = bossListEntities;
    }
}
