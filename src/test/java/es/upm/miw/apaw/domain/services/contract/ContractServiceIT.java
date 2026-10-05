
package es.upm.miw.apaw.domain.services.contract;

import es.upm.miw.apaw.adapters.out.contract.postgres.ContractEntity;
import es.upm.miw.apaw.adapters.out.contract.postgres.ContractRepository;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.contract.*;
import es.upm.miw.apaw.domain.ports.out.contract.ContractGateway;
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
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static es.upm.miw.apaw.config.seeders.ContractSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
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

    @Test
    void testFindByTitle() {
        UserSnapshot user = CONTRACT_0.getUserSnapshot();

        when(this.userFinder.findByIds(Set.of(user.getId())))
                .thenReturn(List.of(user));

        List<Contract> contracts = this.contractService.find(
                ContractFindCriteria.builder()
                        .title("Contrato de servicios de consultoría")
                        .build());

        assertThat(contracts)
                .extracting(Contract::getId)
                .containsExactlyInAnyOrder(
                        CONTRACT_0.getId(),
                        CONTRACT_10.getId());
    }

    @Test
    void testFindNoResults() {
        when(this.userFinder.findByIds(Set.of()))
                .thenReturn(List.of());

        List<Contract> contracts = this.contractService.find(
                ContractFindCriteria.builder()
                        .title("Título que no existe")
                        .build());

        assertThat(contracts).isEmpty();
    }

    @Test
    void testFindByClauseType() {
        List<UserSnapshot> users = List.of(
                CONTRACT_3.getUserSnapshot(),
                CONTRACT_8.getUserSnapshot(),
                CONTRACT_10.getUserSnapshot(),
                CONTRACT_13.getUserSnapshot(),
                CONTRACT_17.getUserSnapshot(),
                CONTRACT_19.getUserSnapshot()
        );

        Set<UUID> userIds = users.stream()
                .map(UserSnapshot::getId)
                .collect(Collectors.toSet());

        when(this.userFinder.findByIds(userIds))
                .thenReturn(users);

        List<Contract> contracts = this.contractService.find(
                ContractFindCriteria.builder()
                        .clauseType(ClauseType.PAYMENT)
                        .build());

        assertThat(contracts)
                .extracting(Contract::getId)
                .containsExactlyInAnyOrder(
                        CONTRACT_3.getId(),
                        CONTRACT_8.getId(),
                        CONTRACT_10.getId(),
                        CONTRACT_13.getId(),
                        CONTRACT_17.getId(),
                        CONTRACT_19.getId());
    }

    @Test
    void testFindByActive() {
        List<UserSnapshot> users = List.of(
                CONTRACT_6.getUserSnapshot(),
                CONTRACT_9.getUserSnapshot(),
                CONTRACT_10.getUserSnapshot(),
                CONTRACT_11.getUserSnapshot(),
                CONTRACT_12.getUserSnapshot(),
                CONTRACT_14.getUserSnapshot()
        );

        Set<UUID> userIds = users.stream()
                .map(UserSnapshot::getId)
                .collect(Collectors.toSet());

        when(this.userFinder.findByIds(userIds))
                .thenReturn(users);

        List<Contract> contracts = this.contractService.find(
                ContractFindCriteria.builder()
                        .active(false)
                        .build());

        assertThat(contracts)
                .extracting(Contract::getId)
                .containsExactlyInAnyOrder(
                        CONTRACT_6.getId(),
                        CONTRACT_9.getId(),
                        CONTRACT_10.getId(),
                        CONTRACT_11.getId(),
                        CONTRACT_12.getId(),
                        CONTRACT_14.getId(),
                        CONTRACT_17.getId(),
                        CONTRACT_19.getId());
    }

    @Test
    void testFindByCombinedCriteria() {
        UserSnapshot user = CONTRACT_10.getUserSnapshot();

        when(this.userFinder.findByIds(Set.of(user.getId())))
                .thenReturn(List.of(user));

        List<Contract> contracts = this.contractService.find(
                ContractFindCriteria.builder()
                        .title("Contrato de servicios de consultoría")
                        .clauseType(ClauseType.PAYMENT)
                        .build());

        assertThat(contracts)
                .extracting(Contract::getId)
                .containsExactly(CONTRACT_10.getId());
    }

    @Test
    void testFindByCombinedCriteriaNoResults() {
        List<Contract> contracts = this.contractService.find(
                ContractFindCriteria.builder()
                        .title("Contrato de servicios de consultoría")
                        .clauseType(ClauseType.TERMINATION)
                        .build());

        assertThat(contracts).isEmpty();
    }


    @Test
    void testFindByUserCity() {
        Set<UUID> sevillaUserIds = Stream.of(CONTRACT_2, CONTRACT_5, CONTRACT_11, CONTRACT_14, CONTRACT_17)
                .map(contract -> contract.getUserSnapshot().getId())
                .collect(Collectors.toSet());

        when(this.userFinder.findByIds(any())).thenAnswer(invocation -> {
            Set<UUID> requestedIds = invocation.getArgument(0);
            return requestedIds.stream()
                    .map(id -> UserSnapshot.builder()
                            .id(id)
                            .city(sevillaUserIds.contains(id) ? "Sevilla" : "Madrid")
                            .build())
                    .toList();
        });

        List<Contract> contracts = this.contractService.find(
                ContractFindCriteria.builder()
                        .userCity("Sevilla")
                        .build());

        assertThat(contracts)
                .extracting(Contract::getId)
                .containsExactlyInAnyOrder(
                        CONTRACT_2.getId(),
                        CONTRACT_5.getId(),
                        CONTRACT_11.getId(),
                        CONTRACT_14.getId(),
                        CONTRACT_17.getId());
    }
}