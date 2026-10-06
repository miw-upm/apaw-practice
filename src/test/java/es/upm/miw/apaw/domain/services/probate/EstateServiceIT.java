package es.upm.miw.apaw.domain.services.probate;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.probate.CreationEstate;
import es.upm.miw.apaw.domain.model.probate.Estate;
import es.upm.miw.apaw.domain.model.probate.EstateFindCriteria;
import es.upm.miw.apaw.domain.model.probate.Heir;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.ProbateSeederForDev.ESTATE_0;
import static es.upm.miw.apaw.config.seeders.ProbateSeederForDev.HEIR_ID_0;
import static es.upm.miw.apaw.config.seeders.ProbateSeederForDev.HEIR_ID_1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class EstateServiceIT {
    @Autowired
    private EstateService estateService;
    @MockitoBean
    private UserFinder userFinder;

    @Test
    void testCreate() {
        UUID userId = UUID.randomUUID();
        UserSnapshot user = UserSnapshot.builder()
                .id(userId).mobile("600000100").firstName("cliente0").build();
        when(this.userFinder.read(userId)).thenReturn(user);

        CreationEstate creation = CreationEstate.builder()
                .fileNumber("EXP-" + UUID.randomUUID())
                .deceasedName("Deceased Test")
                .netValue(new BigDecimal("100000.00"))
                .lastWill(true)
                .heirIds(List.of(HEIR_ID_0, HEIR_ID_1))
                .userId(userId)
                .build();

        Estate estate = this.estateService.create(creation);

        assertThat(estate.getId()).isNotNull();
        assertThat(estate.getFileNumber()).isEqualTo(creation.getFileNumber());
        assertThat(estate.getUserSnapshot()).isEqualTo(user);
        assertThat(estate.getHeirs()).extracting(Heir::getId).containsExactly(HEIR_ID_0, HEIR_ID_1);
    }

    @Test
    void testFindByFileNumber() {
        when(this.userFinder.read(any(UUID.class))).thenReturn(
                UserSnapshot.builder().id(UUID.randomUUID()).mobile("600000100").build());

        EstateFindCriteria criteria = EstateFindCriteria.builder()
                .fileNumber(ESTATE_0.getFileNumber())
                .build();

        List<Estate> estates = this.estateService.find(criteria);

        assertThat(estates).extracting(Estate::getFileNumber).contains(ESTATE_0.getFileNumber());
    }
}
