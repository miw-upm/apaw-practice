package es.upm.miw.apaw_practice.adapters.mongodb.football.entities;

import es.upm.miw.apaw_practice.domain.models.football.FootballPlayer;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;

import java.util.UUID;

public class FootballPlayerEntity {
    @Id
    private String id;
    private Boolean defense;
    private Integer goalsScored;
    private Integer age;

    public FootballPlayerEntity() {
        //empty for framework
    }

    public FootballPlayerEntity(Boolean defense, Integer goalsScored, Integer age) {
        this.id = UUID.randomUUID().toString();
        this.defense = defense;
        this.goalsScored = goalsScored;
        this.age = age;
    }

    public Boolean isDefense() {
        return defense;
    }

    public void setDefense(Boolean defense) {
        this.defense = defense;
    }

    public Integer getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(Integer goalsScored) {
        this.goalsScored = goalsScored;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FootballPlayer toFootballPlayer() {
        FootballPlayer footballPlayer = new FootballPlayer();
        BeanUtils.copyProperties(this, footballPlayer);
        return footballPlayer;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((FootballPlayerEntity) obj).id));
    }

    @Override
    public String toString() {
        return "FootballPlayer{" +
                "defense=" + defense +
                ", goalsScored=" + goalsScored +
                ", age=" + age +
                '}';
    }


}
