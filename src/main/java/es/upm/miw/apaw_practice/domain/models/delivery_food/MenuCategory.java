package es.upm.miw.apaw_practice.domain.models.delivery_food;

public class MenuCategory {

    private String name;
    private String description;
    private Boolean active;

    public MenuCategory() {
    }

    public MenuCategory(String name, String description, Boolean active) {
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

    public static MenuCategory ofName(MenuCategory menuCategory) {
        MenuCategory menuCategoryDto = new MenuCategory();
        menuCategoryDto.setName(menuCategory.getName());
        return menuCategoryDto;
    }
}
