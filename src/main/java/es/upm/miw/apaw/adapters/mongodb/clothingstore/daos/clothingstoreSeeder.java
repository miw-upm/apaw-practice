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
    private final OrderRepository orderRepository;
    private final InvoiceRepository invoiceRepository;

    @Autowired
    public clothingstoreSeeder(GarmentRepository garmentRepository,
                               StoreRepository storeRepository,
                               OrderRepository orderRepository,
                               InvoiceRepository invoiceRepository) {
        this.garmentRepository = garmentRepository;
        this.storeRepository = storeRepository;
        this.orderRepository = orderRepository;
        this.invoiceRepository = invoiceRepository;
    }

    public void seedDatabase() {
        log.warn("------- Clothingstore Initial Load -----------");
        this.storeRepository.deleteAll();
        this.orderRepository.deleteAll();
        this.invoiceRepository.deleteAll();
        this.garmentRepository.deleteAll();

        GarmentEntity g1 = GarmentEntity.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7001"))
                .size("M").price(new BigDecimal("59.99")).onSale(true).build();
        GarmentEntity g2 = GarmentEntity.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7002"))
                .size("L").price(new BigDecimal("89.99")).onSale(false).build();
        this.garmentRepository.saveAll(List.of(g1, g2));

        UUID userId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000");

        InvoiceEntity invoice = InvoiceEntity.builder()
                .number("INV-2025-001")
                .issuedAt(LocalDate.of(2025, 10, 5))
                .tax(new BigDecimal("21.00"))
                .dueDate(LocalDate.of(2025, 11, 5))
                .build();
        InvoiceEntity savedInvoice = this.invoiceRepository.save(invoice);

        OrderEntity order = OrderEntity.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7010"))
                .date(LocalDate.of(2025, 10, 6))
                .total(g1.getPrice().add(g2.getPrice()))
                .itemCount(2)
                .status("PAID")
                .paymentMethod("CARD")
                .userId(userId)
                .invoice(savedInvoice)
                .garments(List.of(g1, g2))
                .build();
        OrderEntity savedOrder = this.orderRepository.save(order);

        StoreEntity store = StoreEntity.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7005"))
                .name("Madrid Fashion")
                .address("Calle Gran Vía 25, Madrid")
                .orders(List.of(savedOrder))
                .build();

        this.storeRepository.save(store);
        log.warn("------- Clothingstore Initial Load Completed -----------");
    }

    public void deleteAll() {
        this.storeRepository.deleteAll();
        this.orderRepository.deleteAll();
        this.invoiceRepository.deleteAll();
        this.garmentRepository.deleteAll();
    }
}
