package es.upm.miw.apaw_practice.adapters.mongodb.gun_store.entities;

import es.upm.miw.apaw_practice.domain.models.gun_store.CompatibleAmmo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Document
public class CompatibleAmmoEntity {
    @Id
    Integer ammoId;
    BigDecimal price;
    private String caliber;

    public CompatibleAmmoEntity() {
        //Empty for framework
    }

    public CompatibleAmmoEntity(CompatibleAmmo compatibleAmmo) {
        BeanUtils.copyProperties(compatibleAmmo, this);
        this.ammoId = UUID.randomUUID().hashCode();
    }

    public Integer getAmmoId() {
        return ammoId;
    }

    public void setAmmoId(Integer ammoId) {
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

    public CompatibleAmmo toCompatibleAmmo() {
        CompatibleAmmo compatibleAmmo = new CompatibleAmmo();
        BeanUtils.copyProperties(this, compatibleAmmo);
        return compatibleAmmo;
    }

    public CompatibleAmmoEntity fromCompatibleAmmo(CompatibleAmmo compatibleAmmo) {
        BeanUtils.copyProperties(compatibleAmmo, this);
        return this;
    }

    @Override
    public String toString() {
        return "CompatibleAmmoEntity{" + "price=" + price + ", ammoId=" + ammoId + ", caliber='" + caliber + '\'' + '}';
    }
}
