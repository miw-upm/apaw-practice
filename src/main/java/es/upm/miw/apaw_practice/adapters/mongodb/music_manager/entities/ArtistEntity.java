package es.upm.miw.apaw_practice.adapters.mongodb.music_manager.entities;

import es.upm.miw.apaw_practice.domain.models.music_manager.Artist;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.UUID;

@Document
public class ArtistEntity {
    @Id
    private String id;
    private String firstName;
    private String familyName;
    private Integer age;

    public ArtistEntity() {
        // empty for framework
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void fromArtist(Artist artist) {
        BeanUtils.copyProperties(artist, this);
    }

    public Artist toArtist() {
        Artist artist = new Artist();
        BeanUtils.copyProperties(this, artist);
        return artist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArtistEntity)) return false;
        ArtistEntity that = (ArtistEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(familyName, that.familyName) && Objects.equals(age, that.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, familyName, age);
    }

    @Override
    public String toString() {
        return "ArtistEntity{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", age=" + age +
                '}';
    }
}
