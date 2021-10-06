package es.upm.miw.apaw_practice.adapters.mongodb.music_manager.entities;

import es.upm.miw.apaw_practice.domain.models.music_manager.Album;
import es.upm.miw.apaw_practice.domain.models.music_manager.Artist;
import es.upm.miw.apaw_practice.domain.models.music_manager.Band;
import es.upm.miw.apaw_practice.domain.models.music_manager.Song;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Document
public class AlbumEntity {
    @Id
    private String id;
    @DBRef
    private BandEntity bandEntity;
    @DBRef
    private List<SongEntity> songEntities;
    private String albumTitle;
    private String label;
    private BigDecimal price;
    private LocalDate releaseDate;

    public AlbumEntity(BandEntity bandEntity, List<SongEntity> songEntities, String albumTitle, String label, BigDecimal price, LocalDate releaseDate) {
        this.id = UUID.randomUUID().toString();
        this.bandEntity = bandEntity;
        this.songEntities = songEntities;
        this.albumTitle = albumTitle;
        this.label = label;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    public AlbumEntity() {
        // empty for framework
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BandEntity getBandEntity() {
        return bandEntity;
    }

    public void setBandEntity(BandEntity band) {
        this.bandEntity = band;
    }

    public List<SongEntity> getSongEntities() {
        return songEntities;
    }

    public void setSongEntities(List<SongEntity> songEntities) {
        this.songEntities = songEntities;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Album toAlbum() {
        Album album = new Album();
        BeanUtils.copyProperties(this, album, "bandEntity", "songEntities");
        album.setBand(bandEntity.toBand());
        List<Song> tracks = this.songEntities.stream()
                .map(SongEntity::toSong)
                .collect(Collectors.toList());
        album.setTracks(tracks);
        return album;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AlbumEntity)) return false;
        AlbumEntity that = (AlbumEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(bandEntity, that.bandEntity) && Objects.equals(songEntities, that.songEntities) && Objects.equals(albumTitle, that.albumTitle) && Objects.equals(label, that.label) && Objects.equals(price, that.price) && Objects.equals(releaseDate, that.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bandEntity, songEntities, albumTitle, label, price, releaseDate);
    }

    @Override
    public String toString() {
        return "AlbumEntity{" +
                "id='" + id + '\'' +
                ", bandEntity=" + bandEntity +
                ", songEntities=" + songEntities +
                ", albumTitle='" + albumTitle + '\'' +
                ", label='" + label + '\'' +
                ", price=" + price +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
