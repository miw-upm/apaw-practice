package es.upm.miw.apaw_practice.adapters.mongodb.art_museum.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document
public class MuseumEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private Integer capacity;
    private Boolean isOpen;
    @DBRef
    private List<ArtworkEntity> artworks;
    private List<ExhibitionEntity> exhibitions;

    public MuseumEntity() {
        //empty for framework
    }

    public MuseumEntity(String name, Integer capacity, Boolean isOpen, List<ArtworkEntity> artworks, List<ExhibitionEntity> exhibitions) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.capacity = capacity;
        this.isOpen = isOpen;
        this.artworks = artworks;
        this.exhibitions = exhibitions;
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

    public List<ArtworkEntity> getArtworks() {
        return artworks;
    }

    public void setArtworks(List<ArtworkEntity> artworks) {
        this.artworks = artworks;
    }

    public List<ExhibitionEntity> getExhibitions() {
        return exhibitions;
    }

    public void setExhibitions(List<ExhibitionEntity> exhibitions) {
        this.exhibitions = exhibitions;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (name.equals(((MuseumEntity) obj).name));
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "MuseumEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", isOpen=" + isOpen +
                ", artworks=" + artworks +
                ", exhibitions=" + exhibitions +
                '}';
    }
}
