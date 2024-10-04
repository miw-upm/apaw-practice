package es.upm.miw.apaw_practice.domain.models.car;

import java.math.BigDecimal;
import java.util.List;

public class Piece {
    private String partNumber;
    private String description;
    private BigDecimal cost;
    private List<Manufacturer> manufacturerList;

    public Piece() {
        //empty for framework
    }
    public Piece(String partNumber, String description, BigDecimal cost, List<Manufacturer> manufacturerList) {
        this.partNumber = partNumber;
        this.description = description;
        this.cost = cost;
        this.manufacturerList = manufacturerList;
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

    public List<Manufacturer> getManufacturerList() {
        return manufacturerList;
    }

    public void setManufacturerList(List<Manufacturer> manufacturerList) {
        this.manufacturerList = manufacturerList;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "partNumber='" + partNumber + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", manufacturer=" + manufacturerList +
                '}';
    }
}
