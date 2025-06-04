package es.upm.miw.apaw_practice.domain.models.music_festival;

import java.math.BigDecimal;

public class MusicFestivalBudgetUpdating {

    private String name;
    private BigDecimal budget;

    public MusicFestivalBudgetUpdating() {
        //empty for framework
    }

    public MusicFestivalBudgetUpdating(String name, BigDecimal budget) {
        this.name = name;
        this.budget = budget;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "MusicFestivalBudgetUpdating{" +
                "name='" + name + '\'' +
                ", budget=" + budget +
                '}';
    }
}