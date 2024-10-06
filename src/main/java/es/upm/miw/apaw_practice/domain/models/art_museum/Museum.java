package es.upm.miw.apaw_practice.domain.models.art_museum;

import es.upm.miw.apaw_practice.domain.models.shop.Article;
import es.upm.miw.apaw_practice.domain.models.shop.Tag;

import java.util.List;
import java.util.stream.Collectors;

public class Museum {
    private String name;
    private Integer capacity;
    private Boolean isOpen;
    private List<Artwork> artworks;
    private List<Exhibition> exhibitions;

    public Museum() {
        //empty for framework
    }

    public Museum(String name, Integer capacity, Boolean isOpen, List<Artwork> artworks, List<Exhibition> exhibitions) {
        this.name = name;
        this.capacity = capacity;
        this.isOpen = isOpen;
        this.artworks = artworks;
        this.exhibitions = exhibitions;
    }

    public static Museum ofArtworkInventoryNumber(Museum museum) {
        museum.setArtworks(
                museum.artworks.stream()
                        .map(Artwork::ofInventoryNumber)
                        .toList()
        );
        return museum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public List<Artwork> getArtworks() {
        return artworks;
    }

    public void setArtworks(List<Artwork> artworks) {
        this.artworks = artworks;
    }

    public List<Exhibition> getExhibitions() {
        return exhibitions;
    }

    public void setExhibitions(List<Exhibition> exhibitions) {
        this.exhibitions = exhibitions;
    }

    @Override
    public String toString() {
        return "Museum{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", isOpen=" + isOpen +
                ", artworks=" + artworks +
                ", exhibitions=" + exhibitions +
                '}';
    }
}
