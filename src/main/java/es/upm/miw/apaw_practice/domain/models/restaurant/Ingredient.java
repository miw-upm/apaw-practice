package es.upm.miw.apaw_practice.domain.models.restaurant;

import es.upm.miw.apaw_practice.domain.models.restaurant.builders.IngredientBuilders;

import java.util.Objects;

public class Ingredient {

    private String name;
    private Boolean spicy;
    private Boolean available;

    public Ingredient() {
        //empty from framework
    }

    public Ingredient(String name, Boolean spicy, Boolean available) {
        this.name = name;
        this.spicy = spicy;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isSpicy() {
        return spicy;
    }

    public void setSpicy(Boolean spicy) {
        this.spicy = spicy;
    }

    public Boolean isAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return spicy == that.spicy && available == that.available && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, spicy, available);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", spicy=" + spicy +
                ", available=" + available +
                '}';
    }

    public static class Builder implements IngredientBuilders.Name, IngredientBuilders.Optionals {
        private final Ingredient ingredient;

        public Builder() {
            ingredient = new Ingredient();
        }

        @Override
        public IngredientBuilders.Optionals name(String name) {
            this.ingredient.name = name;
            return this;
        }

        @Override
        public IngredientBuilders.Optionals spicy(Boolean spicy) {
            this.ingredient.spicy = spicy;
            return this;
        }

        @Override
        public IngredientBuilders.Optionals available(Boolean available) {
            this.ingredient.available = available;
            return this;
        }

        @Override
        public Ingredient build() {
            return ingredient;
        }
    }

}
