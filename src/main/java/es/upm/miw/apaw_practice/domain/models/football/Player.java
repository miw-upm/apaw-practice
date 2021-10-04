package es.upm.miw.apaw_practice.domain.models.football;

public class Player {
    private Boolean defense;
    private Integer goalsScored;
    private Integer age;

    public Player() {
        //empty for framework
    }

    public Player(Boolean defense, Integer goalsScored, Integer age) {
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

    @Override
    public String toString() {
        return "Player{" +
                "defense=" + defense +
                ", goalsScored=" + goalsScored +
                ", age=" + age +
                '}';
    }
}
