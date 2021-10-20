package es.upm.miw.apaw_practice.domain.models.music_manager;

import java.util.Objects;

public class Artist {
    private String firstName;
    private String familyName;
    private Integer age;

    public Artist() {
        // empty for framework
    }

    public static ArtistBuilders.FirstName builder() {
        return new Builder();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist)) return false;
        Artist artist = (Artist) o;
        return Objects.equals(firstName, artist.firstName) && Objects.equals(familyName, artist.familyName) && Objects.equals(age, artist.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, familyName, age);
    }

    @Override
    public String toString() {
        return "Artist{" +
                "firstName='" + firstName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", age=" + age +
                '}';
    }

    public static class Builder implements ArtistBuilders.FirstName, ArtistBuilders.FamilyName, ArtistBuilders.Age, ArtistBuilders.Optionals {

        private final Artist artist;

        public Builder() {
            this.artist = new Artist();
        }

        @Override
        public ArtistBuilders.FamilyName firstName(String firstName) {
            this.artist.firstName = firstName;
            return this;
        }

        @Override
        public ArtistBuilders.Age familyName(String familyName) {
            this.artist.familyName = familyName;
            return this;
        }

        @Override
        public ArtistBuilders.Optionals age(Integer age) {
            this.artist.age = age;
            return this;
        }

        @Override
        public Artist build() {
            return this.artist;
        }
    }
}