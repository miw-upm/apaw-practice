package es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities;

import es.upm.miw.apaw_practice.domain.models.videogame.Platform;
import es.upm.miw.apaw_practice.domain.models.videogame.VideoGameCompany;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Document
public class VideoGameCompanyEntity {

    @Id
    private String id;
    @Indexed(unique = true)

    private String name;
    private LocalDate formationDate;
    private Boolean stockMarket;
    private List<PlatformEntity> platformEntities;

    public VideoGameCompanyEntity() {
        //empty from framework
    }

    public VideoGameCompanyEntity(String name, LocalDate formationDate, Boolean stockMarket, List<PlatformEntity> platformEntities) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.formationDate = formationDate;
        this.stockMarket = stockMarket;
        this.platformEntities = platformEntities;
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

    public List<PlatformEntity> getPlatformEntities() {
        return platformEntities;
    }

    public void setPlatformEntities(List<PlatformEntity> platformEntities) {
        this.platformEntities = platformEntities;
    }

    public VideoGameCompany toVideoGameCompany() {
        VideoGameCompany videoGameCompany = new VideoGameCompany();
        BeanUtils.copyProperties(this, videoGameCompany, "platformEntities");
        List<Platform> platforms = this.platformEntities.stream()
                .map(PlatformEntity::toPlatform)
                .collect(Collectors.toList());
        videoGameCompany.setPlatforms(platforms);
        return videoGameCompany;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((VideoGameCompanyEntity) obj).id));
    }

    @Override
    public String toString() {
        return "VideoGameCompanyEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", formationDate=" + formationDate +
                ", stockMarket=" + stockMarket +
                ", platformEntities=" + platformEntities +
                '}';
    }
}
