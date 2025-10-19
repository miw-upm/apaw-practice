package es.upm.miw.apaw.adapters.mongodb.winery.persistence;

import es.upm.miw.apaw.adapters.mongodb.winery.daos.TastingSessionRepository;
import es.upm.miw.apaw.adapters.mongodb.winery.daos.WineRepository;
import es.upm.miw.apaw.adapters.mongodb.winery.entities.WineEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.winery.Wine;
import es.upm.miw.apaw.domain.persistenceports.winery.WinePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Repository("winePersistence")
public class WinePersistenceMongodb implements WinePersistence {

    private final WineRepository wineRepository;
    private final TastingSessionRepository tastingSessionRepository;

    @Autowired
    public WinePersistenceMongodb(WineRepository wineRepository, TastingSessionRepository tastingSessionRepository) {
        this.wineRepository = wineRepository;
        this.tastingSessionRepository = tastingSessionRepository;
    }

    @Override
    public Wine readById(UUID id) {
        return this.wineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(" Wine id: " + id))
                .toWine();
    }

    @Override
    public void delete(UUID id) {
        this.wineRepository.deleteById(id);
    }

    @Override
    public Wine update(Wine wine) {
        WineEntity wineEntity = this.wineRepository
                .findById(wine.getId())
                .orElseThrow(() -> new NotFoundException(" Wine id: " + wine.getId()));
        wineEntity.setPrice(wine.getPrice());
        return this.wineRepository.save(wineEntity).toWine();
    }

    @Override
    public BigDecimal sumPricesByComment(String comment) {
        String search = comment.toLowerCase().trim();
        return this.tastingSessionRepository.findAll().stream()
                .filter(session -> session.getEvaluationEntities() != null &&
                        session.getEvaluationEntities().stream()
                                .anyMatch(e -> e.getComment() != null &&
                                        e.getComment().toLowerCase().contains(search)))
                .flatMap(session -> session.getWineEntities() != null
                        ? session.getWineEntities().stream()
                        : java.util.stream.Stream.empty())
                .map(WineEntity::getPrice)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }





}
