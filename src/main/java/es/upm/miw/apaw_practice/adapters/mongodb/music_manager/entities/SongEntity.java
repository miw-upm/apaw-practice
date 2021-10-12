package es.upm.miw.apaw_practice.adapters.mongodb.music_manager.entities;

import es.upm.miw.apaw_practice.domain.models.music_manager.Song;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.UUID;

@Document
public class SongEntity {
    @Id
    private String id;
    private String songTitle;
    private String genre;
    private Integer length;

    private SongEntity() {
        // empty for framework
    }

    public SongEntity(Song song) {
        BeanUtils.copyProperties(song, this);
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Song toSong() {
        Song song = new Song();
        BeanUtils.copyProperties(this, song);
        return song;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SongEntity)) return false;
        SongEntity that = (SongEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(songTitle, that.songTitle) && Objects.equals(genre, that.genre) && Objects.equals(length, that.length);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, songTitle, genre, length);
    }

    @Override
    public String toString() {
        return "SongEntity{" +
                "id='" + id + '\'' +
                ", songTitle='" + songTitle + '\'' +
                ", genre='" + genre + '\'' +
                ", length=" + length +
                '}';
    }
}
