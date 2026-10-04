package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.stucktaskdetector.postgres.StuckTaskAlertEntity;
import es.upm.miw.apaw.adapters.out.stucktaskdetector.postgres.StuckTaskAlertRepository;
import es.upm.miw.apaw.adapters.out.stucktaskdetector.postgres.StuckTaskRuleEntity;
import es.upm.miw.apaw.adapters.out.stucktaskdetector.postgres.StuckTaskRuleRepository;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskAlert;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskRule;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Log4j2
@Component
@Profile({"dev", "test"})
@Order(5)
@RequiredArgsConstructor
public class StuckTaskDetectorSeederForDev implements ApplicationRunner {
    private static final String USER_PREFIX = "aaaaaaaa-bbbb-cccc-dddd-eeeeffff";

    private static final String RULE_PREFIX = "55555555-6666-7777-8888-9999aaaa";
    public static final UUID RULE_ID_0 = UUID.fromString(RULE_PREFIX + "0000");
    public static final StuckTaskRule RULE_0 = StuckTaskRule.builder()
            .id(RULE_ID_0)
            .name("Tax procedure inactivity")
            .procedureKeyword("tax")
            .thresholdDays(15)
            .penaltyAmount(new BigDecimal("50.00"))
            .active(true)
            .createdAt(LocalDate.of(2025, 1, 10))
            .createdByUser(user("0000", "600000100", "cliente0"))
            .build();
    public static final UUID RULE_ID_1 = UUID.fromString(RULE_PREFIX + "0001");
    public static final StuckTaskRule RULE_1 = StuckTaskRule.builder()
            .id(RULE_ID_1)
            .name("Eviction procedure delay")
            .procedureKeyword("eviction")
            .thresholdDays(30)
            .active(true)
            .createdAt(LocalDate.of(2025, 2, 5))
            .createdByUser(user("0001", "600000101", "cliente1"))
            .build();
    public static final UUID RULE_ID_2 = UUID.fromString(RULE_PREFIX + "0002");
    public static final StuckTaskRule RULE_2 = StuckTaskRule.builder()
            .id(RULE_ID_2)
            .name("Inheritance procedure backlog")
            .procedureKeyword("inheritance")
            .thresholdDays(45)
            .penaltyAmount(new BigDecimal("120.00"))
            .active(false)
            .createdAt(LocalDate.of(2025, 3, 20))
            .createdByUser(user("0000", "600000100", "cliente0"))
            .build();

    private static final String ALERT_PREFIX = "66666666-7777-8888-9999-aaaabbbb";
    public static final UUID ALERT_ID_0 = UUID.fromString(ALERT_PREFIX + "0000");
    public static final StuckTaskAlert ALERT_0 = StuckTaskAlert.builder()
            .id(ALERT_ID_0)
            .reference("STA-2025-0001")
            .detectedAt(LocalDate.of(2025, 2, 1))
            .resolvedAt(LocalDate.of(2025, 2, 10))
            .escalated(false)
            .resolutionNotes("Task resumed after contacting the client")
            .stuckTaskRule(RULE_0)
            .build();
    public static final UUID ALERT_ID_1 = UUID.fromString(ALERT_PREFIX + "0001");
    public static final StuckTaskAlert ALERT_1 = StuckTaskAlert.builder()
            .id(ALERT_ID_1)
            .reference("STA-2025-0002")
            .detectedAt(LocalDate.of(2025, 3, 1))
            .escalated(true)
            .stuckTaskRule(RULE_0)
            .build();
    public static final UUID ALERT_ID_2 = UUID.fromString(ALERT_PREFIX + "0002");
    public static final StuckTaskAlert ALERT_2 = StuckTaskAlert.builder()
            .id(ALERT_ID_2)
            .detectedAt(LocalDate.of(2025, 3, 15))
            .escalated(false)
            .stuckTaskRule(RULE_1)
            .build();
    public static final UUID ALERT_ID_3 = UUID.fromString(ALERT_PREFIX + "0003");
    public static final StuckTaskAlert ALERT_3 = StuckTaskAlert.builder()
            .id(ALERT_ID_3)
            .reference("STA-2025-0003")
            .detectedAt(LocalDate.of(2025, 4, 10))
            .resolvedAt(LocalDate.of(2025, 5, 2))
            .escalated(true)
            .resolutionNotes("Escalated to the partner in charge and closed")
            .stuckTaskRule(RULE_1)
            .build();
    public static final UUID ALERT_ID_4 = UUID.fromString(ALERT_PREFIX + "0004");
    public static final StuckTaskAlert ALERT_4 = StuckTaskAlert.builder()
            .id(ALERT_ID_4)
            .reference("STA-2025-0004")
            .detectedAt(LocalDate.of(2025, 5, 20))
            .escalated(false)
            .stuckTaskRule(RULE_2)
            .build();

    private final StuckTaskRuleRepository stuckTaskRuleRepository;
    private final StuckTaskAlertRepository stuckTaskAlertRepository;

    private static UserSnapshot user(String idSuffix, String mobile, String firstName) {
        return UserSnapshot.builder()
                .id(UUID.fromString(USER_PREFIX + idSuffix))
                .mobile(mobile)
                .firstName(firstName)
                .build();
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        log.warn("------- Initial Load from JAVA (stuck task rules and alerts) -----------");
        this.seedStuckTaskRules();
        this.seedStuckTaskAlerts();
    }

    private void seedStuckTaskRules() {
        List<StuckTaskRuleEntity> stuckTaskRules = List.of(RULE_0, RULE_1, RULE_2).stream()
                .filter(rule -> !this.stuckTaskRuleRepository.existsById(rule.getId()))
                .map(StuckTaskRuleEntity::new)
                .toList();
        this.stuckTaskRuleRepository.saveAll(stuckTaskRules);
        log.warn("        ------- stuck task rules: {} added", stuckTaskRules.size());
    }

    private void seedStuckTaskAlerts() {
        List<StuckTaskAlertEntity> stuckTaskAlerts = List.of(ALERT_0, ALERT_1, ALERT_2, ALERT_3, ALERT_4).stream()
                .filter(alert -> !this.stuckTaskAlertRepository.existsById(alert.getId()))
                .map(this::toEntity)
                .toList();
        this.stuckTaskAlertRepository.saveAll(stuckTaskAlerts);
        log.warn("        ------- stuck task alerts: {} added", stuckTaskAlerts.size());
    }

    private StuckTaskAlertEntity toEntity(StuckTaskAlert stuckTaskAlert) {
        StuckTaskAlertEntity entity = new StuckTaskAlertEntity(stuckTaskAlert);
        UUID stuckTaskRuleId = stuckTaskAlert.getStuckTaskRule().getId();
        entity.setStuckTaskRule(this.stuckTaskRuleRepository.getReferenceById(stuckTaskRuleId));
        return entity;
    }
}