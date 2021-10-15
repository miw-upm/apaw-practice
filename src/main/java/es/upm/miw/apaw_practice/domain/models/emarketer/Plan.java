package es.upm.miw.apaw_practice.domain.models.emarketer;

import java.math.BigDecimal;

public class Plan {

    private String description;
    private BigDecimal price;
    private Integer duration;

    public Plan() {
        //empty for framework
    }

    public Plan(String description, BigDecimal price, Integer duration) {
        this.description = description;
        this.price = price;
        this.duration = duration;
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

    @Override
    public String toString() {
        return "Plan{" +
                "description=" + description +
                ", price=" + price +
                ", duration=" + duration +
                '}';
    }
}
