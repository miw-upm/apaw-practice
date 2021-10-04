package es.upm.miw.apaw_practice.domain.models.football;

public class FootballPlayer {
    private Boolean defense;
    private Integer goalsScored;
    private Integer age;

    public FootballPlayer() {
        //empty for framework
    }

    public FootballPlayer(Boolean defense, Integer goalsScored, Integer age) {
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
        return "FootballPlayer{" +
                "defense=" + defense +
                ", goalsScored=" + goalsScored +
                ", age=" + age +
                '}';
    }
}
