package es.upm.miw.apaw.domain.services.judicialcourt;

import es.upm.miw.apaw.adapters.out.judicialcourt.postgres.JudicialCourtEntity;
import es.upm.miw.apaw.adapters.out.judicialcourt.postgres.JudicialCourtRepository;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.judicialcourt.CreationJudicialCourt;
import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourt;
import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourtStatus;
import es.upm.miw.apaw.domain.model.judicialcourt.LawyerCourtRankingReport;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static es.upm.miw.apaw.config.seeders.JudicialCourtTypeSeederForDev.TYPE_0;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class JudicialCourtServiceIT {
    @Autowired
    private JudicialCourtService judicialCourtService;
    @Autowired
    private JudicialCourtRepository judicialCourtRepository;
    @MockitoBean
    private UserFinder userFinder;

    @BeforeEach
    void setUp() {
        this.judicialCourtRepository.deleteAll();
    }

    @Test
    @Transactional
    void testCreate() {
        UserSnapshot first = this.user("0000", "600000100", "cliente0");
        UserSnapshot second = this.user("0001", "600000101", "cliente1");
        CreationJudicialCourt creation = this.creation(TYPE_0.getId(), List.of(first.getId(), second.getId()));
        when(this.userFinder.findByIds(Set.of(first.getId(), second.getId())))
                .thenReturn(List.of(first, second));

        JudicialCourt judicialCourt = this.judicialCourtService.create(creation);

        assertThat(judicialCourt.getId()).isNotNull();
        assertThat(judicialCourt.getName()).isEqualTo(creation.getName());
        assertThat(judicialCourt.getCity()).isEqualTo(creation.getCity());
        assertThat(judicialCourt.getCreatedAt()).isNotNull();
        assertThat(judicialCourt.getStatus()).isEqualTo(JudicialCourtStatus.ACTIVE);
        assertThat(judicialCourt.getType()).usingRecursiveComparison().isEqualTo(TYPE_0);
        assertThat(judicialCourt.getLawyers())
                .extracting(UserSnapshot::getId)
                .containsExactlyInAnyOrder(first.getId(), second.getId());

        JudicialCourtEntity entity = this.judicialCourtRepository.findById(judicialCourt.getId()).orElseThrow();
        assertThat(entity.getName()).isEqualTo(creation.getName());
        assertThat(entity.getType().getId()).isEqualTo(TYPE_0.getId());
        assertThat(entity.getCity()).isEqualTo(creation.getCity());
        assertThat(entity.getStatus()).isEqualTo(JudicialCourtStatus.ACTIVE);
        assertThat(entity.getLawyerIds()).containsExactlyInAnyOrder(first.getId(), second.getId());
    }

    @Test
    @Transactional
    void testCreateTypeNotFound() {
        UUID missingTypeId = UUID.randomUUID();
        CreationJudicialCourt creation = this.creation(missingTypeId, List.of());
        long before = this.judicialCourtRepository.count();

        assertThatThrownBy(() -> this.judicialCourtService.create(creation))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(missingTypeId.toString());
        assertThat(this.judicialCourtRepository.count()).isEqualTo(before);
    }

    @Test
    @Transactional
    void testCreateLawyerNotFound() {
        UserSnapshot existing = this.user("0000", "600000100", "cliente0");
        UUID missingLawyerId = UUID.randomUUID();
        CreationJudicialCourt creation = this.creation(TYPE_0.getId(), List.of(existing.getId(), missingLawyerId));
        when(this.userFinder.findByIds(Set.of(existing.getId(), missingLawyerId)))
                .thenReturn(List.of(existing));
        long before = this.judicialCourtRepository.count();

        assertThatThrownBy(() -> this.judicialCourtService.create(creation))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(missingLawyerId.toString())
                .hasMessageNotContaining(existing.getId().toString());
        assertThat(this.judicialCourtRepository.count()).isEqualTo(before);
    }

    @Test
    @Transactional
    void testFindLawyerCourtRanking() {
        UserSnapshot first = this.user("0000", "600000100", "cliente0");
        UserSnapshot second = this.user("0001", "600000101", "cliente1");
        UserSnapshot third = this.user("0002", "600000102", "cliente2");
        when(this.userFinder.findByIds(anySet())).thenAnswer(invocation -> {
            Set<UUID> ids = invocation.getArgument(0);
            return Stream.of(first, second, third)
                    .filter(user -> ids.contains(user.getId()))
                    .toList();
        });

        this.judicialCourtService.create(this.creation(TYPE_0.getId(), List.of(first.getId(), second.getId())));
        this.judicialCourtService.create(this.creation(TYPE_0.getId(), List.of(first.getId())));
        this.judicialCourtService.create(this.creation(TYPE_0.getId(), List.of(second.getId(), third.getId())));
        this.judicialCourtService.create(this.creation(TYPE_0.getId(), List.of(second.getId())));

        List<LawyerCourtRankingReport> reports = this.judicialCourtService.findLawyerCourtRanking();

        assertThat(reports)
                .extracting(report -> report.getLawyer().getId())
                .containsExactly(second.getId(), first.getId(), third.getId());
        assertThat(reports)
                .extracting(LawyerCourtRankingReport::getTotalJudicialCourts)
                .containsExactly(3L, 2L, 1L);
    }

    @Test
    @Transactional
    void testFindLawyerCourtRankingEmpty() {
        assertThat(this.judicialCourtService.findLawyerCourtRanking()).isEmpty();
    }

    private CreationJudicialCourt creation(UUID typeId, List<UUID> lawyerIds) {
        return CreationJudicialCourt.builder()
                .name("Tribunal de prueba " + UUID.randomUUID())
                .number(13)
                .address("Calle de la Justicia 1")
                .city("Madrid")
                .postalCode("28001")
                .phone("912345678")
                .email("tribunal@test.com")
                .typeId(typeId)
                .lawyerIds(lawyerIds)
                .build();
    }

    private UserSnapshot user(String idSuffix, String mobile, String firstName) {
        return UserSnapshot.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff" + idSuffix))
                .mobile(mobile)
                .firstName(firstName)
                .build();
    }
}
