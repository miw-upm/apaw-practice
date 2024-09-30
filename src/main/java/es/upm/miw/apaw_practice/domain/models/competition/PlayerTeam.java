package es.upm.miw.apaw_practice.domain.models.competition;

import java.math.BigDecimal;

public class PlayerTeam {

    private TeamCompetition teamCompetition;
    private Double weight;
    private Double height;
    private BigDecimal salary;

    public PlayerTeam() {
        // empty for framework
    }

    public PlayerTeam(TeamCompetition teamCompetition, Double weight, Double height, BigDecimal salary) {
        this.teamCompetition = teamCompetition;
        this.weight = weight;
        this.height = height;
        this.salary = salary;
    }

    public TeamCompetition getTeamCompetition() {
        return teamCompetition;
    }

    public void setTeamCompetition(TeamCompetition teamCompetition) {
        this.teamCompetition = teamCompetition;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "PlayerTeam{" +
                "teamCompetition=" + teamCompetition +
                ", weight=" + weight +
                ", height=" + height +
                ", salary=" + salary +
                '}';
    }
}
