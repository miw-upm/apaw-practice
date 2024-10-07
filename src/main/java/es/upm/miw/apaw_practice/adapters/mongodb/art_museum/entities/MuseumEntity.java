package es.upm.miw.apaw_practice.adapters.mongodb.art_museum.entities;

import es.upm.miw.apaw_practice.domain.models.art_museum.Artwork;
import es.upm.miw.apaw_practice.domain.models.art_museum.Exhibition;
import es.upm.miw.apaw_practice.domain.models.art_museum.Museum;
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
    private List<ArtworkEntity> artworkEntities;
    private List<ExhibitionEntity> exhibitionEntities;

    public MuseumEntity() {
        //empty for framework
    }

    public MuseumEntity(String name, Integer capacity, Boolean isOpen, List<ArtworkEntity> artworkEntities, List<ExhibitionEntity> exhibitionEntities) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.capacity = capacity;
        this.isOpen = isOpen;
        this.artworkEntities = artworkEntities;
        this.exhibitionEntities = exhibitionEntities;
    }

    public Museum toMuseum() {
        List<Artwork> artworks = this.artworkEntities.stream()
                .map(ArtworkEntity::toArtwork)
                .toList();
        List<Exhibition> exhibitions = this.exhibitionEntities.stream()
                .map(ExhibitionEntity::toExhibition)
                .toList();
        return new Museum(name, capacity, isOpen, artworks, exhibitions);
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

    public List<ArtworkEntity> getArtworkEntities() {
        return artworkEntities;
    }

    public void setArtworkEntities(List<ArtworkEntity> artworkEntities) {
        this.artworkEntities = artworkEntities;
    }

    public List<ExhibitionEntity> getExhibitionEntities() {
        return exhibitionEntities;
    }

    public void setExhibitionEntities(List<ExhibitionEntity> exhibitionEntities) {
        this.exhibitionEntities = exhibitionEntities;
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
                ", artworks=" + artworkEntities +
                ", exhibitions=" + exhibitionEntities +
                '}';
    }
}
