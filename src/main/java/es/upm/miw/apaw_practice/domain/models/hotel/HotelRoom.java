package es.upm.miw.apaw_practice.domain.models.hotel;

import es.upm.miw.apaw_practice.domain.models.shopping_center.Employee;
import es.upm.miw.apaw_practice.domain.models.shopping_center.EmployeeBuilders;

import java.math.BigDecimal;

public class HotelRoom {
    private String number;
    private String type;
    private BigDecimal price;
    private Boolean reserved;

    public HotelRoom(){

    }

    public HotelRoom(String number, String type, BigDecimal price, Boolean reserved){
    this.number = number;
    this.type = type;
    this.price = price;
    this.reserved = reserved;
    }

    public static HotelRoomBuilders.Number builder() {
        return new Builder();
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

    public static class Builder implements HotelRoomBuilders.Number, HotelRoomBuilders.Type, HotelRoomBuilders.Price ,HotelRoomBuilders.Optionals {

        private final HotelRoom room;

        public Builder() {
            this.room = new HotelRoom();
        }

        @Override
        public HotelRoomBuilders.Type number(String number) {
            this.room.number = number;
            return this;
        }

        @Override
        public HotelRoomBuilders.Price type(String type) {
            this.room.type = type;
            return this;
        }

        @Override
            public HotelRoomBuilders.Optionals price(BigDecimal price) {
            this.room.price = price;
            return this;
        }

        @Override
        public HotelRoomBuilders.Optionals reserved(boolean reserved) {
            this.room.reserved = reserved;
            return this;
        }

        @Override
        public HotelRoom build() {
            return this.room;
        }
    }
}