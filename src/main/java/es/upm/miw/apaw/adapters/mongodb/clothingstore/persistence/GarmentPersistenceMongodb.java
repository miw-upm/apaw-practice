package es.upm.miw.apaw.adapters.mongodb.clothingstore.persistence;

import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.GarmentRepository;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.StoreRepository;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.entities.GarmentEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.clothingstore.Garment;
import es.upm.miw.apaw.domain.persistenceports.clothingstore.GarmentPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

@Repository
public class GarmentPersistenceMongodb implements GarmentPersistence {

    private final GarmentRepository garmentRepository;
    private final StoreRepository storeRepository;

    @Autowired
    public GarmentPersistenceMongodb(GarmentRepository garmentRepository,
                                     StoreRepository storeRepository) {
        this.garmentRepository = garmentRepository;
        this.storeRepository = storeRepository;
    }

    @Override
    public Garment create(Garment garment) {
        GarmentEntity entity = new GarmentEntity(garment);
        entity.setId(UUID.randomUUID());
        return this.garmentRepository.save(entity).toGarment();
    }

    @Override
    public Stream<Garment> findByPriceBetween(BigDecimal min, BigDecimal max) {
        List<GarmentEntity> list = this.garmentRepository.findByPriceBetween(min, max);
        if (list.isEmpty()) {
            list = this.garmentRepository.findAll().stream()
                    .filter(e -> e.getPrice() != null
                            && e.getPrice().compareTo(min) >= 0
                            && e.getPrice().compareTo(max) <= 0)
                    .toList();
        }
        return list.stream().map(GarmentEntity::toGarment);
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

        Set<UUID> seen = new HashSet<>();
        return this.storeRepository.findByOrdersUserId(userId).stream()
                .filter(store -> store.getOrders() != null)
                .flatMap(store -> store.getOrders().stream())
                .filter(order -> userId.equals(order.getUserId()) && order.getGarments() != null)
                .flatMap(order -> order.getGarments().stream())
                .filter(Objects::nonNull)
                .filter(garment -> garment.getId() != null && seen.add(garment.getId()))
                .map(GarmentEntity::getPrice)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
