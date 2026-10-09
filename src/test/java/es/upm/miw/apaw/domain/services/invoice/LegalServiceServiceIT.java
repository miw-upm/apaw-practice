
package es.upm.miw.apaw.domain.services.invoice;

import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.invoice.LegalService;
import es.upm.miw.apaw.domain.model.invoice.LegalServiceUpdate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.LegalServiceSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class LegalServiceServiceIT {

    @Autowired
    private LegalServiceService legalServiceService;

    @Test
    void testReadSeeder() {
        assertThat(this.legalServiceService.read(ID_0))
                .usingRecursiveComparison()
                .isEqualTo(LEGAL_SERVICE_0);
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();

        assertThatThrownBy(() -> this.legalServiceService.read(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(id.toString());
    }

    @Test
    void testFindAll() {
        assertThat(this.legalServiceService.findAll())
                .extracting(LegalService::getId)
                .contains(ID_0, ID_1, ID_2, ID_3, ID_4, ID_5);
    }

    @Test
    void testCreate() {
        LegalService service = this.newLegalService(
                "Integration create " + UUID.randomUUID());

        LegalService created = this.legalServiceService.create(service);

        assertThat(created.getId()).isNotNull();
        assertThat(created.getName()).isEqualTo(service.getName());
        assertThat(created.getDescription()).isEqualTo(service.getDescription());
        assertThat(created.getFee()).isEqualByComparingTo(service.getFee());
        assertThat(created.getCategory()).isEqualTo(service.getCategory());
        assertThat(created.getLegalArea()).isEqualTo(service.getLegalArea());

        assertThat(this.legalServiceService.read(created.getId()))
                .usingRecursiveComparison()
                .isEqualTo(created);
    }

    @Test
    void testCreateDuplicateName() {
        LegalService service = this.newLegalService(LEGAL_SERVICE_0.getName());

        assertThatThrownBy(() -> this.legalServiceService.create(service))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining(service.getName());
    }

    @Test
    void testUpdate() {
        LegalService original = this.createLegalService();
        LegalService changes = this.newLegalService(
                "Integration update " + UUID.randomUUID());

        LegalService updated = this.legalServiceService.update(
                original.getId(), changes);

        assertThat(updated.getId()).isEqualTo(original.getId());
        assertThat(updated.getName()).isEqualTo(changes.getName());
        assertThat(updated.getDescription()).isEqualTo(changes.getDescription());
        assertThat(updated.getFee()).isEqualByComparingTo(changes.getFee());
        assertThat(updated.getRequiresAppointment())
                .isEqualTo(changes.getRequiresAppointment());
        assertThat(updated.getCategory()).isEqualTo(changes.getCategory());
        assertThat(updated.getLegalArea()).isEqualTo(changes.getLegalArea());
    }

    @Test
    void testUpdateNotFound() {
        UUID id = UUID.randomUUID();
        LegalService changes = this.newLegalService(
                "Integration missing " + UUID.randomUUID());

        assertThatThrownBy(() -> this.legalServiceService.update(id, changes))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(id.toString());
    }

    @Test
    void testUpdateDuplicateName() {
        LegalService original = this.createLegalService();
        LegalService changes = this.newLegalService(LEGAL_SERVICE_0.getName());

        assertThatThrownBy(() ->
                this.legalServiceService.update(original.getId(), changes))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining(changes.getName());
    }

    @Test
    void testUpdateWithSameName() {
        LegalService original = this.createLegalService();
        String originalName = original.getName();

        LegalService changes = this.newLegalService(originalName);
        changes.setDescription("Updated description");
        changes.setFee(new BigDecimal("88.00"));

        LegalService updated = this.legalServiceService.update(
                original.getId(), changes);

        assertThat(updated.getName()).isEqualTo(originalName);
        assertThat(updated.getDescription()).isEqualTo("Updated description");
        assertThat(updated.getFee()).isEqualByComparingTo("88.00");
    }

    @Test
    void testPatchFees() {
        LegalService service0 = this.createLegalService();
        LegalService service1 = this.createLegalService();
        BigDecimal fee0 = new BigDecimal("91.00");
        BigDecimal fee1 = new BigDecimal("215.00");

        this.legalServiceService.updateLegalServices(List.of(
                new LegalServiceUpdate(service0.getId(), fee0),
                new LegalServiceUpdate(service1.getId(), fee1)));

        assertThat(this.legalServiceService.read(service0.getId()).getFee())
                .isEqualByComparingTo(fee0);
        assertThat(this.legalServiceService.read(service1.getId()).getFee())
                .isEqualByComparingTo(fee1);
    }

    @Test
    void testPatchRepeatedIds() {
        LegalService service = this.createLegalService();
        UUID id = service.getId();

        assertThatThrownBy(() ->
                this.legalServiceService.updateLegalServices(List.of(
                        new LegalServiceUpdate(id, new BigDecimal("90.00")),
                        new LegalServiceUpdate(id, new BigDecimal("95.00")))))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining(id.toString());
    }

    @Test
    void testPatchNotFoundDoesNotUpdateExistingServices() {
        LegalService service = this.createLegalService();
        BigDecimal originalFee = service.getFee();

        assertThatThrownBy(() ->
                this.legalServiceService.updateLegalServices(List.of(
                        new LegalServiceUpdate(
                                service.getId(), new BigDecimal("999.00")),
                        new LegalServiceUpdate(
                                UUID.randomUUID(), new BigDecimal("1000.00")))))
                .isInstanceOf(NotFoundException.class);

        assertThat(this.legalServiceService.read(service.getId()).getFee())
                .isEqualByComparingTo(originalFee);
    }

    @Test
    void testDelete() {
        LegalService service = this.createLegalService();

        this.legalServiceService.delete(service.getId());

        assertThatThrownBy(() -> this.legalServiceService.read(service.getId()))
                .isInstanceOf(NotFoundException.class);
    }

    private LegalService createLegalService() {
        return this.legalServiceService.create(
                this.newLegalService("Integration test " + UUID.randomUUID()));
    }

    private LegalService newLegalService(String name) {
        LegalService service = new LegalService();
        service.setName(name);
        service.setDescription("Description for " + name);
        service.setFee(new BigDecimal("75.00"));
        service.setRequiresAppointment(Boolean.TRUE);
        service.setCategory(LEGAL_SERVICE_0.getCategory());
        service.setLegalArea(LEGAL_SERVICE_0.getLegalArea());
        return service;
    }
}