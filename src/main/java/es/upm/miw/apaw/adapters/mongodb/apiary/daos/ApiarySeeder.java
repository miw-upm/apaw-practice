package es.upm.miw.apaw.adapters.mongodb.apiary.daos;

import es.upm.miw.apaw.adapters.mongodb.apiary.entities.ApiaryEntity;
import es.upm.miw.apaw.adapters.mongodb.apiary.entities.HiveEntity;
import es.upm.miw.apaw.adapters.mongodb.apiary.entities.ProductEntity;
import es.upm.miw.apaw.adapters.mongodb.apiary.entities.SaleEntity;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
@Profile({"dev", "test"})
@Log4j2
public class ApiarySeeder {

    private final ApiaryRepository apiaryRepository;
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;

    public ApiarySeeder(ApiaryRepository apiaryRepository,
                        ProductRepository productRepository,
                        SaleRepository saleRepository) {
        this.apiaryRepository = apiaryRepository;
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
    }

    public void seedDatabase() {
        log.warn("------- Apiary Initial Load -----------");

        // ===== APIARIO 1 =====
        ProductEntity[] products1 = {
                ProductEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                        .barcode("P001")
                        .product("Miel de Romero")
                        .price(new BigDecimal("8.00"))
                        .build(),
                ProductEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                        .barcode("P002")
                        .product("Miel de Tomillo")
                        .price(new BigDecimal("7.00"))
                        .build(),
                ProductEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"))
                        .barcode("P003")
                        .product("Cera de Abeja")
                        .price(new BigDecimal("3.50"))
                        .build()
        };
        productRepository.saveAll(Arrays.asList(products1));

        SaleEntity[] sales1 = {
                SaleEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0010"))
                        .idSale(1)
                        .paymentForm(1)
                        .shippingAddress("Calle Mayor 10, Madrid")
                        .amount(new BigDecimal("11.50"))
                        .productEntities(List.of(products1[0], products1[2]))
                        .build(),
                SaleEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0011"))
                        .idSale(2)
                        .paymentForm(2)
                        .shippingAddress("Av. Andalucía 25, Sevilla")
                        .amount(new BigDecimal("7.00"))
                        .productEntities(List.of(products1[1]))
                        .build()
        };
        saleRepository.saveAll(Arrays.asList(sales1));

        HiveEntity[] hives1 = {
                HiveEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100"))
                        .code(101)
                        .type("Langstroth")
                        .queen(true)
                        .installationDate(LocalDate.of(2020, 5, 20))
                        .productEntity(products1[0])
                        .build(),
                HiveEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0101"))
                        .code(102)
                        .type("Layens")
                        .queen(false)
                        .installationDate(LocalDate.of(2021, 3, 15))
                        .productEntity(products1[2])
                        .build(),
        };

        ApiaryEntity apiary1 = ApiaryEntity.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1000"))
                .cadastralRef("0000000-00000000-0001-XX")
                .location("Burgos")
                .rega("REGA00001")
                .hiveEntities(Arrays.asList(hives1))
                .build();
        apiaryRepository.save(apiary1);

        // ===== APIARIO 2 =====
        ProductEntity[] products2 = {
                ProductEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"))
                        .barcode("P004")
                        .product("Miel de Azahar")
                        .price(new BigDecimal("9.00"))
                        .build(),
                ProductEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0004"))
                        .barcode("P005")
                        .product("Polen")
                        .price(new BigDecimal("5.50"))
                        .build()
        };
        productRepository.saveAll(Arrays.asList(products2));

        SaleEntity[] sales2 = {
                SaleEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0020"))
                        .idSale(3)
                        .paymentForm(1)
                        .shippingAddress("Calle Toledo 15, Toledo")
                        .amount(new BigDecimal("14.50"))
                        .productEntities(List.of(products2[0], products2[1]))
                        .build()
        };
        saleRepository.saveAll(Arrays.asList(sales2));

        HiveEntity[] hives2 = {
                HiveEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0102"))
                        .code(103)
                        .type("Top-Bar")
                        .queen(true)
                        .installationDate(LocalDate.of(2022, 4, 10))
                        .productEntity(products2[0])
                        .build(),
                HiveEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0103"))
                        .code(104)
                        .type("Langstroth")
                        .queen(false)
                        .installationDate(LocalDate.of(2023, 1, 5))
                        .productEntity(products2[1])
                        .build(),
        };

        ApiaryEntity apiary2 = ApiaryEntity.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1001"))
                .cadastralRef("0000000-00000000-0002-YY")
                .location("Toledo")
                .rega("REGA00002")
                .hiveEntities(Arrays.asList(hives2))
                .build();
        apiaryRepository.save(apiary2);

        log.warn("------- Apiary seeding completed -----------");
    }

    public void deleteAll() {
        saleRepository.deleteAll();
        productRepository.deleteAll();
        apiaryRepository.deleteAll();
    }
}
