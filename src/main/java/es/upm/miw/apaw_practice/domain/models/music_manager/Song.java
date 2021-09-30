package es.upm.miw.apaw_practice.domain.models.music_manager;

import java.math.BigDecimal;

public class Song {
    private String name;
    private String genre;
    private BigDecimal price;

    public Song(String name, String genre, BigDecimal price) {
        this.name = name;
        this.genre = genre;
        this.price = price;
    }

    public Song() {
        // empty for framework
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                '}';
    }
}
