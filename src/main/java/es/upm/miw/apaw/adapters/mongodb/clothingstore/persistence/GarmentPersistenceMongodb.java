package es.upm.miw.apaw.adapters.mongodb.clothingstore.persistence;

import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.GarmentRepository;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.OrderRepository;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.entities.GarmentEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.clothingstore.Garment;
import es.upm.miw.apaw.domain.persistenceports.clothingstore.GarmentPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.bson.types.Decimal128;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

@Repository
public class GarmentPersistenceMongodb implements GarmentPersistence {

    private final GarmentRepository garmentRepository;
    private final OrderRepository orderRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public GarmentPersistenceMongodb(GarmentRepository garmentRepository,
                                     OrderRepository orderRepository,
                                     MongoTemplate mongoTemplate) {
        this.garmentRepository = garmentRepository;
        this.orderRepository = orderRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Garment create(Garment garment) {
        GarmentEntity entity = new GarmentEntity(garment);
        entity.setId(UUID.randomUUID());
        return this.garmentRepository.save(entity).toGarment();
    }

    @Override
    public Stream<Garment> findByPriceBetween(BigDecimal min, BigDecimal max) {
        Query query = new Query(Criteria.where("price")
                .gte(new Decimal128(min))
                .lte(new Decimal128(max)));
        return this.mongoTemplate.find(query, GarmentEntity.class)
                .stream()
                .map(GarmentEntity::toGarment);
    }

    @Override
    public Garment update(UUID id, Garment garment) {
        GarmentEntity entity = this.garmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Garment not found: " + id));
        entity.fromGarment(garment);
        entity.setId(id);
        return this.garmentRepository.save(entity).toGarment();
    }

    @Override
    public Stream<Garment> readAll() {
        return this.garmentRepository.findAll()
                .stream()
                .map(GarmentEntity::toGarment);
    }

    @Override
    public void delete(UUID id) {
        if (!this.garmentRepository.existsById(id)) {
            throw new NotFoundException("Garment not found: " + id);
        }
        this.garmentRepository.deleteById(id);
    }

    @Override
    public BigDecimal sumDistinctPriceByUserId(UUID userId) {
        if (userId == null) {
            return BigDecimal.ZERO;
        }

        return this.orderRepository.findByUserId(userId).stream()
                .filter(order -> order.getGarments() != null)
                .flatMap(order -> order.getGarments().stream())
                .filter(Objects::nonNull)
                .filter(garment -> garment.getId() != null)
                .distinct()
                .map(GarmentEntity::getPrice)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public Stream<UUID> findDistinctGarmentIdsByInvoiceNumber(String invoiceNumber) {
        if (invoiceNumber == null || invoiceNumber.isBlank()) return Stream.empty();

        return this.orderRepository.findByInvoiceId(invoiceNumber).stream()
                .filter(order -> order.getGarments() != null)
                .flatMap(order -> order.getGarments().stream())
                .map(GarmentEntity::getId)
                .filter(Objects::nonNull)
                .distinct();
    }
}
