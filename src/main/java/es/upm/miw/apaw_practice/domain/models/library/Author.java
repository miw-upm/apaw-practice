package es.upm.miw.apaw_practice.domain.models.library;

public class Author {
    private String id;
    private String fullName;
    private String nationality;
    private String writingStyle;

    public Author() {
        // empty for framework
    }

    public static Builder builder(String fullName, String nationality) {
        return new Builder(fullName, nationality);
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



    public static class Builder {

        private final Author author;

        public Builder(String fullName, String nationality) {
            this.author = new Author();
            this.author.fullName = fullName;
            this.author.nationality = nationality;
        }

        public Builder writingStyle(String writingStyle) {
            this.author.writingStyle = writingStyle;
            return this;
        }

        public Author build() {
            return this.author;
        }
    }
}
