package es.upm.miw.apaw_practice.domain.models.martial_art;

public class Style {
    private String name;
    private String description;
    private Integer popularity;
    private String originCountry;

    public Style() {
        // empty for framework
    }

    public Style(String name, String description, Integer popularity, String originCountry) {
        this.name = name;
        this.description = description;
        this.popularity = popularity;
        this.originCountry = originCountry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    @Override
    public String toString() {
        return "Style{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", popularity=" + popularity +
                ", originCountry='" + originCountry + '\'' +
                '}';
    }
}
