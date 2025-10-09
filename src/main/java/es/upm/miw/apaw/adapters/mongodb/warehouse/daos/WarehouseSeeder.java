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

    private final LocationRepository locationRepository;
    private final ProductItemRepository productItemRepository;
    private final MovementOrderRepository movementOrderRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Autowired
    public WarehouseSeeder(LocationRepository locationRepository,
                           ProductItemRepository productItemRepository,
                           MovementOrderRepository movementOrderRepository,
                           OrderDetailRepository orderDetailRepository) {
        this.locationRepository = locationRepository;
        this.productItemRepository = productItemRepository;
        this.movementOrderRepository = movementOrderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public void seedDatabase() {
        log.warn("------- Warehouse Initial Load -----------");

        // 🔹 Crear ubicaciones
        LocationEntity[] locations = {
                LocationEntity.builder()
                        .id(UUID.fromString("bbbb1111-2222-3333-4444-555566660001"))
                        .currentStock(150)
                        .position("Z1-A")
                        .lastUpdateDate(LocalDateTime.now().minusDays(2))
                        .availability(true)
                        .build(),
                LocationEntity.builder()
                        .id(UUID.fromString("bbbb1111-2222-3333-4444-555566660002"))
                        .currentStock(85)
                        .position("Z2-C")
                        .lastUpdateDate(LocalDateTime.now().minusDays(1))
                        .availability(true)
                        .build(),
                LocationEntity.builder()
                        .id(UUID.fromString("bbbb1111-2222-3333-4444-555566660003"))
                        .currentStock(0)
                        .position("Y1-F")
                        .lastUpdateDate(LocalDateTime.now().minusDays(5))
                        .availability(false)
                        .build()
        };
        this.locationRepository.saveAll(Arrays.asList(locations));

        // 🔹 Crear productos
        ProductItemEntity[] productItems = {
                ProductItemEntity.builder()
                        .id(UUID.fromString("bbbb2222-3333-4444-5555-666677770001"))
                        .barcode("PROD-9001")
                        .appoint("Motor hidráulico industrial")
                        .cost(new BigDecimal("220.45"))
                        .unitOfMeasure("unit")
                        .locationEntities(List.of(locations[0], locations[1]))
                        .build(),
                ProductItemEntity.builder()
                        .id(UUID.fromString("bbbb2222-3333-4444-5555-666677770002"))
                        .barcode("PROD-9002")
                        .appoint("Bomba de presión 2.5L")
                        .cost(new BigDecimal("180.99"))
                        .unitOfMeasure("unit")
                        .locationEntities(List.of(locations[1]))
                        .build(),
                ProductItemEntity.builder()
                        .id(UUID.fromString("bbbb2222-3333-4444-5555-666677770003"))
                        .barcode("PROD-9003")
                        .appoint("Aceite lubricante premium 5W-40")
                        .cost(new BigDecimal("45.30"))
                        .unitOfMeasure("liter")
                        .locationEntities(List.of(locations[2]))
                        .build()
        };
        this.productItemRepository.saveAll(Arrays.asList(productItems));

        // 🔹 Crear detalles de orden (OrderDetails)
        OrderDetailEntity[] orderDetails = {
                OrderDetailEntity.builder()
                        .id(UUID.fromString("bbbb3333-aaaa-bbbb-cccc-000000000001"))
                        .qtyRequested(10)
                        .qtyMoved(8)
                        .unitCost(new BigDecimal("220.45"))
                        .productItemEntity(productItems[0])
                        .build(),
                OrderDetailEntity.builder()
                        .id(UUID.fromString("bbbb3333-aaaa-bbbb-cccc-000000000002"))
                        .qtyRequested(5)
                        .qtyMoved(5)
                        .unitCost(new BigDecimal("180.99"))
                        .productItemEntity(productItems[1])
                        .build(),
                OrderDetailEntity.builder()
                        .id(UUID.fromString("bbbb3333-aaaa-bbbb-cccc-000000000003"))
                        .qtyRequested(12)
                        .qtyMoved(10)
                        .unitCost(new BigDecimal("45.30"))
                        .productItemEntity(productItems[2])
                        .build()
        };
        this.orderDetailRepository.saveAll(Arrays.asList(orderDetails));

        // 🔹 Crear órdenes de movimiento con orderDetailEntities asociados
        MovementOrderEntity[] movementOrders = {
                MovementOrderEntity.builder()
                        .id(UUID.fromString("bbbb3333-4444-5555-6666-777788880001"))
                        .registrationDate(LocalDateTime.now().minusDays(3))
                        .typeOrder("OUTBOUND")
                        .partnerName("Talleres Omega S.A.")
                        .partnerAddress("Sevilla, España")
                        .completedOrder(true)
                        .orderDetailEntities(List.of(orderDetails[0], orderDetails[1])) // ✅ ahora sí correcto
                        .build(),
                MovementOrderEntity.builder()
                        .id(UUID.fromString("bbbb3333-4444-5555-6666-777788880002"))
                        .registrationDate(LocalDateTime.now().minusDays(1))
                        .typeOrder("INBOUND")
                        .partnerName("Distribuidora MaxParts")
                        .partnerAddress("Barcelona, España")
                        .completedOrder(false)
                        .orderDetailEntities(List.of(orderDetails[2])) // ✅ una sola relación
                        .build()
        };
        this.movementOrderRepository.saveAll(Arrays.asList(movementOrders));

        log.warn("------- Warehouse seed completed successfully -----------");
    }

    public void deleteAll() {
        this.movementOrderRepository.deleteAll();
        this.productItemRepository.deleteAll();
        this.locationRepository.deleteAll();
    }
}