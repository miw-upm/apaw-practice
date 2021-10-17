package es.upm.miw.apaw_practice.adapters.mongodb.emarketer.entities;

import es.upm.miw.apaw_practice.domain.models.emarketer.Plan;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Document
public class PlanEntity {

    @Id
    private String id;
    private String description;
    private BigDecimal price;
    private Integer duration;

    public PlanEntity() {
        //empty for framework
    }

    public PlanEntity(String description, BigDecimal price,Integer duration) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.price = price;
        this.duration = duration;
    }

    public PlanEntity(Plan plan) {
        BeanUtils.copyProperties(plan, this);
        this.id = UUID.randomUUID().toString();
    }

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Plan toPlan() {
        Plan plan = new Plan();
        BeanUtils.copyProperties(this, plan);
        return plan;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((PlanEntity) obj).id));
    }

    @Override
    public String toString() {
        return "PlanEntity{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", duration=" + duration +
                '}';
    }
}
