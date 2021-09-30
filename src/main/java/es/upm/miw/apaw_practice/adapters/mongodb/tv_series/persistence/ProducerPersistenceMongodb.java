package es.upm.miw.apaw_practice.adapters.mongodb.tv_series.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.tv_series.daos.ProducerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.tv_series.entities.ProducerEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.tv_series.Producer;
import es.upm.miw.apaw_practice.domain.persistence_ports.tv_series.ProducerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("producerPersistence")
public class ProducerPersistenceMongodb implements ProducerPersistence {

    private final ProducerRepository producerRepository;

    @Autowired
    public ProducerPersistenceMongodb(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Override
    public Stream<Producer> readAll() {
        return this.producerRepository
                .findAll().stream()
                .map(ProducerEntity::toProducer);
    }

    @Override
    public Producer create(Producer producer) {
        return this.producerRepository
                .save(new ProducerEntity(producer))
                .toProducer();
    }

    @Override
    public Producer update(String businessName, Producer producer) {
        ProducerEntity producerEntity = this.producerRepository
                .findByBusinessName(producer.getBusinessName())
                .orElseThrow(() -> new NotFoundException("Producer business name: " + producer.getBusinessName()));
        producerEntity.fromProducer(producer);
        return this.producerRepository
                .save(producerEntity)
                .toProducer();
    }

    @Override
    public boolean existBusinessName(String businessName) {
        return this.producerRepository
                .findByBusinessName(businessName)
                .isPresent();
    }

    @Override
    public Producer read(String businessName) {
        return this.producerRepository
                .findByBusinessName(businessName)
                .orElseThrow(() -> new NotFoundException("Producer business name: " + businessName))
                .toProducer();
    }
}
