package es.upm.miw.apaw_practice.domain.models.hotel;

import java.math.BigDecimal;

public class Room {
    private String number;
    private String type;
    private BigDecimal price;
    private Boolean reserved;

    public Room(){

    }

    public Room(String number, String type, BigDecimal price, Boolean reserved){
    this.number = number;
    this.type = type;
    this.price = price;
    this.reserved = reserved;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    @Override
    public String toString() {
        return "Room{" +
                "number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", reserved=" + reserved +
                '}';
    }
}