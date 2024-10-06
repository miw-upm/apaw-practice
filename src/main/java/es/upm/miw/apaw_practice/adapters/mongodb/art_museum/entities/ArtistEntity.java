package es.upm.miw.apaw_practice.adapters.mongodb.art_museum.entities;

import es.upm.miw.apaw_practice.domain.models.art_museum.Artist;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class ArtistEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String artistName;
    private Integer age;
    private String artStyle;

    public ArtistEntity() {
        //empty for framework
    }

    public ArtistEntity(Artist artist) {
        BeanUtils.copyProperties(artist, this);
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (artistName.equals(((ArtistEntity) obj).artistName));
    }

    @Override
    public int hashCode() {
        return artistName.hashCode();
    }

    @Override
    public String toString() {
        return "ArtistEntity{" +
                "id='" + id + '\'' +
                ", artistName='" + artistName + '\'' +
                ", age=" + age +
                ", artStyle='" + artStyle +
                '}';
    }
}
