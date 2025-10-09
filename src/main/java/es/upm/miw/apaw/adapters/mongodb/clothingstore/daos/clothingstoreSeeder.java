package es.upm.miw.apaw.adapters.mongodb.clothingstore.daos;

import es.upm.miw.apaw.adapters.mongodb.clothingstore.entities.GarmentEntity;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.entities.InvoiceEntity;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.entities.OrderEntity;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.entities.StoreEntity;
import org.springframework.stereotype.Service;
import lombok.extern.log4j.Log4j2;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Log4j2
@Service
public class clothingstoreSeeder {

    private final GarmentRepository garmentRepository;
    private final StoreRepository storeRepository;

    public clothingstoreSeeder(GarmentRepository garmentRepository,
                               StoreRepository storeRepository) {
        this.garmentRepository = garmentRepository;
        this.storeRepository = storeRepository;
    }

    public void seedDatabase() {
        log.warn("------- Clothingstore Initial Load -----------");
        if (this.garmentRepository.count() == 0) {

            GarmentEntity g1 = GarmentEntity.builder()
                    .id(UUID.randomUUID()).size("M")
                    .price(new BigDecimal("59.99")).onSale(true).build();
            GarmentEntity g2 = GarmentEntity.builder()
                    .id(UUID.randomUUID()).size("L")
                    .price(new BigDecimal("89.99")).onSale(false).build();
            this.garmentRepository.saveAll(List.of(g1, g2));
        }

        if (this.storeRepository.count() == 0) {
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
                    .userId(UUID.randomUUID())
                    .invoice(invoice)           // 强连接：嵌入
                    .garments(garments)         // 弱连接：@DBRef
                    .build();

            StoreEntity store = StoreEntity.builder()
                    .id(UUID.randomUUID())
                    .name("Madrid Fashion")
                    .address("Calle Gran Vía 25, Madrid")
                    .orders(List.of(order))     // 强连接：嵌入
                    .build();

            this.storeRepository.save(store);
        }

        System.out.println("------- Clothingstore Initial Load Completed -----------");
    }

    public void deleteAll() {
        // 先删 Store（其中嵌入 Order/Invoice），再删 Garment（被弱连接引用）
        this.storeRepository.deleteAll();
        this.garmentRepository.deleteAll();
    }
}
