package es.upm.miw.apaw.domain.services.immigrationissues;

import es.upm.miw.apaw.adapters.out.immigrationissues.postgres.ImmigrationIssueRepository;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.immigrationissues.LawBasis;
import es.upm.miw.apaw.domain.model.immigrationissues.LawBasisUpdate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.ImmigrationIssuesSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class LawBasisServiceIT {

    @Autowired
    private LawBasisService lawBasisService;
    @Autowired
    private ImmigrationIssueRepository immigrationIssueRepository;

    @Test
    void testReadSeeder() {
        assertThat(this.lawBasisService.read(ID_0)).usingRecursiveComparison().isEqualTo(LAW_BASIS_0);
    }

    @Test
    void testReadInactiveSeeder() {
        assertThat(this.lawBasisService.read(ID_2)).usingRecursiveComparison().isEqualTo(LAW_BASIS_2);
        assertThat(this.lawBasisService.read(ID_2).getActive()).isFalse();
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.lawBasisService.read(id))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    @Test
    void testFindAllAllowsAdditionalLawBases() {
        LawBasis extra = this.createLawBasis();
        List<LawBasis> lawBases = this.lawBasisService.findAll();
        assertThat(lawBases).extracting(LawBasis::getId)
                .contains(ID_0, ID_1, ID_2, ID_3, ID_4, ID_5, extra.getId());
        assertThat(lawBases).extracting(LawBasis::getId)
                .containsSubsequence(ID_0, ID_1, ID_2, ID_3, ID_4, ID_5);
        assertThat(this.lawBasisService.findAll()).extracting(LawBasis::getId)
                .containsExactlyElementsOf(lawBases.stream().map(LawBasis::getId).toList());
    }

    @Test
    void testFindAllIsSortedByLawCode() {
        assertThat(this.lawBasisService.findAll()).extracting(LawBasis::getLawCode).isSorted();
    }

    @Test
    void testCreate() {
        LawBasis lawBasis = this.createLawBasis();
        assertThat(lawBasis.getId()).isNotNull();
        assertThat(lawBasis.getActive()).isTrue();
        assertThat(this.lawBasisService.read(lawBasis.getId()))
                .usingRecursiveComparison().isEqualTo(lawBasis);
    }

    @Test
    void testCreateKeepsProvidedActive() {
        LawBasis lawBasis = this.newLawBasis();
        lawBasis.setActive(false);
        assertThat(this.lawBasisService.create(lawBasis).getActive()).isFalse();
    }

    @Test
    void testCreateDuplicateLawCode() {
        LawBasis lawBasis = this.newLawBasis();
        lawBasis.setLawCode(LAW_BASIS_0.getLawCode());
        assertThatThrownBy(() -> this.lawBasisService.create(lawBasis))
                .isInstanceOf(ConflictException.class).hasMessageContaining(LAW_BASIS_0.getLawCode());
    }

    @Test
    void testUpdateReplacesMutableFields() {
        LawBasis original = this.lawBasisService.read(this.createLawBasis().getId());
        String newLawCode = "ES-LB-" + UUID.randomUUID();
        LawBasis replacement = LawBasis.builder()
                .lawCode(newLawCode).lawName("Updated law").articleNumber(42)
                .publishedOn(LocalDate.of(2026, 1, 20)).active(false).build();
        this.lawBasisService.update(original.getId(), replacement);
        LawBasis updated = this.lawBasisService.read(original.getId());
        assertThat(updated.getId()).isEqualTo(original.getId());
        assertThat(updated.getLawCode()).isEqualTo(newLawCode);
        assertThat(updated.getLawName()).isEqualTo("Updated law");
        assertThat(updated.getArticleNumber()).isEqualTo(42);
        assertThat(updated.getPublishedOn()).isEqualTo(LocalDate.of(2026, 1, 20));
        assertThat(updated.getActive()).isFalse();
    }

    @Test
    void testUpdateSameLawCode() {
        LawBasis lawBasis = this.createLawBasis();
        lawBasis.setLawName("Updated law name");
        this.lawBasisService.update(lawBasis.getId(), lawBasis);
        assertThat(this.lawBasisService.read(lawBasis.getId()).getLawName())
                .isEqualTo("Updated law name");
    }

    @Test
    void testUpdateNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.lawBasisService.update(id, this.newLawBasis()))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    @Test
    void testUpdateDuplicateLawCodeLeavesLawBasisUnchanged() {
        LawBasis lawBasis = this.createLawBasis();
        LawBasis replacement = LawBasis.builder()
                .lawCode(LAW_BASIS_0.getLawCode()).lawName("Replacement").build();
        assertThatThrownBy(() -> this.lawBasisService.update(lawBasis.getId(), replacement))
                .isInstanceOf(ConflictException.class).hasMessageContaining(LAW_BASIS_0.getLawCode());
        assertThat(this.lawBasisService.read(lawBasis.getId()))
                .usingRecursiveComparison().isEqualTo(lawBasis);
    }

    @Test
    void testPatchOnlyAppliesPresentFields() {
        LawBasis original = this.createLawBasis();
        this.lawBasisService.patch(original.getId(), new LawBasisUpdate(null, "Patched name", null, null, false));
        LawBasis patched = this.lawBasisService.read(original.getId());
        assertThat(patched.getLawName()).isEqualTo("Patched name");
        assertThat(patched.getActive()).isFalse();
        assertThat(patched).usingRecursiveComparison()
                .ignoringFields("lawName", "active").isEqualTo(original);
    }

    @Test
    void testPatchEmptyChangesNothing() {
        LawBasis original = this.createLawBasis();
        this.lawBasisService.patch(original.getId(),
                new LawBasisUpdate(null, null, null, null, null));
        assertThat(this.lawBasisService.read(original.getId()))
                .usingRecursiveComparison().isEqualTo(original);
    }

    @Test
    void testPatchDuplicateLawCode() {
        LawBasis original = this.createLawBasis();
        assertThatThrownBy(() -> this.lawBasisService.patch(original.getId(),
                new LawBasisUpdate(LAW_BASIS_0.getLawCode(), null, null, null, null)))
                .isInstanceOf(ConflictException.class).hasMessageContaining(LAW_BASIS_0.getLawCode());
        assertThat(this.lawBasisService.read(original.getId()))
                .usingRecursiveComparison().isEqualTo(original);
    }

    @Test
    void testPatchSameLawCode() {
        LawBasis original = this.createLawBasis();
        this.lawBasisService.patch(original.getId(),
                new LawBasisUpdate(original.getLawCode(), "Renamed", null, null, null));
        LawBasis patched = this.lawBasisService.read(original.getId());
        assertThat(patched.getLawName()).isEqualTo("Renamed");
        assertThat(patched.getLawCode()).isEqualTo(original.getLawCode());
    }

    @Test
    void testPatchNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.lawBasisService.patch(id, new LawBasisUpdate(null, "x", null, null, null)))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    @Test
    void testDelete() {
        LawBasis lawBasis = this.createLawBasis();
        this.lawBasisService.delete(lawBasis.getId());
        assertThatThrownBy(() -> this.lawBasisService.read(lawBasis.getId()))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void testDeleteNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.lawBasisService.delete(id))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    @Test
    void testDeleteReferencedLawBasis() {
        assertThatThrownBy(() -> this.lawBasisService.delete(ID_0))
                .isInstanceOf(ConflictException.class).hasMessageContaining(ID_0.toString());
        assertThat(this.lawBasisService.read(ID_0)).usingRecursiveComparison().isEqualTo(LAW_BASIS_0);
        assertThat(this.immigrationIssueRepository.existsByLawBases_Id(ID_0)).isTrue();
    }

    

    private LawBasis newLawBasis() {
        return LawBasis.builder()
                .lawCode("ES-LB-" + UUID.randomUUID())
                .lawName("IT law basis")
                .articleNumber(7)
                .publishedOn(LocalDate.of(2025, 1, 15))
                .build();
    }

    private LawBasis createLawBasis() {
        return this.lawBasisService.create(this.newLawBasis());
    }
}