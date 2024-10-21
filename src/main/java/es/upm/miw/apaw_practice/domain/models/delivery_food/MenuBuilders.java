package es.upm.miw.apaw_practice.domain.models.delivery_food;

import java.util.List;

public interface MenuBuilders {

    interface  Name {
        Description name(String name);
    }

    interface  Description {
        Rating description(String description);
    }

    interface  Rating {
        Optionals rating(Double rating);
    }

    interface  Optionals {
        Optionals categories(List<MenuCategory> menuCategories);
        Menu build();
    }

}