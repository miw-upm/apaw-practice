package es.upm.miw.apaw_practice.domain.models.videogame;

import java.time.LocalDate;
import java.util.List;

public class VideoGameCompany {

    private String name;
    private LocalDate formationDate;
    private Boolean stockMarket;
    private List<Platform> platforms;

    public VideoGameCompany() {
        //empty from framework
    }

    public VideoGameCompany(String name, LocalDate formationDate, Boolean stockMarket, List<Platform> platforms) {
        this.name = name;
        this.formationDate = formationDate;
        this.stockMarket = stockMarket;
        this.platforms = platforms;
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

    public List<Platform> getConsoles() {
        return platforms;
    }

    public void setConsoles(List<Platform> platforms) {
        this.platforms = platforms;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", formationDate=" + formationDate +
                ", stockMarket=" + stockMarket +
                ", consoles=" + platforms +
                '}';
    }
}
