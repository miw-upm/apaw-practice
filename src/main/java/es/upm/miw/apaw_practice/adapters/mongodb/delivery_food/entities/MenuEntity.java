package es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.entities;

import es.upm.miw.apaw_practice.domain.models.delivery_food.Menu;
import es.upm.miw.apaw_practice.domain.models.delivery_food.MenuCategory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Document
public class MenuEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String description;
    private Double rating;
    @DBRef
    private List<MenuCategoryEntity> categories;

    public MenuEntity() {
    }

    public MenuEntity(String name, String description, List<MenuCategoryEntity> categories, Double rating) {
        this.id = UUID.randomUUID().toString();
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

    public List<MenuCategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<MenuCategoryEntity> categories) {
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
        MenuEntity menu = (MenuEntity) o;
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

    public Menu toMenu() {
        Menu menu = new Menu();
        BeanUtils.copyProperties(this, menu, "categories");
        if (this.categories != null) {
            List<MenuCategory> menuCategories = this.categories.stream()
                    .map(MenuCategoryEntity::toMenuCategory)
                    .toList();
            menu.setCategories(menuCategories);
        }
        return menu;
    }
}
