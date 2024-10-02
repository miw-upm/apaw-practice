package es.upm.miw.apaw_practice.domain.models.boardgame_cafe;

public class Game {
    private String gameName;
    private int numPlayers;
    private String genre;
    private int numberOfCopies;

    public Game() {
        //empty for framework
    }

    public Game(String gameName, int numPlayers, String genre, int numberOfCopies) {
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
                "gameName='" + gameName + '\'' +
                ", numPlayers=" + numPlayers +
                ", genre='" + genre + '\'' +
                ", numberOfCopies=" + numberOfCopies +
                '}';
    }
}