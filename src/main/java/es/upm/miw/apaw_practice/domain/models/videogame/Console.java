package es.upm.miw.apaw_practice.domain.models.videogame;

import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Game;

import java.time.LocalDate;
import java.util.List;

public class Console {
    private String console;
    private long serialNumber;
    private boolean portable;
    private LocalDate creationDate;
    private List<VideoGame> videoGames;

    private  Console() {
        //empty for framework
    }

    public Console(String console, long serialNumber, boolean portable, LocalDate creationDate, List<VideoGame> videoGames) {
        this.console = console;
        this.serialNumber = serialNumber;
        this.portable = portable;
        this.creationDate = creationDate;
        this.videoGames = videoGames;
    }
    public String getConsole() {
        return console;
    }
    public void setConsole(String console) {
        this.console = console;
    }
    public long getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(long serialNumber) {
        this.serialNumber = serialNumber;
    }
    public boolean isPortable() {
        return portable;
    }
    public void setPortable(boolean portable) {
        this.portable = portable;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    public List<VideoGame> getVideoGames() {
        return videoGames;
    }
    public void setVideoGames(List<VideoGame> videoGames) {
        this.videoGames = videoGames;
    }
    public void addVideoGame(VideoGame videoGame) {
        this.videoGames.add(videoGame);
    }

    @Override
    public String toString() {
        return "Console{" +
                "console='" + console + '\'' +
                ", serialNumber=" + serialNumber +
                ", portable=" + portable +
                ", creationDate=" + creationDate +
                ", videoGames=" + videoGames +
                '}';
    }
}
