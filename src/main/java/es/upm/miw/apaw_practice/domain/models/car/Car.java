package es.upm.miw.apaw_practice.domain.models.car;

import java.math.BigDecimal;
import java.util.List;

public class Car {
    private String model;
    private boolean isElectric;
    private BigDecimal price;

    private OwnerCar ownerCar;

    private List<Piece> pieces;

    public Car() {
        //empty for framework
    }
    public Car(String model, boolean isElectric, BigDecimal price, OwnerCar ownerCar, List<Piece> pieces) {
        this.model = model;
        this.isElectric = isElectric;
        this.price = price;
        this.ownerCar = ownerCar;
        this.pieces = pieces;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public void setElectric(boolean electric) {
        isElectric = electric;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public OwnerCar getOwner() {
        return ownerCar;
    }

    public void setOwner(OwnerCar ownerCar) {
        this.ownerCar = ownerCar;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", isElectric=" + isElectric +
                ", price=" + price +
                ", owner=" + (ownerCar != null ? ownerCar.getName() : "None") +
                ", pieces=" + pieces +
                '}';
    }
}
