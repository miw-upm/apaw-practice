package es.upm.miw.apaw_practice.adapters.mongodb.company.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Document
public class ExpenseBillEntity {

    @Id
    private String id;

    @Field
    private BigDecimal amount;

    @Field
    private LocalDate date;

    private String departmentId; // Assuming a reference to the department

    public ExpenseBillEntity() {
        // Empty for framework
    }

    public ExpenseBillEntity(BigDecimal amount, LocalDate date, String departmentId) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.date = date;
        this.departmentId = departmentId;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    // equals, hashCode, toString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpenseBillEntity that = (ExpenseBillEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ExpenseBillEntity{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", departmentId='" + departmentId + '\'' +
                '}';
    }
}
