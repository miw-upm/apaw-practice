package es.upm.miw.apaw_practice.domain.models.basketball;

import java.time.LocalDateTime;
import java.util.List;

public class BasketMatch {

    private int id;
    private LocalDateTime date;
    private String address;
    private List<BasketPlayer> basketPlayers;

    public BasketMatch() {
    }

    public BasketMatch(int id, LocalDateTime date, String address, List<BasketPlayer> basketPlayers) {
        this.id = id;
        this.date = LocalDateTime.now();
        this.address = address;
        this.basketPlayers = basketPlayers;
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

    public void setId(int id) {
        this.id = id;
    }

    public List<BasketPlayer> getPlayers() {
        return basketPlayers;
    }

    public void setPlayers(List<BasketPlayer> players) {
        this.basketPlayers = players;
    }
}
