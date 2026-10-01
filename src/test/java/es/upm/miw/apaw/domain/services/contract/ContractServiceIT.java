
package es.upm.miw.apaw.domain.services.contract;

import es.upm.miw.apaw.adapters.out.contract.postgres.ContractEntity;
import es.upm.miw.apaw.adapters.out.contract.postgres.ContractRepository;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.contract.Clause;
import es.upm.miw.apaw.domain.model.contract.Contract;
import es.upm.miw.apaw.domain.model.contract.ContractType;
import es.upm.miw.apaw.domain.model.contract.CreationContract;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.ContractSeederForDev.ID_0;
import static es.upm.miw.apaw.config.seeders.ContractSeederForDev.ID_1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class ContractServiceIT {

    @Autowired
    private ContractService contractService;

    @Autowired
    private ContractRepository contractRepository;

    @MockitoBean
    private UserFinder userFinder;

    @Test
    @Transactional
    void testCreate() {
        UserSnapshot user = UserSnapshot.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                .mobile("600000100")
                .firstName("cliente0")
                .build();

        CreationContract creation = CreationContract.builder()
                .title("Contract " + UUID.randomUUID())
                .type(ContractType.OTHER)
                .startDate(LocalDate.of(2026, 1, 1))
                .amount(BigDecimal.TEN)
                .clauseIds(List.of(ID_0, ID_1))
                .userId(user.getId())
                .build();

        when(this.userFinder.read(user.getId())).thenReturn(user);

        Contract contract = this.contractService.create(creation);

        assertThat(contract.getId()).isNotNull();
        assertThat(contract.getCreatedAt()).isNotNull();
        assertThat(contract.getAutomaticRenewal()).isFalse();
        assertThat(contract.getClauses())
                .extracting(Clause::getId)
                .containsExactly(ID_0, ID_1);
        assertThat(contract.getUserSnapshot().getId()).isEqualTo(user.getId());

        ContractEntity entity = this.contractRepository
                .findById(contract.getId())
                .orElseThrow();

        assertThat(entity.getTitle()).isEqualTo(creation.getTitle());
        assertThat(entity.getClauses())
                .extracting(clause -> clause.getId())
                .containsExactly(ID_0, ID_1);
        assertThat(entity.getUserId()).isEqualTo(user.getId());
    }

    @Test
    @Transactional
    void testCreateNotFoundClause() {
        UUID missingId = UUID.randomUUID();

        UserSnapshot user = UserSnapshot.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                .mobile("600000100")
                .firstName("cliente0")
                .build();

        CreationContract creation = CreationContract.builder()
                .title("Contract with missing clause")
                .startDate(LocalDate.of(2026, 1, 1))
                .clauseIds(List.of(missingId))
                .userId(user.getId())
                .build();

        when(this.userFinder.read(user.getId())).thenReturn(user);

        assertThatThrownBy(() -> this.contractService.create(creation))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(missingId.toString());
    }
}