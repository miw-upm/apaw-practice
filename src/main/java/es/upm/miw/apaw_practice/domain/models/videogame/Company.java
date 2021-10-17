package es.upm.miw.apaw_practice.domain.models.videogame;

import java.time.LocalDate;
import java.util.List;

public class Company {

    private String name;
    private LocalDate formationDate;
    private Boolean stockMarket;
    private List<Console> consoles;

    public Company(String name, LocalDate formationDate, Boolean stockMarket, List<Console> consoles) {
        this.name = name;
        this.formationDate = formationDate;
        this.stockMarket = stockMarket;
        this.consoles = consoles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getFormationDate() {
        return formationDate;
    }

    public void setFormationDate(LocalDate formationDate) {
        this.formationDate = formationDate;
    }

    public Boolean getStockMarket() {
        return stockMarket;
    }

    public void setStockMarket(Boolean stockMarket) {
        this.stockMarket = stockMarket;
    }

    public List<Console> getConsoles() {
        return consoles;
    }

    public void setConsoles(List<Console> consoles) {
        this.consoles = consoles;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", formationDate=" + formationDate +
                ", stockMarket=" + stockMarket +
                ", consoles=" + consoles +
                '}';
    }
}
