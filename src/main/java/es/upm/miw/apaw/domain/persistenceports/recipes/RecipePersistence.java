package es.upm.miw.apaw.domain.persistenceports.recipes;

import es.upm.miw.apaw.domain.models.recipes.Recipe;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface RecipePersistence {

    Recipe readByReferenceNumber(String referenceNumber);

    void delete(String referenceNumber);

    Stream<Recipe> readAll();
}
