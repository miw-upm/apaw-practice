package es.upm.miw.apaw_practice.adapters.mongodb.library.entities;

import es.upm.miw.apaw_practice.domain.models.library.Category;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.UUID;

@Document
public class CategoryEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String description;

    public CategoryEntity() {
        // empty for framework
    }

    public CategoryEntity(String name, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
    }

    public Category toCategory() {
        Category category = new Category();
        BeanUtils.copyProperties(this, category);
        return category;
    }

    public void fromCategory(Category category) {
        BeanUtils.copyProperties(category, this);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity that = (CategoryEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
