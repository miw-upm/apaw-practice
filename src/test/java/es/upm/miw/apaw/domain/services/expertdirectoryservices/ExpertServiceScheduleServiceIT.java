package es.upm.miw.apaw.domain.services.expertdirectoryservices;

import es.upm.miw.apaw.adapters.out.expertdirectoryservices.postgres.ExpertServiceScheduleEntity;
import es.upm.miw.apaw.adapters.out.expertdirectoryservices.postgres.ExpertServiceScheduleRepository;
import es.upm.miw.apaw.adapters.out.expertdirectoryservices.postgres.LegalExpertProfileEntity;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.CreationExpertServiceSchedule;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.ExpertServiceSchedule;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.LegalExpertProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ExpertServiceScheduleServiceIT {

    @Autowired
    private ExpertServiceScheduleService expertServiceScheduleService;

    @Autowired
    private ExpertServiceScheduleRepository expertServiceScheduleRepository;

    @Test
    @Transactional
    void testCreate() {
        UUID profileId1 = UUID.fromString("00000000-0000-0000-0000-000000000001");
        UUID profileId2 = UUID.fromString("00000000-0000-0000-0000-000000000002");

        CreationExpertServiceSchedule creation = CreationExpertServiceSchedule.builder()
                .tariffCode("TC-TEST-" + UUID.randomUUID().toString().substring(0, 8))
                .description("Servicio pericial IT")
                .rateAmount(new BigDecimal("250.00"))
                .currency("EUR")
                .legalExpertProfileIds(List.of(profileId1, profileId2))
                .build();

        ExpertServiceSchedule schedule = this.expertServiceScheduleService.create(creation);

        assertThat(schedule.getId()).isNotNull();
        assertThat(schedule.getCreationDate()).isEqualTo(LocalDate.now());
        assertThat(schedule.getLegalExpertProfiles()).extracting(LegalExpertProfile::getId)
                .containsExactly(profileId1, profileId2);

        ExpertServiceScheduleEntity entity = this.expertServiceScheduleRepository.findById(schedule.getId())
                .orElseThrow();
        assertThat(entity.getTariffCode()).isEqualTo(creation.getTariffCode());
        assertThat(entity.getLegalExpertProfiles()).extracting(LegalExpertProfileEntity::getId)
                .containsExactly(profileId1, profileId2);
    }
}