package es.upm.miw.apaw.adapters.mongodb.recipes.daos;

import es.upm.miw.apaw.adapters.mongodb.recipes.entities.MenuEntity;
import es.upm.miw.apaw.adapters.mongodb.recipes.entities.RecipeItemEntity;
import es.upm.miw.apaw.adapters.mongodb.recipes.entities.RecipeEntity;
import es.upm.miw.apaw.adapters.mongodb.recipes.entities.IngredientEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
@Profile({"dev", "test"})
@Log4j2
public class RecipesSeeder {

    private final MenuRepository menuRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public RecipesSeeder(MenuRepository menuRepository, RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.menuRepository = menuRepository;
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public void seedDatabase() {
        log.warn("------- start of Recipes initial load -----------");
        IngredientEntity[] ingredients = {
                IngredientEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                        .label("Sugar").measurementUnit("g").unitQuantity(150.0).marketPrice(new BigDecimal("0.75")).build(),
                IngredientEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"))
                        .label("Flour").measurementUnit("g").unitQuantity(1000.0).marketPrice(new BigDecimal("1.20")).build(),
                IngredientEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"))
                        .label("Butter").measurementUnit("g").unitQuantity(250.0).marketPrice(new BigDecimal("2.50")).build(),
                IngredientEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0004"))
                        .label("Milk").measurementUnit("ml").unitQuantity(1000.0).marketPrice(new BigDecimal("1.10")).build(),
                IngredientEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0005"))
                        .label("Eggs").measurementUnit("pcs").unitQuantity(12.0).marketPrice(new BigDecimal("2.80")).build(),
                IngredientEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0006"))
                        .label("Salt").measurementUnit("g").unitQuantity(500.0).marketPrice(new BigDecimal("0.50")).build(),
                IngredientEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0007"))
                        .label("Olive Oil").measurementUnit("ml").unitQuantity(500.0).marketPrice(new BigDecimal("4.75")).build()
        };
        this.ingredientRepository.saveAll(Arrays.asList(ingredients));

        RecipeItemEntity[] recipeItems = {
                RecipeItemEntity.builder().ingredientEntity(ingredients[2]).quantity(150.0).specifications("Room temperature").optional(true).build(),
                RecipeItemEntity.builder().ingredientEntity(ingredients[0]).quantity(100.0).specifications("Granulated, white").optional(false).build(),
                RecipeItemEntity.builder().ingredientEntity(ingredients[1]).quantity(250.0).specifications("All-purpose flour, sifted").optional(false).build(),
                RecipeItemEntity.builder().ingredientEntity(ingredients[4]).quantity(2.0).specifications("Large, free-range").optional(false).build(),
                RecipeItemEntity.builder().ingredientEntity(ingredients[3]).quantity(200.0).specifications("Whole milk, slightly warmed").optional(false).build(),
                RecipeItemEntity.builder().ingredientEntity(ingredients[5]).quantity(2.0).specifications("Pinch of fine salt").optional(true).build(),
                RecipeItemEntity.builder().ingredientEntity(ingredients[6]).quantity(20.0).specifications("Extra virgin, for greasing pan").optional(false).build(),
                RecipeItemEntity.builder().ingredientEntity(ingredients[1]).quantity(300.0).specifications("Bread flour").optional(false).build(),
                RecipeItemEntity.builder().ingredientEntity(ingredients[5]).quantity(5.0).specifications("Sea salt").optional(false).build(),
                RecipeItemEntity.builder().ingredientEntity(ingredients[6]).quantity(30.0).specifications("Olive oil for dough").optional(true).build(),
                RecipeItemEntity.builder().ingredientEntity(ingredients[3]).quantity(100.0).specifications("Milk for dough").optional(false).build(),
                RecipeItemEntity.builder().ingredientEntity(ingredients[0]).quantity(50.0).specifications("Sugar for sauce").optional(false).build(),
                RecipeItemEntity.builder().ingredientEntity(ingredients[2]).quantity(100.0).specifications("Melted butter").optional(false).build(),
                RecipeItemEntity.builder().ingredientEntity(ingredients[4]).quantity(1.0).specifications("Egg for glaze").optional(false).build()
        };

        RecipeEntity[] recipes = {
                RecipeEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0010")).referenceNumber("1").title("Butter Cookies")
                        .instructions("Cream butter and sugar, add flour, shape cookies, bake at 180°C for 15 minutes.").servings(6)
                        .itemEntities(List.of(recipeItems[0], recipeItems[1], recipeItems[2])).build(),
                RecipeEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0011")).referenceNumber("2").title("Omelette")
                        .instructions("Whisk eggs with milk and salt, cook on medium heat with butter.").servings(2)
                        .itemEntities(List.of(recipeItems[3], recipeItems[4], recipeItems[5])).build(),
                RecipeEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0012")).referenceNumber("3").title("Simple Salad Dressing")
                        .instructions("Combine olive oil and salt, whisk until emulsified.").servings(4)
                        .itemEntities(List.of(recipeItems[6])).build(),
                RecipeEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0013")).referenceNumber("4").title("Homemade Bread")
                        .instructions("Mix flour, salt, olive oil, and milk, knead and bake until golden.").servings(4)
                        .itemEntities(List.of(recipeItems[7], recipeItems[8], recipeItems[9], recipeItems[10])).build(),
                RecipeEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0014")).referenceNumber("5").title("Caramel Sauce")
                        .instructions("Melt sugar, add butter, and whisk until smooth.").servings(8)
                        .itemEntities(List.of(recipeItems[11], recipeItems[12])).build(),
                RecipeEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0015")).referenceNumber("6").title("Egg Wash")
                        .instructions("Whisk egg with a splash of milk, brush over pastry before baking.").servings(1)
                        .itemEntities(List.of(recipeItems[13])).build()
        };
        this.recipeRepository.saveAll(Arrays.asList(recipes));

        MenuEntity[] menus = {
                MenuEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000")).internalCode(1L).caption("Christmas")
                        .startDate(LocalDate.of(2025, 10, 5)).type("Vegetarian").userId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"))
                        .recipeEntities(Arrays.asList(recipes[0], recipes[1])).build(),
                MenuEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001")).internalCode(2L).caption("Easter")
                        .startDate(LocalDate.of(2025, 4, 21)).userId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0004"))
                        .recipeEntities(Arrays.asList(recipes[2], recipes[3])).build(),

                MenuEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002")).internalCode(3L).caption("Thanksgiving")
                        .startDate(LocalDate.of(2025, 11, 27)).type("Vegan").userId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                        .recipeEntities(Arrays.asList(recipes[4], recipes[5])).build()
        };
        this.menuRepository.saveAll(Arrays.asList(menus));
        log.warn("------- end of Recipes initial load -----------");
    }

    public void deleteAll() {
        this.menuRepository.deleteAll();
        this.recipeRepository.deleteAll();
        this.ingredientRepository.deleteAll();
    }

}
