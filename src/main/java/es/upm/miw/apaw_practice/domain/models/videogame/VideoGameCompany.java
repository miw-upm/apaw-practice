package es.upm.miw.apaw_practice.domain.models.videogame;

import es.upm.miw.apaw_practice.domain.models.shop.ShoppingCart;

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

    public static VideoGameCompany ofNameStockMarket(VideoGameCompany videoGameCompany) {
        VideoGameCompany videoGameCompanyDto = new VideoGameCompany();
        videoGameCompanyDto.setName(videoGameCompany.getName());
        videoGameCompanyDto.setStockMarket(videoGameCompany.getStockMarket());
        return videoGameCompanyDto;
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

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

    @Override
    public String toString() {
        return "VideoGameCompany{" +
                "name='" + name + '\'' +
                ", formationDate=" + formationDate +
                ", stockMarket=" + stockMarket +
                ", platforms=" + platforms +
                '}';
    }
}
