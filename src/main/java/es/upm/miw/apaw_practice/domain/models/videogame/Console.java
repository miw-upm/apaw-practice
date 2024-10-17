package es.upm.miw.apaw_practice.domain.models.videogame;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Console {
    private String consoleReference;
    private long serialNumber;
    private boolean portable;
    private LocalDate creationDate;
    private List<VideoGame> videoGames;

    public static ConsoleBuilder.ConsoleReference builder(){
        return new Builder();
    }

    private  Console() {
        //empty for framework
    }

    public Console(String consoleReference, long serialNumber, boolean portable, LocalDate creationDate, List<VideoGame> videoGames) {
        this.consoleReference = consoleReference;
        this.serialNumber = serialNumber;
        this.portable = portable;
        this.creationDate = creationDate;
        this.videoGames = videoGames;
    }
    public String getConsoleReference() {
        return consoleReference;
    }
    public void setConsoleReference(String consoleReference) {
        this.consoleReference = consoleReference;
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
                "consoleReference='" + consoleReference + '\'' +
                ", serialNumber=" + serialNumber +
                ", portable=" + portable +
                ", creationDate=" + creationDate +
                ", videoGames=" + videoGames.stream().map(VideoGame::getVideoGameAlias).toList() +
                '}';
    }

    public static class Builder implements ConsoleBuilder.ConsoleReference, ConsoleBuilder.SerialNumber, ConsoleBuilder.Portable, ConsoleBuilder.CreationDate, ConsoleBuilder.VideoGames, ConsoleBuilder.Builder {
        private final Console instance;

        private Builder() {
            instance = new Console();
        }

        @Override
        public ConsoleBuilder.SerialNumber consoleReference(String consoleReference) {
            instance.setConsoleReference(consoleReference);
            return this;
        }

        @Override
        public ConsoleBuilder.Portable serialNumber(long serialNumber) {
            instance.setSerialNumber(serialNumber);
            return this;
        }

        @Override
        public ConsoleBuilder.CreationDate portable(boolean portable) {
            instance.setPortable(portable);
            return this;
        }

        @Override
        public ConsoleBuilder.VideoGames creationDate(LocalDate creationDate) {
            instance.setCreationDate(creationDate);
            return this;
        }

        @Override
        public ConsoleBuilder.Builder videoGames(ArrayList<VideoGame> videoGames){
            instance.setVideoGames(videoGames);
            return this;
        }

        @Override
        public Console build() {
            return instance;
        }
    }
}
