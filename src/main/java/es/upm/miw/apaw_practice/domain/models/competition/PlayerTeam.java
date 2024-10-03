package es.upm.miw.apaw_practice.domain.models.competition;

import java.math.BigDecimal;

public class PlayerTeam {

    private Double weight;
    private Double height;
    private BigDecimal salary;

    public PlayerTeam() {
        // empty for framework
    }

    public PlayerTeam(Double weight, Double height, BigDecimal salary) {
        this.weight = weight;
        this.height = height;
        this.salary = salary;
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
                "weight=" + weight +
                ", height=" + height +
                ", salary=" + salary +
                '}';
    }
}
