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

        // 1. Crear productos
        ProductEntity[] products = {
                ProductEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                        .barcode("P001").product("Miel de Romero").price(new BigDecimal("8.00")).build(),
                ProductEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                        .barcode("P002").product("Miel de Tomillo").price(new BigDecimal("7.00")).build(),
                ProductEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"))
                        .barcode("P003").product("Cera de Abeja").price(new BigDecimal("3.50")).build()
        };
        this.productRepository.saveAll(Arrays.asList(products));


        // 2. Crear ventas asociadas a productos
        SaleEntity[] sales = {
                SaleEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0010"))
                        .idSale(1).paymentForm(1).shippingAddress("Calle Mayor 10, Madrid")
                        .amount(new BigDecimal("11.50")).productEntities(List.of(products[0], products[2])).build(),
                SaleEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0011"))
                        .idSale(2).paymentForm(2).shippingAddress("Av. Andalucía 25, Sevilla")
                        .amount(new BigDecimal("7.00")).productEntities(List.of(products[1])).build()
        };
        this.saleRepository.saveAll(Arrays.asList(sales));

        //3. Crear Colmenas
        HiveEntity[] hives = {
                HiveEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100"))
                        .code(101).type("Langstroth").queen(true).installationDate(LocalDate.of(2020,5,20))
                        .productEntity(products[0]).build(),
                HiveEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0101"))
                        .code(102).type("Layens").queen(false).installationDate(LocalDate.of(2021,3,15))
                        .productEntity(products[2]).build(),
        };

        // 4. Crear apiario
        ApiaryEntity apiary = ApiaryEntity.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1000"))
                .cadastralRef("0000000-00000000-0001-XX")
                .location("Burgos")
                .rega("REGA00001")
                .hiveEntities(Arrays.asList(hives))
                .build();
        this.apiaryRepository.save(apiary);


        log.warn("------- Apiary seeding completed -----------");
    }

    public void deleteAll() {
        this.saleRepository.deleteAll();
        this.productRepository.deleteAll();
        this.apiaryRepository.deleteAll();



    }
}
