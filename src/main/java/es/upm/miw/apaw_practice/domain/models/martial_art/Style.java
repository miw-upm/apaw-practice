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
    // Método estático para iniciar el proceso de construcción
    public static StyleBuilders.Name builder() {
        return new Builder();
    }

    // Builder interno con pasos definidos a través de interfaces
    public static class Builder implements StyleBuilders.Name, StyleBuilders.Description, StyleBuilders.Popularity, StyleBuilders.OriginCountry, StyleBuilders.Optionals {

        private final Style style;

        public Builder() {
            this.style = new Style();
        }

        @Override
        public StyleBuilders.Description name(String name) {
            this.style.name = name;
            return this;
        }

        @Override
        public StyleBuilders.Popularity description(String description) {
            this.style.description = description;
            return this;
        }

        @Override
        public StyleBuilders.OriginCountry popularity(Integer popularity) {
            this.style.popularity = popularity;
            return this;
        }

        @Override
        public StyleBuilders.Optionals originCountry(String originCountry) {
            this.style.originCountry = originCountry;
            return this;
        }

        @Override
        public Style build() {
            return this.style;
        }
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
