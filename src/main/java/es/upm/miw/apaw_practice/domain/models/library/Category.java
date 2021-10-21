package es.upm.miw.apaw_practice.domain.models.library;

public class Category {
    private String id;
    private String name;
    private String description;

    public Category() {
        // empty for framework
    }

    public static Builder builder(String name, String desc) {
        return new Builder(name, desc);
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

    public static class Builder {

        private final Category category;

        public Builder(String name, String desc) {
            this.category = new Category();
            this.category.name = name;
            this.category.description = desc;
        }
        public Category build(){
            return this.category;
        }
    }
}
