package es.upm.miw.apaw_practice.domain.models.music_manager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Album {
    private Band band;
    private List<Song> songs;
    private String name;
    private String label;
    private BigDecimal price;
    private LocalDate releaseDate;

    public Album(Band band, List<Song> songs, String name, String label, BigDecimal price, LocalDate releaseDate) {
        this.band = band;
        this.songs = songs;
        this.name = name;
        this.label = label;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    public Album() {
        // empty for framework
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ", songs=" + songs +
                ", name='" + name + '\'' +
                ", label='" + label + '\'' +
                ", price=" + price +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
