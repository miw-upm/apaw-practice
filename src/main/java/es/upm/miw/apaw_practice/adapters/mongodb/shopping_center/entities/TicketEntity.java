package es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.entities;

import es.upm.miw.apaw_practice.domain.models.shopping_center.Ticket;
import org.springframework.beans.BeanUtils;
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
    private EmployeeShoppingCenterEntity employeeEntity;

    public TicketEntity() {
        //empty from framework
    }

    public TicketEntity(BigDecimal totalPrice, boolean isPaidByCreditCard, EmployeeShoppingCenterEntity employeeEntity) {
        this.id = UUID.randomUUID().toString();
        this.totalPrice = totalPrice;
        this.isPaidByCreditCard = isPaidByCreditCard;
        this.employeeEntity = employeeEntity;
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

    public EmployeeShoppingCenterEntity getEmployeeEntity() {
        return employeeEntity;
    }

    public void setEmployeeEntity(EmployeeShoppingCenterEntity employeeEntity) {
        this.employeeEntity = employeeEntity;
    }

    public Ticket toTicket() {
        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(this, ticket, "employee");
        ticket.setEmployee(this.employeeEntity.toEmployee());
        return ticket;
    }

    @Override
    public String toString() {
        return "TicketEntity{" +
                "id='" + id + '\'' +
                ", totalPrice=" + totalPrice +
                ", isPaidByCreditCard=" + isPaidByCreditCard +
                ", date=" + date +
                ", employeeEntity=" + employeeEntity +
                '}';
    }
}
