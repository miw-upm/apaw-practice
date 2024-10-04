package es.upm.miw.apaw_practice.domain.models.E_commerce_model;

import java.time.LocalDateTime;
import java.util.List;

public class Customer {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime registeredAt;
    private List<Order> orders;  // Customer can have multiple orders
    private List<Payment> payments;  // Customer can have multiple payments

    // Constructor
    public Customer(Long id, String name, String email, LocalDateTime registeredAt, List<Order> orders, List<Payment> payments) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.registeredAt = registeredAt;
        this.orders = orders;
        this.payments = payments;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    // Overriding toString method
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", registeredAt=" + registeredAt +
                ", orders=" + orders +
                ", payments=" + payments +
                '}';
    }
}
