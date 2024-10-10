package es.upm.miw.apaw_practice.domain.models.shopping_center;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Ticket {
    private String id;
    private BigDecimal totalPrice;
    private boolean isPaidByCreditCard;
    private LocalDateTime date;
    private Employee employee;

    public Ticket() {
        //empty for framework
    }

    public Ticket(String id, BigDecimal totalPrice, boolean isPaidByCreditCard, Employee employee) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.isPaidByCreditCard = isPaidByCreditCard;
        this.employee = employee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isPaidByCreditCard() {
        return isPaidByCreditCard;
    }

    public void setPaidByCreditCard(boolean paidByCreditCard) {
        isPaidByCreditCard = paidByCreditCard;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", totalPrice=" + totalPrice +
                ", isPaidByCreditCard=" + isPaidByCreditCard +
                ", date=" + date +
                ", employee=" + employee +
                '}';
    }
}
