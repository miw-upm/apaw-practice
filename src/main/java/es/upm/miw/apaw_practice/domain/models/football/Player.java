package es.upm.miw.apaw_practice.domain.models.football;

public class Player {
    private boolean defense;
    private int goalsScored;
    private int age;

    public Player() {
        //empty for framework
    }

    public Player(boolean defense, int goalsScored, int age) {
        this.defense = defense;
        this.goalsScored = goalsScored;
        this.age = age;
    }

    public boolean isDefense() {
        return defense;
    }

    public void setDefense(boolean defense) {
        this.defense = defense;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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
