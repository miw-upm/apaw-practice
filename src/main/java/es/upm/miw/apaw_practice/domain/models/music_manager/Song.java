package es.upm.miw.apaw_practice.domain.models.music_manager;

public class Song {
    private String songTitle;
    private String genre;
    private int length;

    public Song() {
        // empty for framework
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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
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
