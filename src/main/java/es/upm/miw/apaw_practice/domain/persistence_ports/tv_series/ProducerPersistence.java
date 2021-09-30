package es.upm.miw.apaw_practice.domain.persistence_ports.tv_series;

import es.upm.miw.apaw_practice.domain.models.tv_series.Producer;

import java.util.stream.Stream;

public interface ProducerPersistence {
    Stream<Producer> readAll();

    Producer create (Producer producer);

    Producer update(String businessName, Producer producer);

    Producer read(String businessName);

    boolean existBusinessName(String businessName);
}
