package es.upm.miw.apaw_practice.domain.models.library;

public class CategoryDescriptionUpdating {
    private String name;
    private String description;

    public CategoryDescriptionUpdating() {
        //empty for framework
    }

    public CategoryDescriptionUpdating(String name, String description) {
        this.name = name;
        this.description = description;
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
    public String toString() {
        return "CategoryDescriptionUpdating{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
