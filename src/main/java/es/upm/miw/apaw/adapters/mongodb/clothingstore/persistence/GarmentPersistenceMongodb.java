
package es.upm.miw.apaw.adapters.mongodb.clothingstore.persistence;

import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.GarmentRepository;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.StoreRepository;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.entities.GarmentEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.clothingstore.Garment;
import es.upm.miw.apaw.domain.persistenceports.clothingstore.GarmentPersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import es.upm.miw.apaw.domain.exceptions.BadGatewayException;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;
import java.util.Optional;

@Repository
public class GarmentPersistenceMongodb implements GarmentPersistence {

    private final GarmentRepository garmentRepository;
    private final StoreRepository storeRepository;
    private final UserRestClient userRestClient;

    @Autowired
    public GarmentPersistenceMongodb(GarmentRepository garmentRepository,
                                     StoreRepository storeRepository,
                                     UserRestClient userRestClient) {
        this.garmentRepository = garmentRepository;
        this.storeRepository = storeRepository;
        this.userRestClient = userRestClient;
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
    public BigDecimal sumDistinctPriceByMobile(String mobile) {
        if (mobile == null || mobile.isBlank()) {
            throw new BadGatewayException("Missing 'mobile' param"); // 也可让 Controller 校验成 400
        }
        try {
            var userDto = this.userRestClient.readByMobile(mobile);
            UUID userId = userDto.getId();
            return this.sumDistinctGarmentPriceByUserId(userId);
        } catch (Exception ex) {
            // 这里把所有下游（apaw-user）抛出的 404/4xx/5xx 统一转换为 502
            throw new BadGatewayException(ex.getMessage());
        }
    }

    private BigDecimal sumDistinctGarmentPriceByUserId(UUID userId) {
        if (userId == null) {
            return BigDecimal.ZERO;
        }

        Set<UUID> garmentIds = new HashSet<>();

        // 遍历所有门店，收集该 user 的所有订单里的 garmentId（去重放进 Set）
        this.storeRepository.findAll().forEach(store -> {
            if (store.getOrders() == null) return;
            store.getOrders().forEach(order -> {
                if (userId.equals(order.getUserId()) && order.getGarments() != null) {
                    order.getGarments().forEach(g -> garmentIds.add(g.getId()));
                }
            });
        });

        if (garmentIds.isEmpty()) {
            return BigDecimal.ZERO;
        }

        return garmentIds.stream()
                .map(this.garmentRepository::findById)        // Optional<GarmentEntity>
                .flatMap(Optional::stream)                     // to stream
                .map(GarmentEntity::getPrice)                  // BigDecimal
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
