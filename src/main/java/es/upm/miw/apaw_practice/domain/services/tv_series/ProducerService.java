package es.upm.miw.apaw_practice.domain.services.tv_series;

import es.upm.miw.apaw_practice.domain.models.tv_series.Producer;
import es.upm.miw.apaw_practice.domain.persistence_ports.tv_series.ProducerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class ProducerService {

    private final ProducerPersistence producerPersistence;

    @Autowired
    public ProducerService(ProducerPersistence producerPersistence) {
        this.producerPersistence = producerPersistence;
    }

    public void update(String businessName, Producer producer) {
        this.producerPersistence.update(businessName,producer);
    }

    public Stream<Long> findProducerPhonesByTvSeriesYearAndPlayerNationality(int year, String nationality) {
        return this.producerPersistence.findProducerPhonesByTvSeriesYearAndPlayerNationality(year, nationality);
    }
}