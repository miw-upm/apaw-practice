package es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Document
public class TicketEntity {
    @Id
    private String id;
    private BigDecimal totalPrice;
    private boolean isPaidByCreditCard;
    private LocalDateTime date;
    @DBRef
    private EmployeeEntity employee;

    public TicketEntity() {
        //empty from framework
    }

    public TicketEntity(BigDecimal totalPrice, boolean isPaidByCreditCard, EmployeeEntity employee) {
        this.id = UUID.randomUUID().toString();
        this.totalPrice = totalPrice;
        this.isPaidByCreditCard = isPaidByCreditCard;
        this.employee = employee;
        this.date = LocalDateTime.now();
    }

    public String getId() {
        return id;
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

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "TicketEntity{" +
                "id='" + id + '\'' +
                ", totalPrice=" + totalPrice +
                ", isPaidByCreditCard=" + isPaidByCreditCard +
                ", date=" + date +
                ", employee=" + employee +
                '}';
    }
}
