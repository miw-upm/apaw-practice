package es.upm.miw.apaw_practice.adapters.mongodb.company.entities;

import es.upm.miw.apaw_practice.domain.models.company.ExpenseBill;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Document
public class ExpenseBillEntity {

    @Id
    private String id;

    @Field
    private String description;

    @Field
    private BigDecimal amount;

    @Field
    private LocalDateTime date;

    @DBRef
    private List<DepartmentEntity> departments;

    public ExpenseBillEntity() {
        // Empty for framework
    }

    public ExpenseBillEntity(String description, BigDecimal amount, LocalDateTime date, List<DepartmentEntity> departments) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.departments = departments;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<DepartmentEntity> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentEntity> departments) {
        this.departments = departments;
    }

    public ExpenseBill toExpenseBill() {
        ExpenseBill expenseBill = new ExpenseBill();
        BeanUtils.copyProperties(this, expenseBill);
        if (this.departments != null) {
            expenseBill.setDepartments(this.departments.stream()
                    .map(DepartmentEntity::toDepartment)
                    .toList());
        }
        return expenseBill;
    }
    // equals, hashCode, toString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpenseBillEntity that = (ExpenseBillEntity) o;
        return Objects.equals(description, that.description) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(date, that.date) &&
                Objects.equals(departments, that.departments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, amount, date, departments);
    }

    @Override
    public String toString() {
        return "ExpenseBillEntity{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", departments=" + departments +
                '}';
    }
}
