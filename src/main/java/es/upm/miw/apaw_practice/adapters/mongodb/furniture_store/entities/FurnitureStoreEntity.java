package es.upm.miw.apaw_practice.adapters.mongodb.furniture_store.entities;

import es.upm.miw.apaw_practice.domain.models.furniture_store.Furniture;
import es.upm.miw.apaw_practice.domain.models.furniture_store.FurnitureStore;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Document
public class FurnitureStoreEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private LocalDateTime openingTime;
    private LocalDateTime closeTime;
    @DBRef
    private ManagerEntity managerEntity;
    private List<FurnitureEntity> furnitureEntities;

    public FurnitureStoreEntity() {
        //empty from framework
    }

    public FurnitureStoreEntity(String name, LocalDateTime openingTime, LocalDateTime closeTime,
                                ManagerEntity managerEntity, List<FurnitureEntity> furnitureEntities) {
        this.name = name;
        this.openingTime = openingTime;
        this.closeTime = closeTime;
        this.managerEntity = managerEntity;
        this.furnitureEntities = furnitureEntities;
        this.id = UUID.randomUUID().toString();
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

    public LocalDateTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalDateTime openingTime) {
        this.openingTime = openingTime;
    }

    public LocalDateTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalDateTime closeTime) {
        this.closeTime = closeTime;
    }

    public ManagerEntity getManagerEntity() {
        return managerEntity;
    }

    public void setManagerEntity(ManagerEntity managerEntity) {
        this.managerEntity = managerEntity;
    }

    public List<FurnitureEntity> getFurnitureEntities() {
        return furnitureEntities;
    }

    public void setFurnitureEntities(List<FurnitureEntity> furnitureEntities) {
        this.furnitureEntities = furnitureEntities;
    }

    public FurnitureStore toFurnitureStore() {
        FurnitureStore furnitureStore = new FurnitureStore();
        BeanUtils.copyProperties(this, furnitureStore, "managerEntity", "furnitureEntities");
        List<Furniture> furnitures = this.furnitureEntities.stream()
                .map(FurnitureEntity::toFurniture)
                .toList();
        furnitureStore.setFurnitures(furnitures);
        furnitureStore.setManager(this.managerEntity.toManager());
        return furnitureStore;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass()
                && (name.equals(((FurnitureStoreEntity) obj).name));
    }

    @Override
    public String toString() {
        return "FurnitureStoreEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", openingTime=" + openingTime +
                ", closeTime=" + closeTime +
                ", managerEntity=" + managerEntity +
                ", furnitureEntities=" + furnitureEntities +
                '}';
    }

}
