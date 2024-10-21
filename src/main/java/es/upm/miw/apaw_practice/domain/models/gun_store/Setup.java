package es.upm.miw.apaw_practice.domain.models.gun_store;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Setup {
    private Integer setupId;
    private BigDecimal totalPrice;
    private LocalDate orderDate;
    private List<Gun> guns;

    public Setup() {
        //Empty for framework
    }

    public Setup(int setupId, LocalDate orderDate, BigDecimal totalPrice) {
        this.setupId = setupId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    public Setup(Integer setupId, LocalDate orderDate, BigDecimal totalPrice, List<Gun> guns) {
        this.setupId = setupId;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.guns = guns;
    }

    public int getSetupId() {
        return setupId;
    }

    public void setSetupId(int setupId) {
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

    public List<Gun> getGuns() {
        return guns;
    }

    public void setGuns(List<Gun> guns) {
        this.guns = guns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Setup setup)) return false;
        return Objects.equals(setupId, setup.setupId) && Objects.equals(totalPrice, setup.totalPrice) && Objects.equals(orderDate, setup.orderDate) && Objects.equals(guns, setup.guns);
    }

}




