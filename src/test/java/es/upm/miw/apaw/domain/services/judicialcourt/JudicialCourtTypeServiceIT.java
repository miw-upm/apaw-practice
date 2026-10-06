package es.upm.miw.apaw.domain.services.judicialcourt;

import es.upm.miw.apaw.adapters.out.judicialcourt.postgres.JudicialCourtEntity;
import es.upm.miw.apaw.adapters.out.judicialcourt.postgres.JudicialCourtRepository;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourt;
import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourtStatus;
import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourtType;
import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourtTypeUpdate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.JudicialCourtTypeSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class JudicialCourtTypeServiceIT {
    @Autowired
    private JudicialCourtTypeService judicialCourtTypeService;
    @Autowired
    private JudicialCourtRepository judicialCourtRepository;

    @Test
    void testReadSeeder() {
        assertThat(this.judicialCourtTypeService.read(ID_0)).usingRecursiveComparison().isEqualTo(TYPE_0);
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.judicialCourtTypeService.read(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(id.toString());
    }

    @Test
    void testFindAllAllowsAdditionalTypes() {
        JudicialCourtType extra = this.createType();
        List<JudicialCourtType> judicialCourtTypes = this.judicialCourtTypeService.findAll();

        assertThat(judicialCourtTypes).extracting(JudicialCourtType::getId)
                .contains(ID_0, ID_1, ID_2, ID_3, ID_4, extra.getId());
        assertThat(judicialCourtTypes).extracting(JudicialCourtType::getName)
                .contains(TYPE_0.getName(), TYPE_1.getName(), TYPE_2.getName(), TYPE_3.getName(), TYPE_4.getName(), extra.getName());
        assertThat(this.judicialCourtTypeService.findAll()).extracting(JudicialCourtType::getId)
                .containsExactlyElementsOf(judicialCourtTypes.stream().map(JudicialCourtType::getId).toList());
    }

    @Test
    void testCreate() {
        JudicialCourtType judicialCourtType = this.createType();
        JudicialCourtType stored = this.judicialCourtTypeService.read(judicialCourtType.getId());

        assertThat(stored).usingRecursiveComparison().isEqualTo(judicialCourtType);
        assertThat(stored.getActive()).isTrue();
    }

    @Test
    void testCreateDuplicateName() {
        assertThatThrownBy(() -> this.judicialCourtTypeService.create(JudicialCourtType.builder()
                .name(TYPE_0.getName())
                .description("A duplicate name")
                .code(this.uniqueCode())
                .jurisdiction("Madrid")
                .build()))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining(TYPE_0.getName());
    }

    @Test
    void testCreateDuplicateCode() {
        assertThatThrownBy(() -> this.judicialCourtTypeService.create(JudicialCourtType.builder()
                .name("Tribunal duplicado")
                .description("A duplicate code")
                .code(TYPE_0.getCode())
                .jurisdiction("Madrid")
                .build()))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining(TYPE_0.getCode());
    }

    @Test
    void testUpdateReplacesMutableFields() {
        JudicialCourtType original = this.createType();
        JudicialCourtType replacement = JudicialCourtType.builder()
                .name("Updated Tribunal")
                .description("Updated description")
                .code(this.uniqueCode())
                .jurisdiction("Sevilla")
                .active(false)
                .build();

        this.judicialCourtTypeService.update(original.getId(), replacement);
        JudicialCourtType updated = this.judicialCourtTypeService.read(original.getId());

        assertThat(updated.getId()).isEqualTo(original.getId());
        assertThat(updated.getName()).isEqualTo(replacement.getName());
        assertThat(updated.getDescription()).isEqualTo(replacement.getDescription());
        assertThat(updated.getCode()).isEqualTo(replacement.getCode());
        assertThat(updated.getJurisdiction()).isEqualTo(replacement.getJurisdiction());
        assertThat(updated.getActive()).isFalse();
    }

    @Test
    void testUpdateSameNameAndCodeAllowed() {
        JudicialCourtType original = this.createType();
        original.setDescription("Updated description");
        original.setJurisdiction("Valencia");
        original.setActive(false);

        this.judicialCourtTypeService.update(original.getId(), original);

        JudicialCourtType updated = this.judicialCourtTypeService.read(original.getId());
        assertThat(updated.getDescription()).isEqualTo("Updated description");
        assertThat(updated.getJurisdiction()).isEqualTo("Valencia");
        assertThat(updated.getActive()).isFalse();
    }

    @Test
    void testUpdateNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.judicialCourtTypeService.update(id, TYPE_0))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(id.toString());
    }

    @Test
    void testUpdateDuplicateNameLeavesTypeUnchanged() {
        JudicialCourtType original = this.createType();
        assertThatThrownBy(() -> this.judicialCourtTypeService.update(original.getId(), JudicialCourtType.builder()
                .name(TYPE_0.getName())
                .description("Should not be applied")
                .code(this.uniqueCode())
                .jurisdiction("Madrid")
                .active(true)
                .build()))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining(TYPE_0.getName());
        assertThat(this.judicialCourtTypeService.read(original.getId()).getName()).isEqualTo(original.getName());
    }

    @Test
    void testPatchUpdatesSelectedFields() {
        JudicialCourtType original = this.createType();
        JudicialCourtTypeUpdate update = new JudicialCourtTypeUpdate(
                "Patched Tribunal",
                "Patched description",
                null,
                "Barcelona",
                false);

        JudicialCourtType patched = this.judicialCourtTypeService.patch(original.getId(), update);

        assertThat(patched.getId()).isEqualTo(original.getId());
        assertThat(patched.getName()).isEqualTo(update.name());
        assertThat(patched.getDescription()).isEqualTo(update.description());
        assertThat(patched.getCode()).isEqualTo(original.getCode());
        assertThat(patched.getJurisdiction()).isEqualTo(update.jurisdiction());
        assertThat(patched.getActive()).isFalse();
    }

    @Test
    void testPatchNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.judicialCourtTypeService.patch(id,
                new JudicialCourtTypeUpdate("Missing name", "desc", "CODE", "Madrid", true)))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(id.toString());
    }

    @Test
    void testPatchDuplicateCodeChangesNothing() {
        JudicialCourtType original = this.createType();
        assertThatThrownBy(() -> this.judicialCourtTypeService.patch(original.getId(),
                new JudicialCourtTypeUpdate("Other name", "desc", TYPE_0.getCode(), "Madrid", true)))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining(TYPE_0.getCode());
        assertThat(this.judicialCourtTypeService.read(original.getId()).getCode()).isEqualTo(original.getCode());
    }

    @Test
    void testDelete() {
        JudicialCourtType judicialCourtType = this.createType();
        this.judicialCourtTypeService.delete(judicialCourtType.getId());
        assertThatThrownBy(() -> this.judicialCourtTypeService.read(judicialCourtType.getId()))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void testDeleteMissingType() {
        UUID id = UUID.randomUUID();
        this.judicialCourtTypeService.delete(id);
        assertThatThrownBy(() -> this.judicialCourtTypeService.read(id))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void testDeleteReferencedType() {
        JudicialCourtType judicialCourtType = this.createType();
        this.judicialCourtRepository.saveAndFlush(new JudicialCourtEntity(JudicialCourt.builder()
                .id(UUID.randomUUID())
                .name("Tribunal referenciado " + UUID.randomUUID())
                .address("Casa de la Justicia")
                .city("Madrid")
                .createdAt(LocalDateTime.now())
                .type(judicialCourtType)
                .status(JudicialCourtStatus.ACTIVE)
                .build()));

        assertThatThrownBy(() -> this.judicialCourtTypeService.delete(judicialCourtType.getId()))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining(judicialCourtType.getId().toString());
        assertThat(this.judicialCourtTypeService.read(judicialCourtType.getId()).getId()).isEqualTo(judicialCourtType.getId());
    }

    private JudicialCourtType createType() {
        return this.judicialCourtTypeService.create(JudicialCourtType.builder()
                .name("Servicio tribunal " + UUID.randomUUID())
                .description("Generated for IT")
                .code(this.uniqueCode())
                .jurisdiction("Madrid")
                .build());
    }

    private String uniqueCode() {
        return "JCT" + UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase();
    }
}
