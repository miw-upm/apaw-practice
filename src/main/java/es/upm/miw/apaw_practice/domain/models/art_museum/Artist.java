package es.upm.miw.apaw_practice.domain.models.art_museum;

import java.util.List;

public class Artist {
    private String artistName;
    private Integer age;
    private String artStyle;
    private List<Artwork> artworks;

    public Artist() {
        //empty for framework
    }

    public Artist(String artistName, Integer age, String artStyle, List<Artwork> artworks) {
        this.artistName = artistName;
        this.age = age;
        this.artStyle = artStyle;
        this.artworks = artworks;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getArtStyle() {
        return artStyle;
    }

    public void setArtStyle(String artStyle) {
        this.artStyle = artStyle;
    }

    public List<Artwork> getArtworks() {
        return artworks;
    }

    public void setArtworks(List<Artwork> artworks) {
        this.artworks = artworks;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "artistName='" + artistName + '\'' +
                ", age=" + age +
                ", artStyle='" + artStyle + '\'' +
                ", artworks=" + artworks +
                '}';
    }
}
