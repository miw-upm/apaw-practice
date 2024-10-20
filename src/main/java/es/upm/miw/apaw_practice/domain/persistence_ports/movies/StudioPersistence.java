package es.upm.miw.apaw_practice.domain.persistence_ports.movies;

import es.upm.miw.apaw_practice.domain.models.movies.Studio;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface StudioPersistence {

    Studio findByName(String name);
    BigDecimal findMarketCapitalizationSumByAwardCategory(String category);
}
