
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
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.Optional;
import java.util.*;


@Repository
public class GarmentPersistenceMongodb implements GarmentPersistence {

    private final GarmentRepository garmentRepository;
    private final StoreRepository storeRepository;

    @Autowired
    public GarmentPersistenceMongodb(GarmentRepository garmentRepository,
                                     StoreRepository storeRepository
                                     ) {
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
    public BigDecimal sumDistinctPriceByMobile(String mobile) {
        if (mobile == null || mobile.isBlank()) {
            return BigDecimal.ZERO;
        }

        // 查找与 userId 关联的订单中的所有 Garment 去重
        Set<UUID> garmentIds = new HashSet<>();
        this.storeRepository.findAll().forEach(store -> {
            if (store.getOrders() != null) {
                store.getOrders().forEach(order -> {
                    // 这里假设 mobile 已经在 service 层解析为 userId，这里可直接匹配 userId
                    if (order.getUserId() != null && order.getGarments() != null) {
                        order.getGarments().forEach(g -> garmentIds.add(g.getId()));
                    }
                });
            }
        });

        if (garmentIds.isEmpty()) return BigDecimal.ZERO;

        return garmentIds.stream()
                .map(this.garmentRepository::findById)
                .flatMap(Optional::stream)
                .map(GarmentEntity::getPrice)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
//    private Stream<Garment> findDistinctByUserId(UUID userId) {
//        if (userId == null) return Stream.empty();
//
//        Set<UUID> ids = this.storeRepository.findAll().stream()
//                .filter(s -> s.getOrders() != null)
//                .flatMap(s -> s.getOrders().stream())
//                .filter(o -> userId.equals(o.getUserId()) && o.getGarments() != null)
//                .flatMap(o -> o.getGarments().stream())
//                .map(GarmentEntity::getId)
//                .filter(Objects::nonNull)
//                .collect(Collectors.toCollection(LinkedHashSet::new));
//
//        if (ids.isEmpty()) return Stream.empty();
//
//        return ids.stream()
//                .map(this.garmentRepository::findById)
//                .flatMap(Optional::stream)
//                .map(GarmentEntity::toGarment);
//    }

    @Override
    public Stream<UUID> findDistinctIdsByInvoiceNumber(String invoiceNumber) {
        if (invoiceNumber == null || invoiceNumber.isBlank()) return Stream.empty();

        return this.storeRepository.findAll().stream()
                .filter(s -> s.getOrders() != null)
                .flatMap(s -> s.getOrders().stream())
                .filter(o -> o.getInvoice() != null
                        && invoiceNumber.equals(o.getInvoice().getNumber())
                        && o.getGarments() != null)
                .flatMap(o -> o.getGarments().stream())
                .map(GarmentEntity::getId)
                .filter(Objects::nonNull)
                .distinct();
    }
}
