package es.upm.miw.apaw.adapters.mongodb.warehouse.daos;
/*
import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.InventoryEntity;
import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.ProductItemEntity;
import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.MovementOrderEntity;
import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.OrderDetailEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
@Profile({"dev", "test"})
@Log4j2
public class WarehouseSeeder {

    private final InventoryRepository inventoryRepository;
    private final ProductItemRepository productItemRepository;
    private final MovementOrderRepository movementOrderRepository;
    private final OrderDetailRepository orderDetailRepository;

    // Simulación del UUID de un User existente (para FK: EMPLOYEE_UUID )
    private final UUID EMPLOYEE_UUID  = UUID.fromString("US000000-0000-0000-0000-000000000001");

    @Autowired
    public WarehouseSeeder(
            InventoryRepository inventoryRepository,
            ProductItemRepository productItemRepository,
            MovementOrderRepository movementOrderRepository,
            OrderDetailRepository orderDetailRepository
    ) {
        this.inventoryRepository = inventoryRepository;
        this.productItemRepository = productItemRepository;
        this.movementOrderRepository = movementOrderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }


     // Inicializa las colecciones con datos de prueba (idéntico al ApiarySeeder).

    public void seedDatabase() {
        log.warn("------- Warehouse Initial Load -----------");

        ProductItemEntity[] productItems = {
            ProductItemEntity.builder()
                    .idProductItem(UUID.fromString("PI000000-0000-0000-0000-000000000001")) // [0] Tuerca M8
                    .barcodeProductItem("TB001").nameProductItem("Tuerca M8")
                    .priceProductItem(new BigDecimal("0.75")).unitOfMeasure("UNIDAD").build(),
            ProductItemEntity.builder()
                    .idProductItem(UUID.fromString("PI000000-0000-0000-0000-000000000002")) // [1] Cable Cat6
                    .barcodeProductItem("CB002").nameProductItem("Cable de Red Cat6")
                    .priceProductItem(new BigDecimal("0.30")).unitOfMeasure("METRO").build()
        };
        this.productItemRepository.saveAll(Arrays.asList(productItems));

        // 2. Crear Órdenes de Movimiento (MovementOrder)
        MovementOrderEntity[] orders = {
                MovementOrderEntity.builder()
                        .idMovementOrder(UUID.fromString("MO000000-0000-0000-0000-000000000001")) // [0] Orden 1
                        .id(EMPLOYEE_UUID) // FK a UserDto
                        .registrationDate(LocalDateTime.now())
                        .typeOrder("Despacho").partnerName("Cliente Mayorista XYZ")
                        .partnerAddress("Calle de la Entrega 10").isCompleted(false)
                        .build()
        };
        this.movementOrderRepository.saveAll(Arrays.asList(orders));

        // 3. Crear Inventario (Inventory) - Relacionado con ProductItem
        InventoryEntity[] inventories = {
                InventoryEntity.builder()
                        .idInventory(UUID.fromString("I0000000-0000-0000-0000-000000000001"))
                        .idProductItem(productItems[0].getIdProductItem()) // FK a Tuerca M8
                        .currentStock(1500).location("Rack A01")
                        .lastUpdateDate(LocalDateTime.now()).build(),
                InventoryEntity.builder()
                        .idInventory(UUID.fromString("I0000000-0000-0000-0000-000000000002"))
                        .idProductItem(productItems[1].getIdProductItem()) // FK a Cable Cat6
                        .currentStock(500).location("Zona C05")
                        .lastUpdateDate(LocalDateTime.now()).build()
        };
        this.inventoryRepository.saveAll(Arrays.asList(inventories));

        // 4. Crear Detalles de la Orden (OrderDetail) - Relacionado con MovementOrder y ProductItem
        OrderDetailEntity[] orderDetails = {
                OrderDetailEntity.builder()
                        .idOrderDetail(UUID.fromString("OD000000-0000-0000-0000-000000000001"))
                        .idMovementOrder(orders[0].getIdMovementOrder()) // FK a Orden 1
                        .idProductItem(productItems[0].getIdProductItem()) // FK a Tuerca M8
                        .qtyRequested(200).qtyMoved(0).unitCost(new BigDecimal("0.70")).build(),
                OrderDetailEntity.builder()
                        .idOrderDetail(UUID.fromString("OD000000-0000-0000-0000-000000000002"))
                        .idMovementOrder(orders[0].getIdMovementOrder()) // FK a Orden 1
                        .idProductItem(productItems[1].getIdProductItem()) // FK a Cable Cat6
                        .qtyRequested(150).qtyMoved(0).unitCost(new BigDecimal("0.25")).build()
        };
        this.orderDetailRepository.saveAll(Arrays.asList(orderDetails));


        log.warn("------- Warehouse seeding completed -----------");

    }

    public void deleteAll() {
        this.orderDetailRepository.deleteAll();
        this.movementOrderRepository.deleteAll();
        this.inventoryRepository.deleteAll();
        this.productItemRepository.deleteAll();
    }

}
*/