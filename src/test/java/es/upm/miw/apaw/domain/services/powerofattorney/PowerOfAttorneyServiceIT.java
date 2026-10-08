package es.upm.miw.apaw.domain.services.powerofattorney;

import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.powerofattorney.CreationPowerOfAttorney;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorney;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyParty;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyStatus;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyType;
import es.upm.miw.apaw.domain.ports.out.powerofattorney.PowerOfAttorneyGateway;
import es.upm.miw.apaw.domain.ports.out.powerofattorney.PowerOfAttorneyPartyGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PowerOfAttorneyServiceIT {

    private static final UUID PRINCIPAL_ID = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000");
    private static final UUID ATTORNEY_ID = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001");

    @Mock
    private PowerOfAttorneyGateway powerOfAttorneyGateway;

    @Mock
    private PowerOfAttorneyPartyGateway powerOfAttorneyPartyGateway;

    @InjectMocks
    private PowerOfAttorneyService powerOfAttorneyService;

    @Test
    void testCreateUsesDefaultTypeAndStatus() {
        CreationPowerOfAttorney creation = this.creationWithDefaults();
        PowerOfAttorneyParty principal = this.party(PRINCIPAL_ID);
        PowerOfAttorneyParty attorney = this.party(ATTORNEY_ID);
        when(this.powerOfAttorneyPartyGateway.read(PRINCIPAL_ID)).thenReturn(Optional.of(principal));
        when(this.powerOfAttorneyPartyGateway.read(ATTORNEY_ID)).thenReturn(Optional.of(attorney));
        when(this.powerOfAttorneyGateway.create(any(PowerOfAttorney.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        PowerOfAttorney created = this.powerOfAttorneyService.create(creation);

        assertThat(created.getId()).isNotNull();
        assertThat(created.getProtocolNumber()).isEqualTo(creation.getProtocolNumber());
        assertThat(created.getGrantDate()).isEqualTo(creation.getGrantDate());
        assertThat(created.getExpirationDate()).isEqualTo(creation.getExpirationDate());
        assertThat(created.getScope()).isEqualTo(creation.getScope());
        assertThat(created.getLimitations()).isEqualTo(creation.getLimitations());
        assertThat(created.getNotaryName()).isEqualTo(creation.getNotaryName());
        assertThat(created.getNotaryOffice()).isEqualTo(creation.getNotaryOffice());
        assertThat(created.getNotes()).isEqualTo(creation.getNotes());
        assertThat(created.getPrincipal()).isSameAs(principal);
        assertThat(created.getAttorney()).isSameAs(attorney);
        assertThat(created.getType()).isEqualTo(PowerOfAttorneyType.GENERAL);
        assertThat(created.getStatus()).isEqualTo(PowerOfAttorneyStatus.ACTIVE);
    }

    @Test
    void testCreateKeepsExplicitTypeAndStatus() {
        CreationPowerOfAttorney creation = this.creation(
                PowerOfAttorneyType.LITIGATION, PowerOfAttorneyStatus.REVOKED);
        PowerOfAttorneyParty principal = this.party(PRINCIPAL_ID);
        PowerOfAttorneyParty attorney = this.party(ATTORNEY_ID);
        when(this.powerOfAttorneyPartyGateway.read(PRINCIPAL_ID)).thenReturn(Optional.of(principal));
        when(this.powerOfAttorneyPartyGateway.read(ATTORNEY_ID)).thenReturn(Optional.of(attorney));
        when(this.powerOfAttorneyGateway.create(any(PowerOfAttorney.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        PowerOfAttorney created = this.powerOfAttorneyService.create(creation);

        assertThat(created.getType()).isEqualTo(PowerOfAttorneyType.LITIGATION);
        assertThat(created.getStatus()).isEqualTo(PowerOfAttorneyStatus.REVOKED);
        verify(this.powerOfAttorneyGateway).create(created);
    }

    @Test
    void testCreateDuplicatedProtocolNumber() {
        CreationPowerOfAttorney creation = this.creationWithDefaults();
        when(this.powerOfAttorneyGateway.existsByProtocolNumber(creation.getProtocolNumber())).thenReturn(true);

        assertThatThrownBy(() -> this.powerOfAttorneyService.create(creation))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining(creation.getProtocolNumber());

        verify(this.powerOfAttorneyGateway, never()).create(any(PowerOfAttorney.class));
        verifyNoInteractions(this.powerOfAttorneyPartyGateway);
    }

    @Test
    void testCreateWithGrantDateAfterExpirationDate() {
        CreationPowerOfAttorney creation = this.creation(
                LocalDate.of(2027, 10, 1), LocalDate.of(2026, 10, 1));

        assertThatThrownBy(() -> this.powerOfAttorneyService.create(creation))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("grand date cannot be later than expiration date")
                .hasMessageContaining(creation.getProtocolNumber());

        verify(this.powerOfAttorneyGateway).existsByProtocolNumber(creation.getProtocolNumber());
        verify(this.powerOfAttorneyGateway, never()).create(any(PowerOfAttorney.class));
        verifyNoInteractions(this.powerOfAttorneyPartyGateway);
    }

    @Test
    void testCreateWithSamePrincipalAndAttorney() {
        CreationPowerOfAttorney creation = this.creation(
                PRINCIPAL_ID, PRINCIPAL_ID);

        assertThatThrownBy(() -> this.powerOfAttorneyService.create(creation))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Principal and attorney must be different");

        verify(this.powerOfAttorneyGateway).existsByProtocolNumber(creation.getProtocolNumber());
        verifyNoInteractions(this.powerOfAttorneyPartyGateway);
    }

    @Test
    void testCreatePrincipalNotFound() {
        CreationPowerOfAttorney creation = this.creationWithDefaults();
        when(this.powerOfAttorneyPartyGateway.read(PRINCIPAL_ID)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> this.powerOfAttorneyService.create(creation))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(PRINCIPAL_ID.toString());

        verify(this.powerOfAttorneyPartyGateway).read(PRINCIPAL_ID);
        verify(this.powerOfAttorneyPartyGateway, never()).read(ATTORNEY_ID);
        verify(this.powerOfAttorneyGateway, never()).create(any(PowerOfAttorney.class));
    }

    @Test
    void testCreateAttorneyNotFound() {
        CreationPowerOfAttorney creation = this.creationWithDefaults();
        PowerOfAttorneyParty principal = this.party(PRINCIPAL_ID);
        when(this.powerOfAttorneyPartyGateway.read(PRINCIPAL_ID)).thenReturn(Optional.of(principal));
        when(this.powerOfAttorneyPartyGateway.read(ATTORNEY_ID)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> this.powerOfAttorneyService.create(creation))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(ATTORNEY_ID.toString());

        verify(this.powerOfAttorneyPartyGateway).read(PRINCIPAL_ID);
        verify(this.powerOfAttorneyPartyGateway).read(ATTORNEY_ID);
        verify(this.powerOfAttorneyGateway, never()).create(any(PowerOfAttorney.class));
    }

    @Test
    void testCreateSendsComposedPowerOfAttorneyToGateway() {
        CreationPowerOfAttorney creation = this.creationWithDefaults();
        PowerOfAttorneyParty principal = this.party(PRINCIPAL_ID);
        PowerOfAttorneyParty attorney = this.party(ATTORNEY_ID);
        when(this.powerOfAttorneyPartyGateway.read(PRINCIPAL_ID)).thenReturn(Optional.of(principal));
        when(this.powerOfAttorneyPartyGateway.read(ATTORNEY_ID)).thenReturn(Optional.of(attorney));
        when(this.powerOfAttorneyGateway.create(any(PowerOfAttorney.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        this.powerOfAttorneyService.create(creation);

        ArgumentCaptor<PowerOfAttorney> captor = ArgumentCaptor.forClass(PowerOfAttorney.class);
        verify(this.powerOfAttorneyGateway).create(captor.capture());
        PowerOfAttorney stored = captor.getValue();

        assertThat(stored.getPrincipal()).isSameAs(principal);
        assertThat(stored.getAttorney()).isSameAs(attorney);
        assertThat(stored.getProtocolNumber()).isEqualTo(creation.getProtocolNumber());
    }

    private CreationPowerOfAttorney creationWithDefaults() {
        return this.creation(null, null, null, null, PRINCIPAL_ID, ATTORNEY_ID);
    }

    private CreationPowerOfAttorney creation(PowerOfAttorneyType type, PowerOfAttorneyStatus status) {
        return this.creation(LocalDate.of(2026, 10, 1), LocalDate.of(2027, 10, 1), type, status,
                PRINCIPAL_ID, ATTORNEY_ID);
    }

    private CreationPowerOfAttorney creation(UUID principalId, UUID attorneyId) {
        return this.creation(LocalDate.of(2026, 10, 1), LocalDate.of(2027, 10, 1),
                null, null, principalId, attorneyId);
    }

    private CreationPowerOfAttorney creation(
            LocalDate grantDate, LocalDate expirationDate) {
        return this.creation(grantDate, expirationDate, null, null, PRINCIPAL_ID, ATTORNEY_ID);
    }

    private CreationPowerOfAttorney creation(
            LocalDate grantDate,
            LocalDate expirationDate,
            PowerOfAttorneyType type,
            PowerOfAttorneyStatus status,
            UUID principalId,
            UUID attorneyId) {
        return CreationPowerOfAttorney.builder()
                .protocolNumber("PROTOCOL-" + UUID.randomUUID())
                .grantDate(grantDate)
                .expirationDate(expirationDate)
                .scope("General legal representation")
                .limitations("No property sale")
                .notaryName("Notary Name")
                .notaryOffice("Notary Office")
                .notes("Test notes")
                .type(type)
                .status(status)
                .principalId(principalId)
                .attorneyId(attorneyId)
                .build();
    }

    private PowerOfAttorneyParty party(UUID id) {
        return PowerOfAttorneyParty.builder()
                .id(id)
                .age(35)
                .fullMentalCapacity(true)
                .representationCompany(false)
                .build();
    }
}
