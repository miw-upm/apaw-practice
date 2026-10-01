package es.upm.miw.apaw.domain.services.contract;

import es.upm.miw.apaw.adapters.out.contract.postgres.ClauseEntity;
import es.upm.miw.apaw.adapters.out.contract.postgres.ContractEntity;
import es.upm.miw.apaw.adapters.out.contract.postgres.ContractRepository;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.contract.Clause;
import es.upm.miw.apaw.domain.model.contract.ClauseType;
import es.upm.miw.apaw.domain.model.contract.ClauseUpdate;
import es.upm.miw.apaw.domain.model.contract.ContractType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.ContractSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class ClauseServiceIT {

    @Autowired
    private ClauseService clauseService;

    @Autowired
    private ContractRepository contractRepository;

    @Test
    void testReadSeeder() {
        assertThat(this.clauseService.read(ID_0))
                .usingRecursiveComparison()
                .isEqualTo(CLAUSE_0);
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();

        assertThatThrownBy(() -> this.clauseService.read(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(id.toString());
    }

    @Test
    void testFindAllAllowsAdditionalClauses() {
        Clause extra = this.createClause();

        List<Clause> clauses = this.clauseService.findAll();

        assertThat(clauses)
                .extracting(Clause::getId)
                .contains(ID_0, ID_1, ID_2, ID_3, ID_4, ID_5, ID_6, extra.getId());

        assertThat(clauses)
                .extracting(Clause::getTitle)
                .containsSubsequence(
                        CLAUSE_3.getTitle(),
                        CLAUSE_0.getTitle(),
                        CLAUSE_6.getTitle(),
                        CLAUSE_5.getTitle(),
                        CLAUSE_1.getTitle(),
                        CLAUSE_4.getTitle(),
                        CLAUSE_2.getTitle()
                );

        assertThat(this.clauseService.findAll())
                .extracting(Clause::getId)
                .containsExactlyElementsOf(
                        clauses.stream().map(Clause::getId).toList()
                );
    }

    @Test
    void testCreate() {
        Clause clause = this.createClause();
        Clause stored = this.clauseService.read(clause.getId());

        assertThat(stored)
                .usingRecursiveComparison()
                .isEqualTo(clause);

        assertThat(stored.getId()).isNotNull();
        assertThat(stored.getVersion()).isEqualTo(1);
    }

    @Test
    void testCreateAllowsDuplicateTitle() {
        Clause clause = Clause.builder()
                .title(CLAUSE_0.getTitle())
                .type(ClauseType.CONFIDENTIALITY)
                .content("Contenido de prueba")
                .effectiveFrom(LocalDate.of(2026, 1, 1))
                .version(1)
                .build();

        Clause created = this.clauseService.create(clause);

        assertThat(created.getId()).isNotNull();
        assertThat(created.getTitle()).isEqualTo(CLAUSE_0.getTitle());

        assertThat(this.clauseService.read(CLAUSE_0.getId()).getTitle())
                .isEqualTo(CLAUSE_0.getTitle());

        assertThat(this.clauseService.read(created.getId()).getId())
                .isEqualTo(created.getId());
    }

    @Test
    void testCreateDefaultVersion() {
        Clause clause = this.clauseService.create(
                Clause.builder()
                        .title("Clause without version")
                        .content("Test content")
                        .effectiveFrom(LocalDate.of(2026, 1, 1))
                        .version(null)
                        .build()
        );

        assertThat(clause.getVersion()).isEqualTo(1);
    }

    @Test
    void testUpdateAttributes() {
        Clause clause = this.createClause();

        clause.setType(ClauseType.CONFIDENTIALITY);
        clause.setNotes("Updated notes");

        this.clauseService.update(clause.getId(), clause);

        Clause updated = this.clauseService.read(clause.getId());

        assertThat(updated.getType()).isEqualTo(ClauseType.CONFIDENTIALITY);
        assertThat(updated.getNotes()).isEqualTo("Updated notes");
        assertThat(updated.getContent()).isEqualTo("Original content");
        assertThat(updated.getId()).isEqualTo(clause.getId());
    }

    @Test
    void testUpdateReplacesMutableFields() {
        Clause original = this.createClause();

        Clause replacement = Clause.builder()
                .title("Updated " + UUID.randomUUID())
                .type(ClauseType.CONFIDENTIALITY)
                .content("Updated content")
                .effectiveFrom(LocalDate.of(2026, 2, 1))
                .effectiveUntil(LocalDate.of(2028, 2, 1))
                .version(2)
                .build();

        this.clauseService.update(original.getId(), replacement);

        Clause updated = this.clauseService.read(original.getId());

        assertThat(updated.getTitle()).isEqualTo(replacement.getTitle());
        assertThat(updated.getType()).isEqualTo(replacement.getType());
        assertThat(updated.getContent()).isEqualTo(replacement.getContent());
        assertThat(updated.getEffectiveFrom()).isEqualTo(replacement.getEffectiveFrom());
        assertThat(updated.getEffectiveUntil()).isEqualTo(replacement.getEffectiveUntil());
        assertThat(updated.getNotes()).isNull();
        assertThat(updated.getVersion()).isEqualTo(replacement.getVersion());
        assertThat(updated.getId()).isEqualTo(original.getId());
    }

    @Test
    void testUpdateNotFound() {
        UUID id = UUID.randomUUID();

        assertThatThrownBy(() -> this.clauseService.update(id, CLAUSE_0))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(id.toString());
    }

    @Test
    void testUpdateAllowsDuplicateTitle() {
        Clause original = this.createClause();

        Clause replacement = Clause.builder()
                .title(CLAUSE_0.getTitle())
                .type(ClauseType.OTHER)
                .content("Updated content")
                .effectiveFrom(LocalDate.of(2026, 2, 1))
                .version(2)
                .build();

        this.clauseService.update(original.getId(), replacement);

        Clause updated = this.clauseService.read(original.getId());

        assertThat(updated.getTitle()).isEqualTo(CLAUSE_0.getTitle());
        assertThat(updated.getId()).isEqualTo(original.getId());

        assertThat(this.clauseService.read(ID_0)).usingRecursiveComparison()
                .isEqualTo(CLAUSE_0);
    }

    @Test
    void testDelete() {
        Clause clause = this.createClause();

        this.clauseService.delete(clause.getId());

        assertThatThrownBy(() -> this.clauseService.read(clause.getId()))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void testDeleteNotFound() {
        UUID id = UUID.randomUUID();

        assertThatThrownBy(() -> this.clauseService.delete(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(id.toString());
    }

    @Test
    void testDeleteReferencedClause() {
        Clause clause = this.createClause();

        ContractEntity contract = ContractEntity.builder()
                .id(UUID.randomUUID())
                .title("Contract " + UUID.randomUUID())
                .type(ContractType.OTHER)
                .startDate(LocalDate.of(2026, 1, 1))
                .automaticRenewal(false)
                .createdAt(LocalDateTime.now())
                .userId(UUID.randomUUID())
                .clauses(List.of(new ClauseEntity(clause)))
                .build();

        this.contractRepository.saveAndFlush(contract);

        assertThatThrownBy(() -> this.clauseService.delete(clause.getId()))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining(clause.getId().toString());

        assertThat(this.clauseService.read(clause.getId()).getId())
                .isEqualTo(clause.getId());

        assertThat(this.contractRepository.existsByClauses_Id(clause.getId()))
                .isTrue();
    }

    @Test
    void testPatch() {
        Clause clause = this.createClause();

        ClauseUpdate patch = new ClauseUpdate(
                ClauseType.CONFIDENTIALITY,
                "Updated notes",
                LocalDate.of(2028, 1, 1)
        );

        this.clauseService.patch(clause.getId(), patch);

        Clause updated = this.clauseService.read(clause.getId());

        assertThat(updated.getType()).isEqualTo(ClauseType.CONFIDENTIALITY);
        assertThat(updated.getNotes()).isEqualTo("Updated notes");
        assertThat(updated.getEffectiveUntil()).isEqualTo(LocalDate.of(2028, 1, 1));
    }

    @Test
    void testPatchNotFoundChangesNothing() {
        Clause clause = this.createClause();
        UUID missingId = UUID.randomUUID();

        ClauseUpdate patch = new ClauseUpdate(
                ClauseType.CONFIDENTIALITY,
                "Updated notes",
                LocalDate.of(2028, 1, 1)
        );

        assertThatThrownBy(() -> this.clauseService.patch(missingId, patch))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(missingId.toString());

        assertThat(this.clauseService.read(clause.getId()))
                .usingRecursiveComparison()
                .isEqualTo(clause);
    }

    @Test
    void testPatchNullFieldsRemainUnchanged() {
        Clause clause = this.createClause();

        ClauseUpdate patch = new ClauseUpdate(null, null, null);

        this.clauseService.patch(clause.getId(), patch);

        Clause updated = this.clauseService.read(clause.getId());

        assertThat(updated.getType()).isEqualTo(clause.getType());
        assertThat(updated.getNotes()).isEqualTo(clause.getNotes());
        assertThat(updated.getEffectiveUntil()).isEqualTo(clause.getEffectiveUntil());
    }


    private Clause createClause() {
        return this.clauseService.create(
                Clause.builder()
                        .title("IT Clause " + UUID.randomUUID())
                        .type(ClauseType.OTHER)
                        .content("Original content")
                        .effectiveFrom(LocalDate.of(2026, 1, 1))
                        .notes("Original notes")
                        .version(1)
                        .build()
        );
    }
}
