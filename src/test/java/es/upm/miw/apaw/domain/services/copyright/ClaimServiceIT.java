package es.upm.miw.apaw.domain.services.copyright;

import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.copyright.Claim;
import es.upm.miw.apaw.domain.model.copyright.ClaimCreation;
import es.upm.miw.apaw.domain.model.copyright.ClaimTaskStatusUpdate;
import es.upm.miw.apaw.domain.model.copyright.TaskStatus;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.CopyrightSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class ClaimServiceIT {

    @Autowired
    private ClaimService claimService;

    @MockitoBean
    private UserFinder userFinder;

    @Test
    void testReadSeeder() {
        Claim claim = this.claimService.read(CLAIM_ID_0);
        assertThat(claim.getNumber()).isEqualTo(CLAIM_0.getNumber());
        assertThat(claim.getTaskStatus()).isEqualTo(TaskStatus.CURRENT);
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.claimService.read(id))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    @Test
    void testFindAll() {
        List<Claim> claims = this.claimService.findAll();
        assertThat(claims).extracting(Claim::getNumber)
                .contains(CLAIM_0.getNumber(), CLAIM_1.getNumber(), CLAIM_2.getNumber());
    }

    @Test
    @Transactional
    void testCreate() {
        UserSnapshot user = UserSnapshot.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0010"))
                .mobile("600000110")
                .firstName("clientenuevo")
                .build();
        
        ClaimCreation creation = ClaimCreation.builder()
                .number("CLM-NEW-" + UUID.randomUUID().toString().substring(0, 5))
                .requestedCompensation(BigDecimal.TEN)
                .urgent(true)
                .creativeWorkId(WORK_ID_0)
                .defendantId(user.getId())
                .build();
        
        when(this.userFinder.read(user.getId())).thenReturn(user);

        Claim created = this.claimService.create(creation);

        assertThat(created.getId()).isNotNull();
        assertThat(created.getNumber()).isEqualTo(creation.getNumber());
        assertThat(created.getRequestedCompensation()).isEqualTo(creation.getRequestedCompensation());
        assertThat(created.getUrgent()).isTrue();
        assertThat(created.getDefendant()).isEqualTo(user);
    }

    @Test
    void testCreateDuplicateNumber() {
        ClaimCreation creation = ClaimCreation.builder()
                .number(CLAIM_0.getNumber())
                .requestedCompensation(BigDecimal.TEN)
                .creativeWorkId(WORK_ID_0)
                .defendantId(UUID.randomUUID())
                .build();
        
        assertThatThrownBy(() -> this.claimService.create(creation))
                .isInstanceOf(ConflictException.class).hasMessageContaining(CLAIM_0.getNumber());
    }

    @Test
    @Transactional
    void testUpdate() {
        UserSnapshot user = UserSnapshot.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0011"))
                .mobile("600000111")
                .firstName("clientemodificado")
                .build();

        Claim updatedClaim = Claim.builder()
                .number("CLM-UPD-" + UUID.randomUUID().toString().substring(0, 5))
                .requestedCompensation(new BigDecimal("99.99"))
                .urgent(false)
                .taskStatus(TaskStatus.WITHDRAWN)
                .defendant(user)
                .build();
        
        when(this.userFinder.read(user.getId())).thenReturn(user);

        Claim result = this.claimService.update(CLAIM_ID_1, updatedClaim);
        
        assertThat(result.getNumber()).isEqualTo(updatedClaim.getNumber());
        assertThat(result.getRequestedCompensation()).isEqualTo(updatedClaim.getRequestedCompensation());
        assertThat(result.getUrgent()).isFalse();
        assertThat(result.getTaskStatus()).isEqualTo(TaskStatus.WITHDRAWN);
        assertThat(result.getDefendant()).isEqualTo(user);
    }

    @Test
    void testUpdateDuplicateNumber() {
        Claim updatedClaim = Claim.builder()
                .number(CLAIM_0.getNumber()) // existing number from another claim
                .defendant(UserSnapshot.builder().id(UUID.randomUUID()).build())
                .build();

        assertThatThrownBy(() -> this.claimService.update(CLAIM_ID_1, updatedClaim))
                .isInstanceOf(ConflictException.class).hasMessageContaining(CLAIM_0.getNumber());
    }

    @Test
    @Transactional
    void testDelete() {
        this.claimService.delete(CLAIM_ID_2);
        assertThatThrownBy(() -> this.claimService.read(CLAIM_ID_2)).isInstanceOf(NotFoundException.class);
    }

    @Test
    @Transactional
    void testUpdateTaskStatuses() {
        this.claimService.updateTaskStatuses(List.of(
                new ClaimTaskStatusUpdate(CLAIM_ID_0, TaskStatus.WITHDRAWN),
                new ClaimTaskStatusUpdate(CLAIM_ID_1, TaskStatus.DEPRECATED)));
        
        assertThat(this.claimService.read(CLAIM_ID_0).getTaskStatus()).isEqualTo(TaskStatus.WITHDRAWN);
        assertThat(this.claimService.read(CLAIM_ID_1).getTaskStatus()).isEqualTo(TaskStatus.DEPRECATED);
    }

    @Test
    void testUpdateTaskStatusesDuplicateId() {
        assertThatThrownBy(() -> this.claimService.updateTaskStatuses(List.of(
                new ClaimTaskStatusUpdate(CLAIM_ID_0, TaskStatus.WITHDRAWN),
                new ClaimTaskStatusUpdate(CLAIM_ID_0, TaskStatus.DEPRECATED))))
                .isInstanceOf(BadRequestException.class).hasMessageContaining(CLAIM_ID_0.toString());
    }
}
