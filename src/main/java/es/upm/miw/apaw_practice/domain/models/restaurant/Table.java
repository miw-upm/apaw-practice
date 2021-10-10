package es.upm.miw.apaw_practice.domain.models.restaurant;

import java.math.BigDecimal;
import java.util.List;

public class Table {
    private Integer number;
    private Boolean occupied;
    private String style;
    private BigDecimal price;
    private List<Reserve> reserves;

    Table(){
        //empty for framework
    }

    public Boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Reserve> getReserves() {
        return reserves;
    }

    public void setReserves(List<Reserve> reserves) {
        this.reserves = reserves;
    }

    @Override
    public String toString() {
        return "Table{" +
                "isOccupied=" + occupied +
                ", number=" + number +
                ", style='" + style + '\'' +
                ", price=" + price +
                ", reserves=" + reserves +
                '}';
    }
}
