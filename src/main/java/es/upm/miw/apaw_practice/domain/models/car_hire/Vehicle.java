package es.upm.miw.apaw_practice.domain.models.car_hire;

import java.math.BigDecimal;

public class Vehicle {

    private String VIN_number;
    private BigDecimal dailyCost;
    private Integer numberDays;
    private Integer kilometersAmount;
    private Boolean goodCondition;

    public Vehicle() {
        //empty for framework
    }

    public  Vehicle(String VIN_number, BigDecimal dailyCost, Integer numberDays, Integer kilometersAmount, Boolean goodCondition) {
        this.VIN_number = VIN_number;
        this.dailyCost = dailyCost;
        this.numberDays = numberDays;
        this.kilometersAmount = kilometersAmount;
        this.goodCondition = goodCondition;
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
        return "Vehicle{" +
                "VIN_number='" + VIN_number + '\'' +
                ", dailyCost=" + dailyCost +
                ", numberDays=" + numberDays +
                ", kilometersAmount=" + kilometersAmount +
                ", goodCondition=" + goodCondition +
                '}';
    }
}
