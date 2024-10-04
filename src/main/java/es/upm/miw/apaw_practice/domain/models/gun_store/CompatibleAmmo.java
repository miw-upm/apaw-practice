package es.upm.miw.apaw_practice.domain.models.gun_store;

import java.math.BigDecimal;

public class CompatibleAmmo {
    private Integer ammoId;
    private BigDecimal price;
    private String caliber;

    public CompatibleAmmo() {
        //Empty for framework
    }

    public CompatibleAmmo(int ammoId, BigDecimal price, String caliber) {
        this.ammoId = ammoId;
        this.price = price;
        this.caliber = caliber;
    }

    public int getAmmoId() {
        return ammoId;
    }

    public void setAmmoId(int ammoId) {
        this.ammoId = ammoId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCaliber() {
        return caliber;
    }

    public void setCaliber(String caliber) {
        this.caliber = caliber;
    }
}

