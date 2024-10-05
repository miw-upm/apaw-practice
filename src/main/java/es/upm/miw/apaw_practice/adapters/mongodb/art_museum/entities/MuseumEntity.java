package es.upm.miw.apaw_practice.adapters.mongodb.art_museum.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class MuseumEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private Integer capacity;
    private Boolean isOpen;
    private ExhibitionEntity exhibition;

    public MuseumEntity() {
        //empty for framework
    }

    public MuseumEntity(String name, Integer capacity, Boolean isOpen, ExhibitionEntity exhibition) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.capacity = capacity;
        this.isOpen = isOpen;
        this.exhibition = exhibition;
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

    public ExhibitionEntity getExhibition() {
        return exhibition;
    }

    public void setExhibition(ExhibitionEntity exhibition) {
        this.exhibition = exhibition;
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
                ", exhibition=" + exhibition +
                '}';
    }
}
