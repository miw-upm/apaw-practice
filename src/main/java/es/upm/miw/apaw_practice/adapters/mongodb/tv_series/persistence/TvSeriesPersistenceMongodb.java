package es.upm.miw.apaw_practice.adapters.mongodb.tv_series.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.tv_series.daos.ProducerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.tv_series.daos.TvSeriesRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.tv_series.entities.EpisodeEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.tv_series.entities.TvSeriesEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.tv_series.TvSeries;
import es.upm.miw.apaw_practice.domain.persistence_ports.tv_series.TvSeriesPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository("tvSeriesPersistence")
public class TvSeriesPersistenceMongodb implements TvSeriesPersistence {

    private final TvSeriesRepository tvSeriesRepository;
    private final ProducerRepository producerRepository;

    @Autowired
    public TvSeriesPersistenceMongodb(TvSeriesRepository tvSeriesRepository, ProducerRepository producerRepository) {
        this.tvSeriesRepository = tvSeriesRepository;
        this.producerRepository = producerRepository;
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

    @Override
    public TvSeries read(String title) {
        return this.tvSeriesRepository
                .findByTitle(title)
                .orElseThrow(() -> new NotFoundException("TvSeries title: " + title))
                .toTvSeries();
    }

    @Override
    public TvSeries update(String title, TvSeries tvSeries) {
        TvSeriesEntity tvSeriesEntity = this.tvSeriesRepository
                .findByTitle(title)
                .orElseThrow(() -> new NotFoundException("TvSeries title: " + title));

        String id = tvSeriesEntity.getId();
        tvSeriesEntity.fromTvSeries(tvSeries);
        tvSeriesEntity.setId(id);
        this.producerRepository.findByBusinessName(tvSeries.getProducer().getBusinessName())
                .ifPresent(tvSeriesEntity::setProducerEntity);
        return this.tvSeriesRepository
                .save(tvSeriesEntity)
                .toTvSeries();
    }

    @Override
    public Optional<Integer> getTotalTvSeriesDurationByBusinessName(String businessName) {
        return this.tvSeriesRepository.findAll().stream()
                .filter(tvSeriesEntity ->
                        tvSeriesEntity.getProducerEntity()
                                .getBusinessName().equals(businessName))
                .flatMap(tvSeriesEntity -> tvSeriesEntity.getEpisodeEntities()
                        .stream())
                .map(EpisodeEntity::getDuration)
                .reduce((integer, integer2) -> integer+=integer2);
    }
}
