package es.upm.miw.apaw.adapters.mongodb.vehicle.daos;

import es.upm.miw.apaw.adapters.mongodb.vehicle.entities.DocumentationEntity;
import es.upm.miw.apaw.adapters.mongodb.vehicle.entities.EngineEntity;
import es.upm.miw.apaw.adapters.mongodb.vehicle.entities.ExtraEntity;
import es.upm.miw.apaw.adapters.mongodb.vehicle.entities.VehicleEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

@Repository
@Profile({"dev", "test"})
@Log4j2
public class VehicleSeeder {
    private static final String UUID00 = "aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000";
    private static final String UUID01 = "aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001";
    private static final String UUID02 = "aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002";
    private static final String UUID03 = "aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003";
    private static final String UUID04 = "aaaaaaaa-bbbb-cccc-dddd-eeeeffff0004";
    private static final String UUID05 = "aaaaaaaa-bbbb-cccc-dddd-eeeeffff0005";
    private static final String UUID06 = "aaaaaaaa-bbbb-cccc-dddd-eeeeffff0006";
    private static final String UUID07 = "aaaaaaaa-bbbb-cccc-dddd-eeeeffff0007";
    private static final String UUID08 = "aaaaaaaa-bbbb-cccc-dddd-eeeeffff0008";
    private static final String UUID09 = "aaaaaaaa-bbbb-cccc-dddd-eeeeffff0009";
    private static final String DIESEL = "Diesel";
    private static final String GASOLINE = "Gasolina";
    private static final String SAFETY = "Safety";
    private static final String COMFORT = "Comfort";
    private static final String TECHNOLOGY = "Technology";
    private static final String APPEARANCE = "Appearance";
    private static final String PEUGEOT = "Peugeot";


    private final VehicleRepository vehicleRepository;
    private final EngineRepository engineRepository;
    private final ExtraRepository extraRepository;

