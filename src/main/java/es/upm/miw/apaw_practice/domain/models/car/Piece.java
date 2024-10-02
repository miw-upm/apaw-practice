package es.upm.miw.apaw_practice.domain.models.car;

import java.math.BigDecimal;

public class Piece {

    private String partNumber;
    private String description;
    private BigDecimal cost;

    public Piece() {
        //empty for framework
    }
    public Piece(String partNumber, String description, BigDecimal cost) {
        this.partNumber = partNumber;
        this.description = description;
        this.cost = cost;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
    @Override
    public String toString() {
        return "Piece{" +
                "partNumber='" + partNumber + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                '}';
    }
}
