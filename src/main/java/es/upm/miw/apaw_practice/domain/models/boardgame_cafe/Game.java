package es.upm.miw.apaw_practice.domain.models.boardgame_cafe;

public class Game {
    private String name;
    private int numPlayers;
    private String genre;
    private int numberOfCopies;

    public Game() {
        //empty for framework
    }

    public Game(String name, int numPlayers, String genre, int numberOfCopies) {
        this.name = name;
        this.numPlayers = numPlayers;
        this.genre = genre;
        this.numberOfCopies = numberOfCopies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", numPlayers=" + numPlayers +
                ", genre='" + genre + '\'' +
                ", numberOfCopies=" + numberOfCopies +
                '}';
    }
}