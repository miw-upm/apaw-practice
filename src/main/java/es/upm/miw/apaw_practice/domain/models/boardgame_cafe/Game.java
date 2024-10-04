package es.upm.miw.apaw_practice.domain.models.boardgame_cafe;

public class Game {
    private String gameName;
    private Integer numPlayers;
    private String genre;
    private Integer numberOfCopies;

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
    public String toString() {
        return "Game{" +
                "gameName='" + gameName + '\'' +
                ", numPlayers=" + numPlayers +
                ", genre='" + genre + '\'' +
                ", numberOfCopies=" + numberOfCopies +
                '}';
    }
}