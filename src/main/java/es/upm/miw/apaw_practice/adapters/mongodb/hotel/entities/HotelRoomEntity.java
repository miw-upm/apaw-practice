package es.upm.miw.apaw_practice.adapters.mongodb.hotel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigDecimal;

public class HotelRoomEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String number;
    private String type;
    private BigDecimal price;
    private Boolean reserved;

    public HotelRoomEntity() {

    }

    public HotelRoomEntity(String number, String type, BigDecimal price, Boolean reserved) {
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
        return "HotelRoom{" +
                "number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", reserved=" + reserved +
                '}';
    }

}