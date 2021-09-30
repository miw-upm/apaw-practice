package es.upm.miw.apaw_practice.domain.models.music_manager;

public class Song {
    private String songTitle;
    private String genre;
    private Integer length;

    public Song() {
        // empty for framework
    }

    public Song(String songTitle, String genre, Integer length) {
        this.songTitle = songTitle;
        this.genre = genre;
        this.length = length;
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

    @Override
    public String toString() {
        return "Song{" +
                "songTitle='" + songTitle + '\'' +
                ", genre='" + genre + '\'' +
                ", length=" + length + "s." +
                '}';
    }
}
