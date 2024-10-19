package es.upm.miw.apaw_practice.domain.models.movies;

import java.time.LocalDate;

public interface AwardBuilders {
    interface NameCategoryAndYear {
        Name nameCategoryAndYear(String nameCategoryAndYear);
    }

    interface Name {
        Category name(String name);
    }

    interface Category {
        Year category(String category);
    }

    interface Year {
        Builder year(LocalDate year);
    }

    interface Builder {
        Award build();
    }
}
