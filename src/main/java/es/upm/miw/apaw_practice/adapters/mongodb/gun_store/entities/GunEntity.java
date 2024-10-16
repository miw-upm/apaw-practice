package es.upm.miw.apaw_practice.adapters.mongodb.gun_store.entities;

import es.upm.miw.apaw_practice.domain.models.gun_store.Gun;
import org.springframework.beans.BeanUtils;
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
    private CompatibleAmmoEntity ammoEntity;

    public GunEntity() {
        //Empty for framework
    }

    public GunEntity(BigDecimal price, String name, String manufacturer,
                     List<AccesoryEntity> accesoryEntities, CompatibleAmmoEntity compatibleAmmo) {
        this.name = name;
        this.gunId = UUID.randomUUID().hashCode();
        this.price = price;
        this.manufacturer = manufacturer;
        this.accesoryEntities = accesoryEntities;
        this.ammoEntity = compatibleAmmo;
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

    public CompatibleAmmoEntity getAmmoEntity() {
        return ammoEntity;
    }

    public void setAmmoEntity(CompatibleAmmoEntity ammoEntity) {
        this.ammoEntity = ammoEntity;
    }

    public Gun toGun() {
        Gun gun = new Gun();
        BeanUtils.copyProperties(this, gun);
        gun.setAccesories(this.accesoryEntities.stream().map(AccesoryEntity::toAccesory).toList());
        gun.setAmmo(this.ammoEntity.toCompatibleAmmo());
        return gun;
    }

    @Override
    public String toString() {
        return "GunEntity{" +
                "gunId=" + gunId +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", accesories=" + accesoryEntities +
                ", ammo=" + ammoEntity +
                '}';
    }
}
