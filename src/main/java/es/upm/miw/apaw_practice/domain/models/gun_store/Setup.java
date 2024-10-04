package es.upm.miw.apaw_practice.domain.models.gun_store;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Setup {
    private int setupId;
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
}




