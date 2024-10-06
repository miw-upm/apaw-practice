package es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities;

import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Game;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class GameEntity {
    @Id
    private String gameName;
    private Integer numPlayers;
    private String genre;
    private Integer numberOfCopies;

    public GameEntity() {
        //empty for framework
    }

    public GameEntity(Game game) {
        this.fromGame(game);
    }

    public void fromGame (Game game) {
        BeanUtils.copyProperties(game, this);
    }

    public Game toGame() {
        Game game = new Game();
        BeanUtils.copyProperties(this, game);
        return game;
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
    public int hashCode() {
        return gameName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (gameName.equals(((GameEntity) obj).gameName));
    }

    @Override
    public String toString() {
        return "GameEntity{" +
                "gameName='" + gameName + '\'' +
                ", numPlayers=" + numPlayers +
                ", genre='" + genre + '\'' +
                ", numberOfCopies=" + numberOfCopies +
                '}';
    }
}