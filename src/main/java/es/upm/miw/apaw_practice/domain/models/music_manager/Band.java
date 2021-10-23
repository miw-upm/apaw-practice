package es.upm.miw.apaw_practice.domain.models.music_manager;

import java.util.List;
import java.util.Objects;

public class Band {
    private String bandName;
    private String origin;
    private Boolean active;
    private List<Artist> artists;

    public Band() {
        // empty for framework
    }

    public Band(String bandName, String origin, Boolean active, List<Artist> artists) {
        this.bandName = bandName;
        this.origin = origin;
        this.active = active;
        this.artists = artists;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Band)) return false;
        Band band = (Band) o;
        return Objects.equals(bandName, band.bandName) && Objects.equals(origin, band.origin) && Objects.equals(active, band.active) && Objects.equals(artists, band.artists);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bandName, origin, active, artists);
    }

    @Override
    public String toString() {
        return "Band{" +
                "bandName='" + bandName + '\'' +
                ", origin='" + origin + '\'' +
                ", active=" + active +
                ", artists=" + artists +
                '}';
    }
}
