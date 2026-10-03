package es.upm.miw.apaw.adapters.out.expertdirectoryservices.postgres;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = "spring.jpa.properties.hibernate.generate_statistics=true")
@ActiveProfiles("test")
@Transactional
@EnabledIfSystemProperty(named = "report.perf", matches = "true")
class ExpertServiceScheduleRepositoryPerformanceIT {
    private static final Logger log = LogManager.getLogger();
    private static final int PROFILES = Integer.getInteger("report.profiles", 5000);
    private static final int PROFILES_PER_SCHEDULE = Integer.getInteger("report.profilesPerSchedule", 5);
    private static final int SPECIALTIES = Integer.getInteger("report.specialties", 20);
    private static final int RUNS = Integer.getInteger("report.runs", 10);
    private static final String SPECIALTY_PREFIX = "PERF-";

    @Autowired
    private ExpertServiceScheduleRepository expertServiceScheduleRepository;
    @Autowired
    private LegalExpertProfileRepository legalExpertProfileRepository;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Test
    void testFindSpecialtyReportRowsPerformance() {
        int schedules = this.seedData();
        Statistics statistics = this.entityManagerFactory.unwrap(SessionFactory.class).getStatistics();

        this.expertServiceScheduleRepository.findSpecialtyReportRows(); // warm-up (plan y caches)
        List<Long> timesMs = new ArrayList<>();
        List<SpecialtyReportRow> rows = List.of();
        for (int run = 0; run < RUNS; run++) {
            statistics.clear();
            long start = System.nanoTime();
            rows = this.expertServiceScheduleRepository.findSpecialtyReportRows();
            timesMs.add((System.nanoTime() - start) / 1_000_000);
            assertThat(statistics.getPrepareStatementCount()).isEqualTo(1);
        }

        log.info("REPORT PERF -> profiles={}, schedules={}, specialties={}, rows={}, runs={}, ms min/avg/max={}/{}/{}",
                PROFILES, schedules, SPECIALTIES, rows.size(), RUNS,
                timesMs.stream().min(Long::compare).orElse(0L),
                timesMs.stream().mapToLong(Long::longValue).average().orElse(0),
                timesMs.stream().max(Long::compare).orElse(0L));
        assertThat(rows).filteredOn(row -> row.specialtyArea().startsWith(SPECIALTY_PREFIX))
                .hasSize(Math.min(SPECIALTIES, PROFILES))
                .extracting(SpecialtyReportRow::totalSchedules)
                .isSortedAccordingTo(Comparator.reverseOrder());
    }

    private int seedData() {
        List<LegalExpertProfileEntity> profiles = IntStream.range(0, PROFILES)
                .mapToObj(index -> LegalExpertProfileEntity.builder()
                        .id(UUID.randomUUID())
                        .taxIdCode("PERF-TAX-" + index)
                        .specialtyArea(SPECIALTY_PREFIX + (index % SPECIALTIES))
                        .yearsOfExperience(index % 40)
                        .requiresPrepayment(false)
                        .partnershipDate(LocalDate.of(2020, 1, 1))
                        .userId(UUID.randomUUID())
                        .build())
                .toList();
        this.legalExpertProfileRepository.saveAll(profiles);

        int schedules = PROFILES / PROFILES_PER_SCHEDULE;
        this.expertServiceScheduleRepository.saveAll(IntStream.range(0, schedules)
                .mapToObj(index -> ExpertServiceScheduleEntity.builder()
                        .id(UUID.randomUUID())
                        .tariffCode("PERF-TAR-" + index)
                        .description("Performance tariff " + index)
                        .rateAmount(BigDecimal.valueOf(50 + index % 200))
                        .currency("EUR")
                        .creationDate(LocalDate.of(2024, 1, 1))
                        .legalExpertProfiles(new ArrayList<>(profiles.subList(
                                index * PROFILES_PER_SCHEDULE,
                                (index + 1) * PROFILES_PER_SCHEDULE)))
                        .build())
                .toList());
        this.entityManager.flush();
        this.entityManager.clear();
        return schedules;
    }
}
