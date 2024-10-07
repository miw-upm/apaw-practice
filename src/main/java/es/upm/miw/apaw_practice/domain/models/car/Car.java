package es.upm.miw.apaw_practice.domain.models.car;

import java.math.BigDecimal;
import java.util.List;

public class Car {
    private String name;
    private boolean isElectric;
    private BigDecimal price;

    private Owner owner;

    private List<Piece> pieces;

    public Car() {
        //empty for framework
    }
    public Car(String name, boolean isElectric, BigDecimal price, Owner owner, List<Piece> pieces) {
        this.name = name;
        this.isElectric = isElectric;
        this.price = price;
        this.owner = owner;
        this.pieces = pieces;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
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
                "name='" + name + '\'' +
                ", isElectric=" + isElectric +
                ", price=" + price +
                ", owner=" + (owner != null ? owner.getName() : "None") +
                ", pieces=" + pieces +
                '}';
    }
}
