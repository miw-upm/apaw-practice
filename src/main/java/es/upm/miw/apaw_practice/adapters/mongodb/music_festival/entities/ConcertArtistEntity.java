package es.upm.miw.apaw_practice.adapters.mongodb.music_festival.entities;

import es.upm.miw.apaw_practice.domain.models.music_festival.ConcertArtist;
import java.util.Objects;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "artists")
public class ConcertArtistEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String nationality;
    private double rating;

    public ConcertArtistEntity() {
        // empty for framework
    }

    public ConcertArtistEntity(String name, String nationality, double rating) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.nationality = nationality;
        this.rating = rating;
    }

    public ConcertArtist toDomain() {
        ConcertArtist artist = new ConcertArtist();
        BeanUtils.copyProperties(this, artist);
        return artist;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ConcertArtistEntity other)) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return "ConcertArtistEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", rating=" + rating +
                '}';
    }
}
