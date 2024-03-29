package es.upm.miw.apaw_practice.domain.models.restaurant;

import es.upm.miw.apaw_practice.domain.models.restaurant.builders.DishBuilders;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Dish {

    private String title;
    private BigDecimal price;
    private CategoryRestaurant category;
    private List<Ingredient> ingredients;

    public Dish() {
        //empty from framework
    }

    public Dish(String title, BigDecimal price, CategoryRestaurant category, List<Ingredient> ingredients) {
        this.title = title;
        this.price = price;
        this.category = category;
        this.ingredients = ingredients;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CategoryRestaurant getCategory() {
        return category;
    }

    public void setCategory(CategoryRestaurant category) {
        this.category = category;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return title.equals(dish.title) && Objects.equals(price, dish.price) && Objects.equals(category, dish.category) && Objects.equals(ingredients, dish.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price, category, ingredients);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", ingredients=" + ingredients +
                '}';
    }

    public static class Builder implements DishBuilders.Title, DishBuilders.Optionals {
        private final Dish dish;

        public Builder() {
            dish = new Dish();
        }

        @Override
        public DishBuilders.Optionals title(String title) {
            this.dish.title = title;
            return this;
        }

        @Override
        public DishBuilders.Optionals price(BigDecimal price) {
            this.dish.price = price;
            return this;
        }

        @Override
        public DishBuilders.Optionals category(CategoryRestaurant category) {
            this.dish.category = category;
            return this;
        }

        @Override
        public DishBuilders.Optionals ingredients(List<Ingredient> ingredients) {
            this.dish.ingredients = ingredients;
            return this;
        }

        @Override
        public Dish build() {
            return this.dish;
        }
    }

}
