package es.upm.miw.apaw.adapters.mongodb.warehouse.daos;

import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.LocationEntity;
import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.MovementOrderEntity;
import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.OrderDetailEntity;
import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.ProductItemEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
@Profile({"dev", "test"})
@Log4j2
public class WarehouseSeeder {

    private final ProductItemRepository productItemRepository;
    private final LocationRepository locationRepository;
    private final MovementOrderRepository movementOrderRepository;

    @Autowired
    public WarehouseSeeder(ProductItemRepository productItemRepository,
                           LocationRepository locationRepository,
                           MovementOrderRepository movementOrderRepository) {
        this.productItemRepository = productItemRepository;
        this.locationRepository = locationRepository;
        this.movementOrderRepository = movementOrderRepository;
    }

    public void seedDatabase() {
        log.warn("------- Warehouse Initial Load -----------");

        ProductItemEntity[] productItems = {
                ProductItemEntity.builder()
                        .id(UUID.fromString("bbbbbbbb-cccc-dddd-eeee-ffffaaa00001"))
                        .barcode("PI-001")
                        .appoint("Wood Screw 10mm")
                        .cost(new BigDecimal("0.20"))
                        .unitOfMeasure("UNIT")
                        .build(),
                ProductItemEntity.builder()
                        .id(UUID.fromString("bbbbbbbb-cccc-dddd-eeee-ffffaaa00002"))
                        .barcode("PI-002")
                        .appoint("Metal Bolt 15mm")
                        .cost(new BigDecimal("0.35"))
                        .unitOfMeasure("UNIT")
                        .build(),
                ProductItemEntity.builder()
                        .id(UUID.fromString("bbbbbbbb-cccc-dddd-eeee-ffffaaa00003"))
                        .barcode("PI-003")
                        .appoint("Plastic Handle")
                        .cost(new BigDecimal("1.15"))
                        .unitOfMeasure("UNIT")
                        .build()
        };
        this.productItemRepository.saveAll(Arrays.asList(productItems));

        // =======================================================
        // 2️⃣ Locations
        // =======================================================
        LocationEntity[] locations = {
                LocationEntity.builder()
                        .id(UUID.fromString("cccccccc-dddd-eeee-ffff-aaaabbbb0001"))
                        .currentStock(100)
                        .position("A-01-01")
                        .lastUpdateDate(LocalDateTime.now().minusDays(1))
                        .productItemEntities(List.of(productItems[0], productItems[1]))
                        .availability(true)
                        .build(),
                LocationEntity.builder()
                        .id(UUID.fromString("cccccccc-dddd-eeee-ffff-aaaabbbb0002"))
                        .currentStock(50)
                        .position("B-02-03")
                        .lastUpdateDate(LocalDateTime.now().minusDays(2))
                        .productItemEntities(List.of(productItems[2]))
                        .availability(true)
                        .build()
        };
        this.locationRepository.saveAll(Arrays.asList(locations));

        // =======================================================
        // 3️⃣ Movement Orders
        // =======================================================
        OrderDetailEntity[] orderDetails = {
                OrderDetailEntity.builder()
                        .productItemEntity(productItems[0])
                        .qtyRequested(10)
                        .qtyMoved(10)
                        .unitCost(productItems[0].getCost())
                        .build(),
                OrderDetailEntity.builder()
                        .productItemEntity(productItems[1])
                        .qtyRequested(5)
                        .qtyMoved(5)
                        .unitCost(productItems[1].getCost())
                        .build(),
                OrderDetailEntity.builder()
                        .productItemEntity(productItems[2])
                        .qtyRequested(15)
                        .qtyMoved(15)
                        .unitCost(productItems[2].getCost())
                        .build()
        };

        MovementOrderEntity[] movementOrders = {
                MovementOrderEntity.builder()
                        .id(UUID.fromString("dddddddd-eeee-ffff-aaaa-bbbbcccc0001"))
                        .registrationDate(LocalDateTime.now().minusHours(4))
                        .typeOrder("INBOUND")
                        .partnerName("Supplier XYZ")
                        .partnerAddress("Calle Mayor 123, Madrid")
                        .completedOrder(true)
                        .userId(UUID.fromString("eeeeeeee-ffff-aaaa-bbbb-ccccdddd0001"))
                        .orderDetailEntities(List.of(orderDetails[0], orderDetails[1]))
                        .build(),
                MovementOrderEntity.builder()
                        .id(UUID.fromString("dddddddd-eeee-ffff-aaaa-bbbbcccc0002"))
                        .registrationDate(LocalDateTime.now().minusHours(2))
                        .typeOrder("OUTBOUND")
                        .partnerName("Customer ABC")
                        .partnerAddress("Av. Central 56, Barcelona")
                        .completedOrder(false)
                        .userId(UUID.fromString("eeeeeeee-ffff-aaaa-bbbb-ccccdddd0001"))
                        .orderDetailEntities(List.of(orderDetails[2]))
                        .build()
        };
        this.movementOrderRepository.saveAll(Arrays.asList(movementOrders));

        log.warn("------- Warehouse seed completed successfully -----------");
    }

    public void deleteAll() {
        this.movementOrderRepository.deleteAll();
        this.locationRepository.deleteAll();
        this.productItemRepository.deleteAll();
    }

}