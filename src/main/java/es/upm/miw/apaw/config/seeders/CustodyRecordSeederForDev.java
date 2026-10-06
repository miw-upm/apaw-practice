package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.evidencemanagement.postgres.CustodyRecordEntity;
import es.upm.miw.apaw.adapters.out.evidencemanagement.postgres.CustodyRecordRepository;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.evidencemanagement.CustodyRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Log4j2
@Component
@Profile({"dev", "test"})
@Order(2)
@RequiredArgsConstructor
public class CustodyRecordSeederForDev implements ApplicationRunner {
    public static final String PREFIX = "cccccccc-dddd-eeee-ffff-aaaabbbb";
    public static final String CUSTODIAN_PREFIX = "dddddddd-eeee-ffff-aaaa-bbbbcccc";
    public static final UUID CUSTODIAN_ID_0 = UUID.fromString(CUSTODIAN_PREFIX + "0000");
    public static final UUID CUSTODIAN_ID_1 = UUID.fromString(CUSTODIAN_PREFIX + "0001");
    public static final UUID ID_0 = UUID.fromString(PREFIX + "0000");
    public static final CustodyRecord RECORD_0 = CustodyRecord.builder()
            .id(ID_0)
            .recordedAt(LocalDateTime.of(2025, 1, 10, 9, 0))
            .durationMinutes(30)
            .action("COLLECTED")
            .location("Crime scene")
            .notes("Collected and sealed at the crime scene")
            .custodian(UserSnapshot.builder().id(CUSTODIAN_ID_0).build())
            .build();
    public static final UUID ID_1 = UUID.fromString(PREFIX + "0001");
    public static final CustodyRecord RECORD_1 = CustodyRecord.builder()
            .id(ID_1)
            .recordedAt(LocalDateTime.of(2025, 2, 10, 10, 0))
            .durationMinutes(45)
            .action("TRANSFERRED")
            .location("Forensic laboratory")
            .notes("Delivered to the forensic laboratory")
            .custodian(UserSnapshot.builder().id(CUSTODIAN_ID_1).build())
            .build();
    public static final UUID ID_2 = UUID.fromString(PREFIX + "0002");
    public static final CustodyRecord RECORD_2 = CustodyRecord.builder()
            .id(ID_2)
            .recordedAt(LocalDateTime.of(2025, 3, 10, 11, 0))
            .action("INSPECTED")
            .custodian(UserSnapshot.builder().id(CUSTODIAN_ID_0).build())
            .build();
    public static final UUID ID_3 = UUID.fromString(PREFIX + "0003");
    public static final CustodyRecord RECORD_3 = CustodyRecord.builder()
            .id(ID_3)
            .recordedAt(LocalDateTime.of(2025, 4, 10, 12, 0))
            .durationMinutes(120)
            .action("ANALYZED")
            .location("Forensic laboratory")
            .notes("DNA analysis completed")
            .custodian(UserSnapshot.builder().id(CUSTODIAN_ID_1).build())
            .build();
    public static final UUID ID_4 = UUID.fromString(PREFIX + "0004");
    public static final CustodyRecord RECORD_4 = CustodyRecord.builder()
            .id(ID_4)
            .recordedAt(LocalDateTime.of(2025, 4, 10, 12, 0))
            .durationMinutes(15)
            .action("PHOTOGRAPHED")
            .location("Forensic laboratory")
            .custodian(UserSnapshot.builder().id(CUSTODIAN_ID_0).build())
            .build();
    public static final UUID ID_5 = UUID.fromString(PREFIX + "0005");
    public static final CustodyRecord RECORD_5 = CustodyRecord.builder()
            .id(ID_5)
            .recordedAt(LocalDateTime.of(2025, 6, 10, 10, 30))
            .durationMinutes(60)
            .action("STORED")
            .location("Evidence warehouse")
            .notes("Stored on shelf 12 of the evidence warehouse")
            .custodian(UserSnapshot.builder().id(CUSTODIAN_ID_1).build())
            .build();

    private final CustodyRecordRepository custodyRecordRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        this.seed();
    }

    private void seed() {
        log.warn("------- Initial Load from JAVA -----------");
        List<CustodyRecordEntity> custodyRecords = List.of(RECORD_0, RECORD_1, RECORD_2, RECORD_3, RECORD_4, RECORD_5)
                .stream()
                .filter(custodyRecord -> !this.custodyRecordRepository.existsById(custodyRecord.getId()))
                .map(CustodyRecordEntity::new)
                .toList();
        this.custodyRecordRepository.saveAll(custodyRecords);
        log.warn("        ------- custody records: {} added", custodyRecords.size());
    }
}