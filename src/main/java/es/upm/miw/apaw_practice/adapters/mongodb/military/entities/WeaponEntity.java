package es.upm.miw.apaw_practice.adapters.mongodb.military.entities;

import es.upm.miw.apaw_practice.domain.models.military.Weapon;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Document
public class WeaponEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String serialCode;
    private String manufacturer;
    private BigDecimal cost;

    public WeaponEntity() {
        //empty for framework
    }

    public WeaponEntity(Weapon weapon) {
        BeanUtils.copyProperties(weapon, this);
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSerialCode() {
        return serialCode;
    }

    public void setSerialCode(String serialCode) {
        this.serialCode = serialCode;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public int hashCode() {
        return serialCode.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (serialCode.equals(((WeaponEntity) obj).serialCode));
    }

    @Override
    public String toString() {
        return "WeaponEntity{" +
                "id='" + id + '\'' +
                ", serialCode='" + serialCode + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", cost=" + cost +
                '}';
    }
}
