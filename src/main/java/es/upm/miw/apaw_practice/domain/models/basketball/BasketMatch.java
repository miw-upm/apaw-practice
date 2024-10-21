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
        this.date = date;
        this.address = address;
        this.basketPlayers = basketPlayers;
    }

    public static BasketMatchBuilders.Id builder() {
        return new Builder();
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

    public static class Builder implements BasketMatchBuilders.Id, BasketMatchBuilders.Date,
            BasketMatchBuilders.Address, BasketMatchBuilders.Optionals {

        private final BasketMatch basketMatch;

        public Builder() {
            this.basketMatch = new BasketMatch();
        }

        @Override
        public BasketMatchBuilders.Date id(int id) {
            this.basketMatch.id = id;
            return this;
        }

        @Override
        public BasketMatchBuilders.Address date(LocalDateTime date) {
            this.basketMatch.date = date;
            return this;
        }

        @Override
        public BasketMatchBuilders.Optionals address(String address) {
            this.basketMatch.address = address;
            return this;
        }

        @Override
        public BasketMatchBuilders.Optionals basketPlayers(List<BasketPlayer> basketPlayers) {
            this.basketMatch.basketPlayers = basketPlayers;
            return this;
        }

        @Override
        public BasketMatch build() {
            return this.basketMatch;
        }
    }
}
