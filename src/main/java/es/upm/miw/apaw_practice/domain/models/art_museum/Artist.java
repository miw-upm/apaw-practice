package es.upm.miw.apaw_practice.domain.models.art_museum;

public class Artist {
    private String artistName;
    private Integer age;
    private String artStyle;

    public Artist() {
        //empty for framework
    }

    public Artist(String artistName, Integer age, String artStyle) {
        this.artistName = artistName;
        this.age = age;
        this.artStyle = artStyle;
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

    @Override
    public String toString() {
        return "Artist{" +
                "artistName='" + artistName + '\'' +
                ", age=" + age +
                ", artStyle='" + artStyle +
                '}';
    }
}
