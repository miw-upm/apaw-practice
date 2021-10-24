package es.upm.miw.apaw_practice.domain.persistence_ports.tv_series;

import es.upm.miw.apaw_practice.domain.models.tv_series.Producer;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ProducerPersistence {
    Producer update(String businessName, Producer producer);

    Producer read(String businessName);

    Stream<Long> findProducerPhonesByTvSeriesYearAndPlayerNationality(int year, String nationality);
}