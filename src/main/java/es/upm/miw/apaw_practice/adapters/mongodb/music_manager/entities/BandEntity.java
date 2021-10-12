package es.upm.miw.apaw_practice.adapters.mongodb.music_manager.entities;

import es.upm.miw.apaw_practice.domain.models.music_manager.Artist;
import es.upm.miw.apaw_practice.domain.models.music_manager.Band;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Document
public class BandEntity {
    @Id
    private String id;
    private String bandName;
    private String origin;
    private Boolean active;
    @DBRef
    private List<ArtistEntity> artistEntities;

    public BandEntity(String bandName, String origin, Boolean active, List<ArtistEntity> artistEntities) {
        this.id = UUID.randomUUID().toString();
        this.bandName = bandName;
        this.origin = origin;
        this.active = active;
        this.artistEntities = artistEntities;
    }

    public BandEntity() {
        // empty for framework
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<ArtistEntity> getArtistEntities() {
        return artistEntities;
    }

    public void setArtistEntities(List<ArtistEntity> artistEntities) {
        this.artistEntities = artistEntities;
    }

    public Band toBand() {
        Band band = new Band();
        BeanUtils.copyProperties(this, band, "artistEntities");
        List<Artist> artists = this.artistEntities.stream()
                .map(ArtistEntity::toArtist)
                .collect(Collectors.toList());
        band.setArtists(artists);
        return band;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BandEntity)) return false;
        BandEntity that = (BandEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(bandName, that.bandName) && Objects.equals(origin, that.origin) && Objects.equals(active, that.active) && Objects.equals(artistEntities, that.artistEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bandName, origin, active, artistEntities);
    }

    @Override
    public String toString() {
        return "BandEntity{" +
                "id='" + id + '\'' +
                ", bandName='" + bandName + '\'' +
                ", origin='" + origin + '\'' +
                ", active=" + active +
                ", artists=" + artistEntities +
                '}';
    }
}
