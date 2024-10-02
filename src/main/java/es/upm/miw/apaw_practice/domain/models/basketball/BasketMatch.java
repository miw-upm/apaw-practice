package es.upm.miw.apaw_practice.domain.models.basketball;

import java.time.LocalDateTime;
import java.util.List;

public class BasketMatch {

    private int id;
    private LocalDateTime date;
    private String address;

    public BasketMatch(int id) {
        this.id = id;
        this.date = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