    @Autowired
    public VehicleSeeder(EngineRepository engineRepository, ExtraRepository extraRepository, VehicleRepository vehicleRepository) {
        this.engineRepository = engineRepository;
        this.extraRepository = extraRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public void seedDatabase() {
        log.warn("------- Vehicle Initial Load -----------");
        EngineEntity[] engineItems = {
                EngineEntity.builder().id(UUID.fromString(UUID00))
                        .codeEngine("VMIVDS000VIS00000").type(DIESEL).displacement(1600.00).build(),
                EngineEntity.builder().id(UUID.fromString(UUID01))
                        .codeEngine("VMIVDS000VIS00001").type(DIESEL).displacement(1800.00).build(),
                EngineEntity.builder().id(UUID.fromString(UUID02))
                        .codeEngine("VMIVDS000VIS00002").type(DIESEL).displacement(2000.00).build(),
                EngineEntity.builder().id(UUID.fromString(UUID03))
                        .codeEngine("VMIVDS000VIS00003").type(DIESEL).displacement(2200.00).build(),
                EngineEntity.builder().id(UUID.fromString(UUID04))
                        .codeEngine("VMIVDS000VIS00004").type(GASOLINE).displacement(125.00).build(),
                EngineEntity.builder().id(UUID.fromString(UUID05))
                        .codeEngine("VMIVDS000VIS00005").type(GASOLINE).displacement(500.00).build(),
                EngineEntity.builder().id(UUID.fromString(UUID06))
                        .codeEngine("VMIVDS000VIS00006").type(GASOLINE).displacement(1100.00).build(),
                EngineEntity.builder().id(UUID.fromString(UUID07))
                        .codeEngine("VMIVDS000VIS00007").type(GASOLINE).displacement(1250.00).build()
        };
        this.engineRepository.saveAll(Arrays.asList(engineItems));

        ExtraEntity[] extraItems = {
                ExtraEntity.builder().id(UUID.fromString(UUID00))
                        .category(SAFETY).description("Automatic Emergency Braking (AEB)").price(BigDecimal.valueOf(5000.00)).build(),
                ExtraEntity.builder().id(UUID.fromString(UUID01))
                        .category(SAFETY).description("Lane Keeping Assist").price(BigDecimal.valueOf(4000.00)).build(),
                ExtraEntity.builder().id(UUID.fromString(UUID02))
                        .category(SAFETY).description("Airbags").price(BigDecimal.valueOf(500.00)).build(),
                ExtraEntity.builder().id(UUID.fromString(UUID03))
                        .category(COMFORT).description("Climate control").price(BigDecimal.valueOf(1500.00)).build(),
                ExtraEntity.builder().id(UUID.fromString(UUID04))
                        .category(COMFORT).description("Keyless entry and start").price(BigDecimal.valueOf(1500.00)).build(),
                ExtraEntity.builder().id(UUID.fromString(UUID05))
                        .category(TECHNOLOGY).description("Navigation system").price(BigDecimal.valueOf(4000.00)).build(),
                ExtraEntity.builder().id(UUID.fromString(UUID06))
                        .category(TECHNOLOGY).description("Apple CarPlay / Android Auto").price(BigDecimal.valueOf(1000.00)).build(),
                ExtraEntity.builder().id(UUID.fromString(UUID07))
                        .category(APPEARANCE).description("Tinted rear windows").price(BigDecimal.valueOf(1500.00)).build(),
                ExtraEntity.builder().id(UUID.fromString(UUID08))
                        .category(APPEARANCE).description("Roof bars and luggage rack").price(BigDecimal.valueOf(1000.00)).build(),
                ExtraEntity.builder().id(UUID.fromString(UUID09))
                        .category(APPEARANCE).description("Luggage rack and cases").price(BigDecimal.valueOf(1500.00)).build()
        };
        this.extraRepository.saveAll(Arrays.asList(extraItems));

        DocumentationEntity[] documentationItems = {
                DocumentationEntity.builder().name("ITV").validate(true).issued(LocalDate.of(2025,10,10)).build(),
                DocumentationEntity.builder().name("Driving licence").validate(true).issued(LocalDate.of(2025,10,10)).build(),
                DocumentationEntity.builder().name("Insurance policy").validate(true).issued(LocalDate.of(2025,10,10)).build(),
                DocumentationEntity.builder().name("ITV").validate(false).issued(LocalDate.of(2025,10,10)).build(),
                DocumentationEntity.builder().name("Driving licence").validate(false).issued(LocalDate.of(2025,10,10)).build(),
                DocumentationEntity.builder().name("Insurance policy").validate(false).issued(LocalDate.of(2025,10,10)).build(),
                DocumentationEntity.builder().name("TestDocument").validate(false).issued(LocalDate.of(2025,10,10)).build()
        };

        VehicleEntity[] vehicleItems = {
                VehicleEntity.builder().id(UUID.fromString(UUID00))
                        .plate("0000FGG").brand(PEUGEOT).model("407 sw").registrationDate(LocalDate.of(2006, 01,01))
                        .engineEntity(engineItems[3])
                        .documentationEntities(Arrays.asList(documentationItems[0], documentationItems[1], documentationItems[2]))
                        .extraEntities(Collections.singletonList(extraItems[2]))
                        .owner(UUID.fromString(UUID00))
                        .build(),
                VehicleEntity.builder().id(UUID.fromString(UUID01))
                        .plate("0001FGG").brand(PEUGEOT).model("407 sw").registrationDate(LocalDate.of(2006, 02,01))
                        .engineEntity(engineItems[3])
                        .documentationEntities(Arrays.asList(documentationItems[3], documentationItems[4]))
                        .extraEntities(Arrays.asList(extraItems[2], extraItems[3]))
                        .owner(UUID.fromString(UUID01))
                        .build(),
                VehicleEntity.builder().id(UUID.fromString(UUID02))
                        .plate("0000DHP").brand(PEUGEOT).model("206 sw").registrationDate(LocalDate.of(2005, 03,01))
                        .engineEntity(engineItems[0])
                        .documentationEntities(Arrays.asList(documentationItems[0], documentationItems[1]))
                        .extraEntities(Arrays.asList(extraItems[2]))
                        .owner(UUID.fromString(UUID00))
                        .build(),
                VehicleEntity.builder().id(UUID.fromString(UUID03))
                        .plate("0001DHP").brand(PEUGEOT).model("206 sw").registrationDate(LocalDate.of(2006, 04,01))
                        .engineEntity(engineItems[1])
                        .documentationEntities(Arrays.asList(documentationItems[0], documentationItems[1]))
                        .extraEntities(Collections.emptyList())
                        .owner(UUID.fromString(UUID01))
                        .build(),
                VehicleEntity.builder().id(UUID.fromString(UUID04))
                        .plate("0000BBB").brand("Yamaha").model("TMAX").registrationDate(LocalDate.of(2001, 07,01))
                        .engineEntity(engineItems[4])
                        .documentationEntities(Arrays.asList(documentationItems[3], documentationItems[1]))
                        .extraEntities(Collections.emptyList())
                        .owner(UUID.fromString(UUID02))
                        .build(),
                VehicleEntity.builder().id(UUID.fromString(UUID05))
                        .plate("0000LDS").brand("Benelli").model("TRK 502 X").registrationDate(LocalDate.of(2020, 01,01))
                        .engineEntity(engineItems[5])
                        .documentationEntities(Arrays.asList(documentationItems[0], documentationItems[1], documentationItems[6]))
                        .extraEntities(Arrays.asList(extraItems[9]))
                        .owner(UUID.fromString(UUID03))
                        .build(),
                VehicleEntity.builder().id(UUID.fromString(UUID06))
                        .plate("0000LHS").brand("Honda").model("CMX500 Rebel").registrationDate(LocalDate.of(2020, 07,01))
                        .engineEntity(engineItems[5])
                        .documentationEntities(Arrays.asList(documentationItems[0], documentationItems[1]))
                        .extraEntities(Collections.emptyList())
                        .owner(UUID.fromString(UUID04))
                        .build(),
                VehicleEntity.builder().id(UUID.fromString(UUID07))
                        .plate("0000MMM").brand("Honda").model("Africa Twin CRF1100L").registrationDate(LocalDate.of(2023, 12,31))
                        .engineEntity(engineItems[6])
                        .documentationEntities(Arrays.asList(documentationItems[4]))
                        .extraEntities(Arrays.asList(extraItems[0], extraItems[1], extraItems[6], extraItems[9]))
                        .owner(UUID.fromString(UUID03))
                        .build(),
                VehicleEntity.builder().id(UUID.fromString(UUID08))
                        .plate("0001MMM").brand("BMW").model("GS 1250").registrationDate(LocalDate.of(2023, 12,31))
                        .engineEntity(engineItems[7])
                        .documentationEntities(Arrays.asList(documentationItems[4]))
                        .extraEntities(Arrays.asList(extraItems[0], extraItems[1], extraItems[4], extraItems[5], extraItems[6], extraItems[9]))
                        .owner(UUID.fromString(UUID05))
                        .build(),
                VehicleEntity.builder().id(UUID.fromString(UUID09))
                        .plate("0000NNN").brand("No").model("Engine").registrationDate(LocalDate.of(2025, 10,9))
                        .engineEntity(null)
                        .documentationEntities(Arrays.asList())
                        .extraEntities(Collections.emptyList())
                        .owner(UUID.fromString(UUID05))
                        .build(),
        };
        this.vehicleRepository.saveAll(Arrays.asList(vehicleItems));
        log.warn("------- Vehicle Finish initial Load -----------");
    }

    public void deleteAll() {
        this.vehicleRepository.deleteAll();
        this.extraRepository.deleteAll();
        this.engineRepository.deleteAll();
    }
}
