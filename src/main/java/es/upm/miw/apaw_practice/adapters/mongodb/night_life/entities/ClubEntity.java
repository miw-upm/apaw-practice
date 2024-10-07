package es.upm.miw.apaw_practice.adapters.mongodb.night_life.entities;

import es.upm.miw.apaw_practice.domain.models.night_life.Club;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.UUID;

@Document
public class ClubEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private Integer capacity;
    private boolean opened;
    @DBRef
    private OwnerEntity ownerEntity;

    public ClubEntity() {
        //empty for framework
    }

    public ClubEntity(Club club) {
        this.id = UUID.randomUUID().toString();
        this.name = club.getName();
        this.capacity = club.getCapacity();
        this.opened = club.isOpened();
        this.ownerEntity = new OwnerEntity(club.getOwner());
    }

    public ClubEntity(String name, Integer capacity, boolean opened, OwnerEntity ownerEntity) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.capacity = capacity;
        this.opened = opened;
        this.ownerEntity = ownerEntity;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public OwnerEntity getOwnerEntity() {
        return ownerEntity;
    }

    public void setOwnerEntity(OwnerEntity ownerEntity) {
        this.ownerEntity = ownerEntity;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (name.equals(((ClubEntity) obj).name));
    }

    @Override
    public String toString() {
        return "ClubEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", opened=" + opened +
                ", ownerEntity=" + (ownerEntity != null ? ownerEntity.toString() : "null") +
                '}';
    }


}
