package es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities;

import es.upm.miw.apaw_practice.domain.models.videogame.Console;
import es.upm.miw.apaw_practice.domain.models.videogame.VideoGame;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Document
public class ConsoleEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String consoleReference;
    private Long serialNumber;
    private LocalDate creationDate;
    @DBRef
    private List<VideoGamerEntity> videoGameEntities;
    private Boolean portable;

    public ConsoleEntity() {
        //empty for framework
    }

    public ConsoleEntity(String consoleReference, Long serialNumber, LocalDate creationDate, List<VideoGamerEntity> videoGameEntities , Boolean portable) {
        this.id = UUID.randomUUID().toString();
        this.consoleReference = consoleReference;
        this.serialNumber = serialNumber;
        this.creationDate = creationDate;
        this.videoGameEntities = videoGameEntities;
        this.portable = portable;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getConsoleReference() {
        return consoleReference;
    }
    public void setConsoleReference(String consoleReference) {
        this.consoleReference = consoleReference;
    }
    public Long getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    public List<VideoGamerEntity> getVideoGameEntities() {
        return videoGameEntities;
    }
    public void setVideoGameEntities(List<VideoGamerEntity> videoGameEntities) {
        this.videoGameEntities = videoGameEntities;
    }
    public Boolean getPortable() {
        return portable;
    }
    public void setPortable(Boolean portable) {
        this.portable = portable;
    }

    public Console toConsole(){
        List<VideoGame> videoGames = this.videoGameEntities.stream()
                .map(VideoGamerEntity::toVideoGame)
                .toList();
        return new Console(consoleReference, serialNumber, portable, creationDate, videoGames);
    }

    public void fromConsole(Console console){
        BeanUtils.copyProperties(this, console);
    }

    @Override
    public int hashCode() {
        return this.consoleReference.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (consoleReference.equals(((ConsoleEntity)obj).consoleReference));
    }

    @Override
    public String toString() {
        return "ConsoleEntity{" +
                "id='" + id + '\'' +
                ", consoleReference='" + consoleReference + '\'' +
                ", serialNumber=" + serialNumber +
                ", portable='" + portable + '\'' +
                ", creationDate=" + creationDate +
                ", videoGamesEntities=" + videoGameEntities +
                '}';
    }
}