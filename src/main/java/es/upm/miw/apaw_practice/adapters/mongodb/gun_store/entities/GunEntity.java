package es.upm.miw.apaw_practice.adapters.mongodb.gun_store.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Document
public class GunEntity {
    @Id
    private Integer gunId;
    private BigDecimal price;
    private String name;
    private String manufacturer;
    private List<AccesoryEntity> accesoryEntities;
    private CompatibleAmmoEntity ammoEntities;

    public GunEntity() {
        //Empty for framework
    }

    public GunEntity(BigDecimal price, String name, String manufacturer,
                     List<AccesoryEntity> accesoryEntities, CompatibleAmmoEntity compatibleAmmo) {
        this.gunId = UUID.randomUUID().hashCode();
        this.price = price;
        this.manufacturer = manufacturer;
        this.accesoryEntities = accesoryEntities;
        this.ammoEntities = compatibleAmmo;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getGunId() {
        return gunId;
    }

    public void setGunId(Integer gunId) {
        this.gunId = gunId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<AccesoryEntity> getAccesoryEntities() {
        return accesoryEntities;
    }

    public void setAccesoryEntities(List<AccesoryEntity> accesoryEntities) {
        this.accesoryEntities = accesoryEntities;
    }

    public CompatibleAmmoEntity getAmmoEntities() {
        return ammoEntities;
    }

    public void setAmmoEntities(CompatibleAmmoEntity ammoEntities) {
        this.ammoEntities = ammoEntities;
    }

    @Override
    public String toString() {
        return "GunEntity{" +
                "gunId=" + gunId +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", accesories=" + accesoryEntities +
                ", ammo=" + ammoEntities +
                '}';
    }
}
