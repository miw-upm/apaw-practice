package es.upm.miw.apaw_practice.adapters.mongodb.martial_art.entities;

import es.upm.miw.apaw_practice.domain.models.martial_art.Style;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.Objects;

public class StyleEntity {
    private String name;
    private String description;
    private Integer popularity;
    private String originCountry;
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
    public Style toStyle() {
        Style style = new Style();
        BeanUtils.copyProperties(this, style);
        return style;
    }
    public StyleEntity(String name, String description, String originCountry, Integer popularity) {
        this.name = name;
        this.description = description;
        this.originCountry = originCountry;
        this.popularity = popularity;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StyleEntity that = (StyleEntity) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(popularity, that.popularity) && Objects.equals(originCountry, that.originCountry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, popularity, originCountry);
    }

    @Override
    public String toString() {
        return "StyleEntity{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", popularity=" + popularity +
                ", originCountry='" + originCountry + '\'' +
                '}';
    }
}
