package es.upm.miw.apaw_practice.adapters.mongodb.car_hire.entities;

import es.upm.miw.apaw_practice.domain.models.car_hire.Vehicle;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Document
public class VehicleEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String VIN_number;
    private BigDecimal dailyCost;
    private Integer numberDays;
    private Integer kilometersAmount;
    private Boolean goodCondition;

    public VehicleEntity() {
        //empty for framework
    }

    public VehicleEntity(Vehicle vehicle) {
        BeanUtils.copyProperties(vehicle, this);
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVIN_number() {
        return VIN_number;
    }

    public void setVIN_number(String VIN_number) {
        this.VIN_number = VIN_number;
    }

    public BigDecimal getDailyCost() {
        return dailyCost;
    }

    public void setDailyCost(BigDecimal dailyCost) {
        this.dailyCost = dailyCost;
    }

    public Integer getNumberDays() {
        return numberDays;
    }

    public void setNumberDays(Integer numberDays) {
        this.numberDays = numberDays;
    }

    public Integer getKilometersAmount() {
        return kilometersAmount;
    }

    public void setKilometersAmount(Integer kilometersAmount) {
        this.kilometersAmount = kilometersAmount;
    }

    public Boolean getGoodCondition() {
        return goodCondition;
    }

    public void setGoodCondition(Boolean goodCondition) {
        this.goodCondition = goodCondition;
    }

    @Override
    public String toString() {
        return "VehicleEntity{" +
                "id='" + id + '\'' +
                ", VIN_number='" + VIN_number + '\'' +
                ", dailyCost=" + dailyCost +
                ", numberDays=" + numberDays +
                ", kilometersAmount=" + kilometersAmount +
                ", goodCondition=" + goodCondition +
                '}';
    }
}
