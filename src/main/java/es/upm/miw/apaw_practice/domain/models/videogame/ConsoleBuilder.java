package es.upm.miw.apaw_practice.domain.models.videogame;

import java.time.LocalDate;
import java.util.ArrayList;

public interface ConsoleBuilder {
    interface ConsoleReference{
        SerialNumber consoleReference(String consoleReference);
    }
    interface SerialNumber{
        Portable serialNumber(long serialNumber);
    }
    interface Portable{
        CreationDate portable(boolean portable);
    }
    interface CreationDate{
        VideoGames creationDate(LocalDate creationDate);
    }
    interface VideoGames{
        Builder videoGames(ArrayList<VideoGame> videoGames);
    }
    interface Builder{
        Console build();
    }
}