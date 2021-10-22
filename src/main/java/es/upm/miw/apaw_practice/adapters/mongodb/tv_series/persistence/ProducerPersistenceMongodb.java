package es.upm.miw.apaw_practice.adapters.mongodb.tv_series.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.tv_series.daos.ProducerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.tv_series.entities.ProducerEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.tv_series.Producer;
import es.upm.miw.apaw_practice.domain.persistence_ports.tv_series.ProducerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("producerPersistence")
public class ProducerPersistenceMongodb implements ProducerPersistence {

    private final ProducerRepository producerRepository;

    @Autowired
    public ProducerPersistenceMongodb(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Override
    public Producer update(String businessName, Producer producer) {
        ProducerEntity producerEntity = this.producerRepository
                .findByBusinessName(businessName)
                .orElseThrow(() -> new NotFoundException("Producer business name: " + businessName));
        producerEntity.fromProducer(producer);
        return this.producerRepository
                .save(producerEntity)
                .toProducer();
    }

    @Override
    public Producer read(String businessName) {
        return this.producerRepository
                .findByBusinessName(businessName)
                .orElseThrow(() -> new NotFoundException("Producer business name: " + businessName))
                .toProducer();
    }
}
