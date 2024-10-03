package es.upm.miw.apaw_practice.domain.models.gun_store;

import java.math.BigDecimal;

public class Gun {
    private int gunId;
    private BigDecimal price;
    private String name;
    private String manufacturer;

    public Gun() {
        //Empty for framework
    }

    public Gun(String name, BigDecimal price, int gunId, String manufacturer) {
        this.name = name;
        this.price = price;
        this.gunId = gunId;
        this.manufacturer = manufacturer;
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
}
