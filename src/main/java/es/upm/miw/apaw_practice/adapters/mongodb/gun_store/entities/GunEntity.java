package es.upm.miw.apaw_practice.adapters.mongodb.gun_store.entities;

import es.upm.miw.apaw_practice.domain.models.gun_store.Accesory;
import es.upm.miw.apaw_practice.domain.models.gun_store.CompatibleAmmo;
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
    private List<Accesory> accesories;
    private CompatibleAmmo ammo;

    public GunEntity() {
        //Empty for framework
    }

    public GunEntity(BigDecimal price, String name, String manufacturer, List<Accesory> accesories, CompatibleAmmo compatibleAmmo) {
        this.gunId = UUID.randomUUID().hashCode();
        this.price = price;
        this.manufacturer = manufacturer;
        this.accesories = accesories;
        this.ammo = compatibleAmmo;
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

    public List<Accesory> getAccesories() {
        return accesories;
    }

    public void setAccesories(List<Accesory> accesories) {
        this.accesories = accesories;
    }

    public CompatibleAmmo getAmmo() {
        return ammo;
    }

    public void setAmmo(CompatibleAmmo ammo) {
        this.ammo = ammo;
    }

    @Override
    public String toString() {
        return "GunEntity{" +
                "gunId=" + gunId +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", accesories=" + accesories +
                ", ammo=" + ammo +
                '}';
    }
}
