package es.upm.miw.apaw_practice.domain.models.library;

public class Author {
    private String fullName;
    private String nationality;
    private String writingStyle;

    public Author(String fullName, String nationality, String writingStyle) {
        this.fullName = fullName;
        this.nationality = nationality;
        this.writingStyle = writingStyle;
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
}
