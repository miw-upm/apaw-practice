package es.upm.miw.apaw.domain.services.copyright;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.copyright.CreativeWork;
import es.upm.miw.apaw.domain.model.copyright.CreativeWorkCreation;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("test")
class CreativeWorkServiceIT {

    @Autowired
    private CreativeWorkService creativeWorkService;

    @MockitoBean
    private UserFinder userFinder;

    @Test
    void testCreateCreativeWork() {
        UUID authorId = UUID.randomUUID();
        UserSnapshot mockUser = UserSnapshot.builder()
                .id(authorId)
                .mobile("666555444")
                .firstName("Test Author")
                .build();

        given(this.userFinder.read(any())).willReturn(mockUser);

        CreativeWorkCreation creation = CreativeWorkCreation.builder()
                .registrationCode("RW-NEW-001")
                .title("New Test Work")
                .estimatedValuation(new BigDecimal("100.0"))
                .authorPenName("New Pen Name")
                .authorId(authorId)
                .build();

        CreativeWork created = this.creativeWorkService.create(creation);

        assertThat(created).isNotNull();
        assertThat(created.getId()).isNotNull();
        assertThat(created.getRegistrationDate()).isNotNull();
        assertThat(created.getClaims()).isEmpty(); 

        assertThat(created.getTitle()).isEqualTo("New Test Work");
        assertThat(created.getAuthor()).isEqualTo(mockUser);

        verify(this.userFinder, times(1)).read(authorId);
    }

    @Test
    void testCreateCreativeWorkConflict() {
        CreativeWorkCreation creation = CreativeWorkCreation.builder()
                .registrationCode("RW-001")
                .title("Duplicate Work")
                .estimatedValuation(new BigDecimal("10.0"))
                .authorPenName("Duplicate Pen Name")
                .authorId(UUID.randomUUID())
                .build();

        assertThrows(ConflictException.class, () -> this.creativeWorkService.create(creation));
    }

    @Test
    void testGenerateClaimSummaries() {
        // En el Seeder:
        // WORK_0 (RW-001) tiene CLAIM_0 (5000.00) y CLAIM_1 (1000.00) -> SUM = 6000.00, COUNT = 2, autor "cliente0"
        // WORK_1 (RW-002) tiene CLAIM_2 (12500.00) -> SUM = 12500.00, COUNT = 1, autor "cliente1"
        // El de mayor suma es RW-002.

        // Mock del UserFinder para las llamadas por lotes
        java.util.List<UserSnapshot> mockUsers = java.util.List.of(
                UserSnapshot.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000")).firstName("cliente0").build(),
                UserSnapshot.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001")).firstName("cliente1").build()
        );
        given(this.userFinder.findByIds(any())).willReturn(mockUsers);

        java.util.List<es.upm.miw.apaw.domain.model.copyright.CreativeWorkClaimSummary> summaries = 
                this.creativeWorkService.generateClaimSummaries();

        assertThat(summaries).hasSize(2);
        
        // Verifica la hidratación del autor
        assertThat(summaries).allSatisfy(summary -> assertThat(summary.getAuthor()).isNotNull());

        // Verifica que estén ordenados por SUM de mayor a menor (esto fallará por culpa del ASC)
        assertThat(summaries.get(0).getTotalRequestedCompensation())
                .isGreaterThan(summaries.get(1).getTotalRequestedCompensation());
        
        // El primero debería ser RW-002 (suma 12500.00)
        assertThat(summaries.get(0).getRegistrationCode()).isEqualTo("RW-002");
        assertThat(summaries.get(0).getClaimCount()).isEqualTo(1L);
        assertThat(summaries.get(0).getTotalRequestedCompensation()).isEqualByComparingTo(new BigDecimal("12500.00"));

        // El segundo debería ser RW-001 (suma 6000.00)
        assertThat(summaries.get(1).getRegistrationCode()).isEqualTo("RW-001");
        assertThat(summaries.get(1).getClaimCount()).isEqualTo(2L);
        assertThat(summaries.get(1).getTotalRequestedCompensation()).isEqualByComparingTo(new BigDecimal("6000.00"));
    }
}
