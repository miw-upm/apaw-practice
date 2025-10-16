package es.upm.miw.apaw.adapters.mongodb.clothingstore.daos;

import es.upm.miw.apaw.adapters.mongodb.clothingstore.entities.GarmentEntity;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.entities.InvoiceEntity;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.entities.OrderEntity;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.entities.StoreEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
@Profile({"dev", "test"})
@Log4j2
public class clothingstoreSeeder {

    private final GarmentRepository garmentRepository;
    private final StoreRepository storeRepository;

    @Autowired
    public clothingstoreSeeder(GarmentRepository garmentRepository,
                               StoreRepository storeRepository) {
        this.garmentRepository = garmentRepository;
        this.storeRepository = storeRepository;
    }

    public void seedDatabase() {
        log.warn("------- Clothingstore Initial Load -----------");
        GarmentEntity g1 = GarmentEntity.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7001"))
                .size("M")
                .price(new BigDecimal("59.99"))
                .onSale(true)
                .build();

        GarmentEntity g2 = GarmentEntity.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7002"))
                .size("L")
                .price(new BigDecimal("89.99"))
                .onSale(false)
                .build();

        this.garmentRepository.saveAll(List.of(g1, g2));
        List<GarmentEntity> garments = this.garmentRepository.findAll();

        InvoiceEntity invoice = InvoiceEntity.builder()
                .number("INV-2025-001")
                .issuedAt(LocalDate.of(2025, 10, 5))
                .tax(new BigDecimal("21.00"))
                .dueDate(LocalDate.of(2025, 11, 5))
                .build();

        OrderEntity order = OrderEntity.builder()
                .date(LocalDate.of(2025, 10, 6))
                .total(garments.stream()
                        .map(GarmentEntity::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .itemCount(garments.size())
                .status("PAID")
                .paymentMethod("CARD")
                .userId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7000"))
                .invoice(invoice)       // 强连接：嵌入
                .garments(garments)     // 弱连接：@DBRef
                .build();

        StoreEntity store = StoreEntity.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7005")) // 固定ID，和测试一致
                .name("Madrid Fashion")
                .address("Calle Gran Vía 25, Madrid")
                .orders(List.of(order))
                .build();

        this.storeRepository.save(store);
        this.storeRepository.save(store);

        log.warn("------- Clothingstore Initial Load Completed -----------");
    }
    public void deleteAll() {
        this.storeRepository.deleteAll();
        this.garmentRepository.deleteAll();
    }
}

