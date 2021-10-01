package es.upm.miw.apaw_practice.domain.models.music_manager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Album {
    private Band band;
    private List<Song> tracks;
    private String albumTitle;
    private String label;
    private BigDecimal price;
    private LocalDate releaseDate;

    public Album() {
        // empty for framework
    }

    public Album(Band band, List<Song> tracks, String albumTitle, String label, BigDecimal price, LocalDate releaseDate) {
        this.band = band;
        this.tracks = tracks;
        this.albumTitle = albumTitle;
        this.label = label;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public List<Song> getTracks() {
        return tracks;
    }

    public void setTracks(List<Song> tracks) {
        this.tracks = tracks;
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

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Album{" +
                "band=" + band +
                ", tracks=" + tracks +
                ", albumTitle='" + albumTitle + '\'' +
                ", label='" + label + '\'' +
                ", price=" + price +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
