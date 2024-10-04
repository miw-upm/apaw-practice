package es.upm.miw.apaw_practice.adapters.mongodb.competition.entities;

import es.upm.miw.apaw_practice.domain.models.competition.PlayerTeam;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.util.UUID;

@Document
public class PlayerTeamEntity {
    @Id
    private String id;
    private Double weight;
    private Double height;
    private BigDecimal salary;

    public PlayerTeamEntity() {
        // empty for framework
    }

    public PlayerTeamEntity(PlayerTeam playerTeam) {
        BeanUtils.copyProperties(playerTeam, this);
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "PlayerTeamEntity{" +
                "id='" + id + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", salary=" + salary +
                '}';
    }
}
