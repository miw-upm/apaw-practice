package es.upm.miw.apaw.domain.services.probate;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.probate.Heir;
import es.upm.miw.apaw.domain.model.probate.HeirStatus;
import es.upm.miw.apaw.domain.model.probate.HeirUpdate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class HeirServiceIT {
    @Autowired
    private HeirService heirService;

    private Heir newHeir() {
        return Heir.builder()
                .fullName("Service Heir " + UUID.randomUUID())
                .nationalId("NID-" + UUID.randomUUID())
                .birthDate(LocalDate.of(1990, 1, 1))
                .sharePercentage(new BigDecimal("30.00"))
                .build();
    }

    @Test
    void testCreate() {
        Heir heir = this.heirService.create(this.newHeir());
        assertThat(heir.getId()).isNotNull();
        assertThat(heir.getHeirStatus()).isEqualTo(HeirStatus.PENDING);
    }

    @Test
    void testCreateDuplicateNationalId() {
        Heir heir = this.heirService.create(this.newHeir());
        Heir duplicate = Heir.builder()
                .fullName("Duplicate")
                .nationalId(heir.getNationalId())
                .birthDate(LocalDate.of(1991, 2, 2))
                .sharePercentage(new BigDecimal("10.00"))
                .build();
        assertThatThrownBy(() -> this.heirService.create(duplicate))
                .isInstanceOf(ConflictException.class);
    }

    @Test
    void testRead() {
        Heir heir = this.heirService.create(this.newHeir());
        assertThat(this.heirService.read(heir.getId()).getNationalId()).isEqualTo(heir.getNationalId());
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.heirService.read(id))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void testFindAllNotEmpty() {
        assertThat(this.heirService.findAll()).isNotEmpty();
    }

    @Test
    void testUpdate() {
        Heir heir = this.heirService.create(this.newHeir());
        Heir updated = this.heirService.update(heir.getId(), Heir.builder()
                .fullName("Updated Name")
                .nationalId(heir.getNationalId())
                .birthDate(heir.getBirthDate())
                .sharePercentage(new BigDecimal("60.00"))
                .heirStatus(HeirStatus.ACCEPTED)
                .build());
        assertThat(updated.getFullName()).isEqualTo("Updated Name");
        assertThat(updated.getSharePercentage()).isEqualByComparingTo("60.00");
    }

    @Test
    void testPatch() {
        Heir heir = this.heirService.create(this.newHeir());
        Heir patched = this.heirService.patch(heir.getId(),
                new HeirUpdate(null, null, null, null, HeirStatus.NOTIFIED, null));
        assertThat(patched.getHeirStatus()).isEqualTo(HeirStatus.NOTIFIED);
        assertThat(patched.getFullName()).isEqualTo(heir.getFullName());
    }

    @Test
    void testDelete() {
        Heir heir = this.heirService.create(this.newHeir());
        this.heirService.delete(heir.getId());
        assertThatThrownBy(() -> this.heirService.read(heir.getId()))
                .isInstanceOf(NotFoundException.class);
    }
}
