package es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Document
public class VideoGameCompanyEntity {

    @Id
    private String id;
    @Indexed(unique = true)

    private String name;
    private LocalDate formationDate;
    private Boolean stockMarket;
    private List<PlatformEntity> platforms;

    public VideoGameCompanyEntity() {
        //empty from framework
    }

    public VideoGameCompanyEntity(String name, LocalDate formationDate, Boolean stockMarket, List<PlatformEntity> platforms) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.formationDate = formationDate;
        this.stockMarket = stockMarket;
        this.platforms = platforms;
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

    public List<PlatformEntity> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<PlatformEntity> platforms) {
        this.platforms = platforms;
    }

    @Override
    public String toString() {
        return "VideoGameCompanyEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", formationDate=" + formationDate +
                ", stockMarket=" + stockMarket +
                ", platforms=" + platforms +
                '}';
    }
}
