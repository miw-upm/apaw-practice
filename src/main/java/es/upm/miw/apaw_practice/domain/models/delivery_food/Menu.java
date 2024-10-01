package es.upm.miw.apaw_practice.domain.models.delivery_food;

import java.util.List;
import java.util.Objects;

public class Menu {

    private String id;
    private String name;
    private String description;
    private Double rating;
    private List<MenuCategory> categories;

    public Menu() {
    }

    public Menu(String id, String name, String description, List<MenuCategory> categories, Double rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categories = categories;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<MenuCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<MenuCategory> categories) {
        this.categories = categories;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(id, menu.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", categories=" + categories +
                ", rating=" + rating +
                '}';
    }
}
