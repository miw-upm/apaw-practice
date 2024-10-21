package es.upm.miw.apaw_practice.domain.models.basketball;

public class BasketPlayer {
    private String dni;
    private String name;
    private int dorsal;
    private int points;

    public BasketPlayer(String dni, String name, int dorsal, int points) {
        this.dni = dni;
        this.name = name;
        this.dorsal = dorsal;
        this.points = points;
    }

    public BasketPlayer() {

    }

    public static BasketPlayerBuilders.Dni builder(){
        return new Builder();
    }

    public String getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public static class Builder implements BasketPlayerBuilders.Dni, BasketPlayerBuilders.Name,
            BasketPlayerBuilders.Dorsal, BasketPlayerBuilders.Points, BasketPlayerBuilders.Builder {

        private final BasketPlayer basketPlayer;

        public Builder() {
            this.basketPlayer = new BasketPlayer();
        }

        @Override
        public BasketPlayerBuilders.Name dni(String dni) {
            this.basketPlayer.dni = dni;
            return this;
        }

        @Override
        public BasketPlayerBuilders.Dorsal name(String name) {
            this.basketPlayer.name = name;
            return this;
        }

        @Override
        public BasketPlayerBuilders.Points dorsal(int dorsal) {
            this.basketPlayer.dorsal = dorsal;
            return this;
        }

        @Override
        public BasketPlayerBuilders.Builder points(int points) {
            this.basketPlayer.points = points;
            return this;
        }

        @Override
        public BasketPlayer build() {
            return this.basketPlayer;
        }
    }
}
