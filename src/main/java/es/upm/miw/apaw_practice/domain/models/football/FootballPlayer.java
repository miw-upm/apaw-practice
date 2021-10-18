package es.upm.miw.apaw_practice.domain.models.football;

import java.util.Objects;

public class FootballPlayer {
    private Boolean defense;
    private Integer goalsScored;
    private Integer age;
    private String name;

    public FootballPlayer() {
        //empty for framework
    }

    public FootballPlayer(Boolean defense, Integer goalsScored, Integer age, String name) {
        this.defense = defense;
        this.goalsScored = goalsScored;
        this.age = age;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FootballPlayer that = (FootballPlayer) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "FootballPlayer{" +
                "defense=" + defense +
                ", goalsScored=" + goalsScored +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
