package es.upm.miw.apaw_practice.domain.models.restaurant;

import java.math.BigDecimal;
import java.util.List;

public class Table {
    private boolean isOccupied;
    private int number;
    private String style;
    private BigDecimal price;
    private List<Client> clients;
    private List<Reserve> reserves;

    Table(){
        //empty for framework
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
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

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
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
                "isOccupied=" + isOccupied +
                ", number=" + number +
                ", style='" + style + '\'' +
                ", price=" + price +
                ", clients=" + clients +
                ", reserves=" + reserves +
                '}';
    }
}
