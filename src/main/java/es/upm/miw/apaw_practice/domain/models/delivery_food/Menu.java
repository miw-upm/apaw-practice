package es.upm.miw.apaw_practice.domain.models.delivery_food;

import java.util.List;
import java.util.Objects;

public class Menu {

    private String name;
    private String description;
    private Double rating;
    private List<MenuCategory> categories;

    public Menu() {
    }

    public Menu(String name, String description, List<MenuCategory> categories, Double rating) {
        this.name = name;
        this.description = description;
        this.categories = categories;
        this.rating = rating;
    }

    public static MenuBuilders.Name builder() {
        return new Builder();
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
        return Objects.equals(name, menu.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", categories=" + categories +
                ", rating=" + rating +
                '}';
    }

    public static class Builder implements MenuBuilders.Name, MenuBuilders.Description, MenuBuilders.Rating,
            MenuBuilders.Optionals {

        private final Menu menu;

        public Builder() {
            this.menu = new Menu();
        }

        @Override
        public MenuBuilders.Description name(String name) {
            menu.name = name;
            return this;
        }

        @Override
        public MenuBuilders.Rating description(String description) {
            menu.description = description;
            return this;
        }

        @Override
        public MenuBuilders.Optionals rating(Double rating) {
            menu.rating = rating;
            return this;
        }

        @Override
        public MenuBuilders.Optionals categories(List<MenuCategory> categories) {
            menu.categories = categories;
            return this;
        }

        @Override
        public Menu build() {
            return this.menu;
        }
    }
}
