package es.upm.miw.apaw_practice.adapters.mongodb.movies.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.movies.daos.StudioRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.movies.entities.MovieEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.movies.entities.StudioEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.movies.Studio;
import es.upm.miw.apaw_practice.domain.persistence_ports.movies.StudioPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Repository("studioPersistence")
public class StudioPersistenceMongodb implements StudioPersistence {

    private final StudioRepository studioRepository;

    @Autowired
    public StudioPersistenceMongodb(StudioRepository studioRepository) {
        this.studioRepository = studioRepository;
    }

    @Override
    public Studio findByName(String name) {
        return this.studioRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Studio name: " + name))
                .toStudio();
    }

    @Override
    public BigDecimal findMarketCapitalizationSumByAwardCategory(String category) {

        if (category == null) {
            return BigDecimal.ZERO;
        }

        List<StudioEntity> studios = this.studioRepository.findAll();

        return studios.stream()
                .filter(studio -> studio.getProducedMovies().stream()
                        .map(MovieEntity::getAwardWon)
                        .filter(Objects::nonNull)
                        .anyMatch(award -> category.equals(award.getCategory())))
                .map(StudioEntity::getMarketCapitalization)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
