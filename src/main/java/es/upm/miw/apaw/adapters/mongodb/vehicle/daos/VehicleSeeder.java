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
                EngineEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                        .codeEngine("VMIVDS000VIS00000").type("Diesel").displacement(1600.00).build(),
                EngineEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                        .codeEngine("VMIVDS000VIS00001").type("Diesel").displacement(1800.00).build(),
                EngineEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"))
                        .codeEngine("VMIVDS000VIS00002").type("Diesel").displacement(2000.00).build(),
                EngineEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"))
                        .codeEngine("VMIVDS000VIS00003").type("Diesel").displacement(2200.00).build(),
                EngineEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0004"))
                        .codeEngine("VMIVDS000VIS00004").type("Gasolina").displacement(125.00).build(),
                EngineEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0005"))
                        .codeEngine("VMIVDS000VIS00005").type("Gasolina").displacement(500.00).build(),
                EngineEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0006"))
                        .codeEngine("VMIVDS000VIS00006").type("Gasolina").displacement(1100.00).build(),
                EngineEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0007"))
                        .codeEngine("VMIVDS000VIS00007").type("Gasolina").displacement(1250.00).build()
        };
        this.engineRepository.saveAll(Arrays.asList(engineItems));

        ExtraEntity[] extraItems = {
                ExtraEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                        .category("Safety").description("Automatic Emergency Braking (AEB)").price(BigDecimal.valueOf(5000.00)).build(),
                ExtraEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                        .category("Safety").description("Lane Keeping Assist").price(BigDecimal.valueOf(4000.00)).build(),
                ExtraEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"))
                        .category("Safety").description("Airbags").price(BigDecimal.valueOf(500.00)).build(),
                ExtraEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"))
                        .category("Comfort").description("Climate control").price(BigDecimal.valueOf(1500.00)).build(),
                ExtraEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0004"))
                        .category("Comfort").description("Keyless entry and start").price(BigDecimal.valueOf(1500.00)).build(),
                ExtraEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0005"))
                        .category("Technology").description("Navigation system").price(BigDecimal.valueOf(4000.00)).build(),
                ExtraEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0006"))
                        .category("Technology").description("Apple CarPlay / Android Auto").price(BigDecimal.valueOf(1000.00)).build(),
                ExtraEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0007"))
                        .category("Appearance").description("Tinted rear windows").price(BigDecimal.valueOf(1500.00)).build(),
                ExtraEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0008"))
                        .category("Appearance").description("Roof bars and luggage rack").price(BigDecimal.valueOf(1000.00)).build(),
                ExtraEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0009"))
                        .category("Appearance").description("Luggage rack and cases").price(BigDecimal.valueOf(1500.00)).build()
        };
        this.extraRepository.saveAll(Arrays.asList(extraItems));

        DocumentationEntity[] documentationItems = {
                DocumentationEntity.builder().name("ITV").validate(true).build(),
                DocumentationEntity.builder().name("Driving licence").validate(true).build(),
                DocumentationEntity.builder().name("Insurance policy").validate(true).build(),
                DocumentationEntity.builder().name("ITV").validate(false).build(),
                DocumentationEntity.builder().name("Driving licence").validate(false).build(),
                DocumentationEntity.builder().name("Insurance policy").validate(false).build()
        };

        VehicleEntity[] vehicleItems = {
                VehicleEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                        .plate("0000FGG").brand("Peugeot").model("407 sw").registrationDate(LocalDate.of(2006, 01,01))
                        .engineEntity(engineItems[3])
                        .documentationEntities(Arrays.asList(documentationItems[0], documentationItems[1], documentationItems[2]))
                        .extraEntities(Arrays.asList(extraItems[2]))
                        .owner(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                        .build(),
                VehicleEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                        .plate("0001FGG").brand("Peugeot").model("407 sw").registrationDate(LocalDate.of(2006, 02,01))
                        .engineEntity(engineItems[3])
                        .documentationEntities(Arrays.asList(documentationItems[3], documentationItems[4], documentationItems[5]))
                        .extraEntities(Arrays.asList(extraItems[2], extraItems[3]))
                        .owner(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                        .build(),
                VehicleEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"))
                        .plate("0000DHP").brand("Peugeot").model("206 sw").registrationDate(LocalDate.of(2005, 03,01))
                        .engineEntity(engineItems[0])
                        .documentationEntities(Arrays.asList(documentationItems[0], documentationItems[1], documentationItems[2]))
                        .extraEntities(Arrays.asList(extraItems[2]))
                        .owner(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                        .build(),
                VehicleEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"))
                        .plate("0001DHP").brand("Peugeot").model("206 sw").registrationDate(LocalDate.of(2006, 04,01))
                        .engineEntity(engineItems[1])
                        .documentationEntities(Arrays.asList(documentationItems[0], documentationItems[1], documentationItems[2]))
                        .extraEntities(Collections.emptyList())
                        .owner(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                        .build(),
                VehicleEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0004"))
                        .plate("0000BBB").brand("Yamaha").model("TMAX").registrationDate(LocalDate.of(2001, 07,01))
                        .engineEntity(engineItems[4])
                        .documentationEntities(Arrays.asList(documentationItems[3], documentationItems[1], documentationItems[2]))
                        .extraEntities(Collections.emptyList())
                        .owner(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"))
                        .build(),
                VehicleEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0005"))
                        .plate("0000LDS").brand("Benelli").model("TRK 502 X").registrationDate(LocalDate.of(2020, 01,01))
                        .engineEntity(engineItems[5])
                        .documentationEntities(Arrays.asList(documentationItems[0], documentationItems[1], documentationItems[5]))
                        .extraEntities(Arrays.asList(extraItems[9]))
                        .owner(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"))
                        .build(),
                VehicleEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0006"))
                        .plate("0000LHS").brand("Honda").model("CMX500 Rebel").registrationDate(LocalDate.of(2020, 07,01))
                        .engineEntity(engineItems[5])
                        .documentationEntities(Arrays.asList(documentationItems[0], documentationItems[1], documentationItems[5]))
                        .extraEntities(Collections.emptyList())
                        .owner(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0004"))
                        .build(),
                VehicleEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0007"))
                        .plate("0000MMM").brand("Honda").model("Africa Twin CRF1100L").registrationDate(LocalDate.of(2023, 12,31))
                        .engineEntity(engineItems[6])
                        .documentationEntities(Arrays.asList(documentationItems[0], documentationItems[3], documentationItems[2]))
                        .extraEntities(Arrays.asList(extraItems[0], extraItems[1], extraItems[6], extraItems[9]))
                        .owner(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"))
                        .build(),
                VehicleEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0008"))
                        .plate("0001MMM").brand("BMW").model("GS 1250").registrationDate(LocalDate.of(2023, 12,31))
                        .engineEntity(engineItems[7])
                        .documentationEntities(Arrays.asList(documentationItems[0], documentationItems[3], documentationItems[2]))
                        .extraEntities(Arrays.asList(extraItems[0], extraItems[1], extraItems[4], extraItems[5], extraItems[6], extraItems[9]))
                        .owner(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0005"))
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
