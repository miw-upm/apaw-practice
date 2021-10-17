package es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities;

import es.upm.miw.apaw_practice.domain.models.videogame.Console;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Document
public class CompanyEntity {

    @Id
    private String id;
    @Indexed(unique = true)

    private String name;
    private LocalDate formationDate;
    private Boolean stockMarket;
    private List<Console> consoles;

    public CompanyEntity() {
        //empty from framework
    }

    public CompanyEntity(String name, LocalDate formationDate, Boolean stockMarket, List<Console> consoles) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.formationDate = formationDate;
        this.stockMarket = stockMarket;
        this.consoles = consoles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return "CompanyEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", formationDate=" + formationDate +
                ", stockMarket=" + stockMarket +
                ", consoles=" + consoles +
                '}';
    }
}
