package es.upm.miw.apaw_practice.adapters.rest.library;

import es.upm.miw.apaw_practice.domain.models.library.CategoryDescriptionUpdating;
import es.upm.miw.apaw_practice.domain.services.library.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(CategoryResource.CATEGORIES)
public class CategoryResource {
    static final String CATEGORIES = "/library/categories";

    private final CategoryService categoryService;

    @Autowired
    public CategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PatchMapping
    public void updateDescriptions(@RequestBody List<CategoryDescriptionUpdating> categoryDescriptionUpdatingList) {
        this.categoryService.updateDescriptions(categoryDescriptionUpdatingList.stream());
    }
}
