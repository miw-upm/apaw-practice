package es.upm.miw.apaw_practice.adapters.mongodb.library.entities;

import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.UUID;

@Document
public class AuthorEntity {
    @Id
    private String id;
    private String fullName;
    private String nationality;
    private String writingStyle;

    public AuthorEntity() {
        //empty for framework
    }

    public AuthorEntity(String fullName, String nationality, String writingStyle) {
        this.id = UUID.randomUUID().toString();
        this.fullName = fullName;
        this.nationality = nationality;
        this.writingStyle = writingStyle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getWritingStyle() {
        return writingStyle;
    }

    public void setWritingStyle(String writingStyle) {
        this.writingStyle = writingStyle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorEntity that = (AuthorEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(fullName, that.fullName) && Objects.equals(nationality, that.nationality) && Objects.equals(writingStyle, that.writingStyle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, nationality, writingStyle);
    }

    @Override
    public String toString() {
        return "AuthorEntity{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", nationality='" + nationality + '\'' +
                ", writingStyle='" + writingStyle + '\'' +
                '}';
    }
}
