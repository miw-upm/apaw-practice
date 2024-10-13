package es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.entities;

import es.upm.miw.apaw_practice.domain.models.delivery_food.MenuCategory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class MenuCategoryEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String description;
    private Boolean active;

    public MenuCategoryEntity() {
    }

    public MenuCategoryEntity(String name, String description, Boolean active) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.active = active;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "MenuCategory{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                '}';
    }

    public MenuCategory toMenuCategory() {
        return new MenuCategory(name, description, active);
    }
}
