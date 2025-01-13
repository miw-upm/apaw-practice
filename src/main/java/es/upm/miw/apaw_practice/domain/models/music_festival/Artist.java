package es.upm.miw.apaw_practice.domain.models.music_festival;

public class Artist {

    private String name;
    private String nationality;
    private double rating;

    public Artist() {
        //empty for framework
    }

    public Artist(String name, String nationality, double rating) {
        this.name = name;
        this.nationality = nationality;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", rating=" + rating +
                '}';
    }
}
