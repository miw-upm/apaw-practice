package es.upm.miw.apaw_practice.domain.services.library;

import es.upm.miw.apaw_practice.domain.models.library.Category;
import es.upm.miw.apaw_practice.domain.models.library.CategoryDescriptionUpdating;
import es.upm.miw.apaw_practice.domain.persistence_ports.library.CategoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class CategoryService {

    private final CategoryPersistence categoryPersistence;

    @Autowired
    public CategoryService(CategoryPersistence categoryPersistence) {
        this.categoryPersistence = categoryPersistence;
    }


    public void updateDescriptions(Stream<CategoryDescriptionUpdating> categoryDescriptionUpdating) {
        categoryDescriptionUpdating.map(categoryNewDescription -> {
                    Category category = this.categoryPersistence.findByName(categoryNewDescription.getName());
                    category.setDescription(categoryNewDescription.getDescription());
                    return category;
                })
                .forEach(this.categoryPersistence::update);
    }
}
