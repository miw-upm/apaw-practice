package es.upm.miw.apaw_practice.adapters.mongodb.library.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.library.daos.CategoryRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.library.entities.CategoryEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.library.Category;
import es.upm.miw.apaw_practice.domain.persistence_ports.library.CategoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("categoryPersistence")
public class CategoryPersistenceMongodb implements CategoryPersistence {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryPersistenceMongodb(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository
                .findByName(name).orElseThrow(() -> new NotFoundException("Category name: " + name))
                .toCategory();
    }

    @Override
    public Category update(Category category) {
        CategoryEntity categoryEntity = this.categoryRepository
                .findByName(category.getName())
                .orElseThrow(() -> new NotFoundException("Category name: " + category.getName()));
        categoryEntity.fromCategory(category);
        return this.categoryRepository.save(categoryEntity).toCategory();
    }
}
