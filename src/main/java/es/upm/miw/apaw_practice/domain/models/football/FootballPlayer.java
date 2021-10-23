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

    public static FootballPlayerBuilders.Defense builder() {
        return new Builder();
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

    public static class Builder implements FootballPlayerBuilders.Name, FootballPlayerBuilders.Age, FootballPlayerBuilders.GoalsScored, FootballPlayerBuilders.Defense, FootballPlayerBuilders.FootballPlayerBuild {
        private final FootballPlayer footballPlayer;

        public Builder() {
            this.footballPlayer = new FootballPlayer();
        }

        @Override
        public FootballPlayerBuilders.GoalsScored defense(Boolean defense) {
            this.footballPlayer.defense = defense;
            return this;
        }

        @Override
        public FootballPlayerBuilders.Age goalsScored(Integer goalsScored) {
            this.footballPlayer.goalsScored = goalsScored;
            return this;
        }

        @Override
        public FootballPlayerBuilders.Name age(Integer age) {
            this.footballPlayer.age = age;
            return this;
        }

        @Override
        public FootballPlayerBuilders.FootballPlayerBuild name(String name) {
            this.footballPlayer.name = name;
            return this;
        }

        @Override
        public FootballPlayer build() {
            return this.footballPlayer;
        }
    }
}
