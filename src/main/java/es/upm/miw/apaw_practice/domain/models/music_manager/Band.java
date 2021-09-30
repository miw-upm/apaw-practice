package es.upm.miw.apaw_practice.domain.models.music_manager;

import java.util.List;

public class Band {
    private String name;
    private String origin;
    private boolean isActive;
    private List<Artist> artist;

    public Band(String name, String origin, boolean isActive, List<Artist> artist) {
        this.name = name;
        this.origin = origin;
        this.isActive = isActive;
        this.artist = artist;
    }

    public Band() {
        //empty for framework
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Artist> getArtist() {
        return artist;
    }

    public void setArtist(List<Artist> artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Band{" +
                "name='" + name + '\'' +
                ", origin='" + origin + '\'' +
                ", isActive=" + isActive +
                ", artist=" + artist +
                '}';
    }
}
