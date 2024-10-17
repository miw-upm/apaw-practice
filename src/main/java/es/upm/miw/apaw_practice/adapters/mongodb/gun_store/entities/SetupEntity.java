package es.upm.miw.apaw_practice.adapters.mongodb.gun_store.entities;

import es.upm.miw.apaw_practice.domain.models.gun_store.Gun;
import es.upm.miw.apaw_practice.domain.models.gun_store.Setup;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class SetupEntity {
    @Id
    private Integer setupId;
    private BigDecimal totalPrice;
    private LocalDate orderDate;
    private List<GunEntity> gunEntities;

    public SetupEntity() {
        //Empty for framework
    }

    public SetupEntity(Integer setupId, BigDecimal totalPrice, List<GunEntity> gunEntities) {
        this.setupId = setupId;
        this.totalPrice = totalPrice;
        this.orderDate = LocalDate.now();
        this.gunEntities = gunEntities;
    }

    public SetupEntity(Setup setup) {
        this.setupId = setup.getSetupId();
        this.totalPrice = setup.getTotalPrice();
        this.orderDate = setup.getOrderDate();
        this.gunEntities = setup.getGuns().stream().map(GunEntity::new)
                .collect(Collectors.toList());
    }

    public Integer getSetupId() {
        return setupId;
    }

    public void setSetupId(Integer setupId) {
        this.setupId = setupId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public List<GunEntity> getGunEntities() {
        return gunEntities;
    }

    public void setGunEntities(List<GunEntity> gunEntities) {
        this.gunEntities = gunEntities;
    }

    @Override
    public String toString() {
        return "SetupEntity{" +
                "setupId=" + setupId +
                ", totalPrice=" + totalPrice +
                ", orderDate=" + orderDate +
                ", guns=" + gunEntities +
                '}';
    }

    public Setup toSetup() {
        Setup setup = new Setup();
        BeanUtils.copyProperties(this, setup);
        setup.setGuns(this.gunEntities.stream().map(GunEntity::toGun).toList());
        return setup;
    }

}
