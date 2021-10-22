package es.upm.miw.apaw_practice.domain.persistence_ports.tv_series;

import es.upm.miw.apaw_practice.domain.models.tv_series.TvSeries;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface TvSeriesPersistence {

    void deleteByTitle(String title);

    Stream<TvSeries> findAll();

    TvSeries read(String title);

    TvSeries update(String title, TvSeries tvSeries);
}
