package es.upm.miw.apaw_practice.domain.models.gun_store;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Gun {
    private Integer gunId;
    private BigDecimal price;
    private String name;
    private String manufacturer;
    private List<Accesory> accesories;
    private CompatibleAmmo ammo;

    public Gun() {
        //Empty for framework
    }

    public Gun(BigDecimal price, String name, String manufacturer, List<Accesory> accesories, CompatibleAmmo ammo) {
        this.price = price;
        this.name = name;
        this.manufacturer = manufacturer;
        this.accesories = accesories;
        this.ammo = ammo;
    }


    public int getGunId() {
        return gunId;
    }

    public void setGunId(int gunId) {
        this.gunId = gunId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public CompatibleAmmo getAmmo() {
        return ammo;
    }

    public void setAmmo(CompatibleAmmo ammo) {
        this.ammo = ammo;
    }

    public List<Accesory> getAccesories() {
        return accesories;
    }

    public void setAccesories(List<Accesory> accesories) {
        this.accesories = accesories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gun gun)) return false;
        return Objects.equals(price, gun.price) && Objects.equals(name, gun.name)
                && Objects.equals(manufacturer, gun.manufacturer) && Objects.equals(ammo, gun.ammo);
    }
}
