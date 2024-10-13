package es.upm.miw.apaw_practice.domain.models.boardgame_cafe;

import java.util.Objects;

public class Game {
    private String gameName;
    private Integer numPlayers;
    private String genre;
    private Integer numberOfCopies;

    public static GameBuilders.GameName buider() {
        return new Builder();
    }

    public Game() {
        //empty for framework
    }

    public Game(String gameName, Integer numPlayers, String genre, Integer numberOfCopies) {
        this.gameName = gameName;
        this.numPlayers = numPlayers;
        this.genre = genre;
        this.numberOfCopies = numberOfCopies;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Integer getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(Integer numPlayers) {
        this.numPlayers = numPlayers;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(Integer numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(gameName, game.gameName) &&
                Objects.equals(numPlayers, game.numPlayers) &&
                Objects.equals(genre, game.genre) &&
                Objects.equals(numberOfCopies, game.numberOfCopies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameName, numPlayers, genre, numberOfCopies);
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameName='" + gameName + '\'' +
                ", numPlayers=" + numPlayers +
                ", genre='" + genre + '\'' +
                ", numberOfCopies=" + numberOfCopies +
                '}';
    }

    public static class Builder implements GameBuilders.GameName, GameBuilders.NumPlayers, GameBuilders.Genre, GameBuilders.NumberOfCopies, GameBuilders.Builder {

        private final Game instance;

        public Builder() {
            instance = new Game();
        }

        @Override
        public GameBuilders.NumPlayers gameName(String gameName) {
            instance.gameName = gameName;
            return this;
        }

        @Override
        public GameBuilders.Genre numPlayers(Integer numPlayers) {
            instance.numPlayers = numPlayers;
            return this;
        }

        @Override
        public GameBuilders.NumberOfCopies genre(String genre) {
            instance.genre = genre;
            return this;
        }

        @Override
        public GameBuilders.Builder numberOfCopies(Integer numberOfCopies) {
            instance.numberOfCopies = numberOfCopies;
            return this;
        }

        @Override
        public Game build() {
            return instance;
        }
    }
}