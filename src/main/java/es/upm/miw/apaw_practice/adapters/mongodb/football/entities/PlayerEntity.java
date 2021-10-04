package es.upm.miw.apaw_practice.adapters.mongodb.football.entities;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public class PlayerEntity {
    @Id
    private String id;
    private Boolean defense;
    private Integer goalsScored;
    private Integer age;

    public PlayerEntity() {
        //empty for framework
    }

    public PlayerEntity(Boolean defense, Integer goalsScored, Integer age) {
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

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((PlayerEntity) obj).id));
    }

    @Override
    public String toString() {
        return "Player{" +
                "defense=" + defense +
                ", goalsScored=" + goalsScored +
                ", age=" + age +
                '}';
    }
}
