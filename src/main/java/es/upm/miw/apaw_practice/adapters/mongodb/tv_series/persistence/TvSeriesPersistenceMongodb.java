package es.upm.miw.apaw_practice.adapters.mongodb.tv_series.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.tv_series.daos.TvSeriesRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.tv_series.entities.TvSeriesEntity;
import es.upm.miw.apaw_practice.domain.models.tv_series.TvSeries;
import es.upm.miw.apaw_practice.domain.persistence_ports.tv_series.TvSeriesPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("tvSeriesPersistence")
public class TvSeriesPersistenceMongodb implements TvSeriesPersistence {

    private final TvSeriesRepository tvSeriesRepository;

    @Autowired
    public TvSeriesPersistenceMongodb(TvSeriesRepository tvSeriesRepository) {
        this.tvSeriesRepository = tvSeriesRepository;
    }

    @Override
    public Stream<TvSeries> findAll() {
        return this.tvSeriesRepository.findAll().stream()
                .map(TvSeriesEntity::toTvSeries);
    }

    @Override
    public void deleteByTitle(String title) {
        this.tvSeriesRepository.deleteByTitle(title);
    }
}
