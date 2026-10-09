package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.copyright.postgres.CreativeWorkAdapter;
import es.upm.miw.apaw.adapters.out.powerofattorney.postgres.PowerOfAttorneyEntity;
import es.upm.miw.apaw.adapters.out.powerofattorney.postgres.PowerOfAttorneyPartyEntity;
import es.upm.miw.apaw.adapters.out.powerofattorney.postgres.PowerOfAttorneyPartyRepository;
import es.upm.miw.apaw.adapters.out.powerofattorney.postgres.PowerOfAttorneyRepository;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorney;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyParty;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyStatus;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Log4j2
@Component
@Profile({"dev", "test"})
@Order(5)
@RequiredArgsConstructor
public class PowerOfAttorneyPartySeederForDev implements ApplicationRunner {

    public static final String PREFIX = "aaaaaaaa-bbbb-cccc-dddd-eeeeffff";
    public static final UUID ID_0 = UUID.fromString(PREFIX + "000a");
    public static final UUID ID_1 = UUID.fromString(PREFIX + "000b");
    public static final UUID ID_2 = UUID.fromString(PREFIX + "000c");
    public static final UUID ID_3 = UUID.fromString(PREFIX + "000d");
    public static final UUID ID_4 = UUID.fromString(PREFIX + "000e");
    public static final UUID ID_5 = UUID.fromString(PREFIX + "000f");
    public static final UUID ID_6 = UUID.fromString(PREFIX + "0010");

    public static final UUID POWER_OF_ATTORNEY_ID_0 = UUID.fromString(PREFIX + "0010");
    public static final UUID POWER_OF_ATTORNEY_ID_1 = UUID.fromString(PREFIX + "0011");
    public static final UUID POWER_OF_ATTORNEY_ID_2 = UUID.fromString(PREFIX + "0012");
    public static final UUID POWER_OF_ATTORNEY_ID_3 = UUID.fromString(PREFIX + "0013");
    public static final UUID POWER_OF_ATTORNEY_ID_4 = UUID.fromString(PREFIX + "0014");
    public static final UUID POWER_OF_ATTORNEY_ID_5 = UUID.fromString(PREFIX + "0015");
    public static final UUID POWER_OF_ATTORNEY_ID_6 = UUID.fromString(PREFIX + "0016");
    public static final UUID POWER_OF_ATTORNEY_ID_7 = UUID.fromString(PREFIX + "0017");
    public static final UUID POWER_OF_ATTORNEY_ID_8 = UUID.fromString(PREFIX + "0018");
    public static final UUID POWER_OF_ATTORNEY_ID_9 = UUID.fromString(PREFIX + "0019");
    public static final UUID POWER_OF_ATTORNEY_ID_10 = UUID.fromString(PREFIX + "0020");

    public static final PowerOfAttorneyParty PARTY_0 = PowerOfAttorneyParty.builder()
            .id(ID_0)
            .age(35)
            .fullMentalCapacity(true)
            .companyName(null)
            .representationCompany(false)
            .userSnapshot(user("0000", "cliente0","00000000T"))
            .build();

    public static final PowerOfAttorneyParty PARTY_1 = PowerOfAttorneyParty.builder()
            .id(ID_1)
            .age(48)
            .fullMentalCapacity(true)
            .companyName("García Legal S.L.")
            .representationCompany(true)
            .userSnapshot(user("0000", "cliente0","00000000T"))
            .build();

    public static final PowerOfAttorneyParty PARTY_2 = PowerOfAttorneyParty.builder()
            .id(ID_2)
            .age(62)
            .fullMentalCapacity(false)
            .companyName(null)
            .representationCompany(false)
            .userSnapshot(user("0001", "cliente1","00000001R"))
            .build();

    public static final PowerOfAttorneyParty PARTY_3 = PowerOfAttorneyParty.builder()
            .id(ID_3)
            .age(41)
            .fullMentalCapacity(true)
            .companyName("García Legal S.L.")
            .representationCompany(true)
            .userSnapshot(user("0006", "cliente6",""))
            .build();
    public static final PowerOfAttorneyParty PARTY_4 = PowerOfAttorneyParty.builder()
            .id(ID_4)
            .age(15)
            .fullMentalCapacity(true)
            .companyName("García Legal S.L.")
            .representationCompany(true)
            .userSnapshot(user("0002", "cliente2", "00000002W"))
            .build();
    public static final PowerOfAttorneyParty PARTY_5 = PowerOfAttorneyParty.builder()
            .id(ID_5)
            .age(14)
            .fullMentalCapacity(false)
            .companyName("García Legal S.L.")
            .representationCompany(false)
            .userSnapshot(user("0003", "cliente3", "00000003A"))
            .build();

    public static final PowerOfAttorneyParty PARTY_6 = PowerOfAttorneyParty.builder()
            .id(ID_6)
            .age(59)
            .fullMentalCapacity(true)
            .companyName("García Legal S.L.")
            .representationCompany(false)
            .userSnapshot(user("000e", "Manager1", "00000011B"))
            .build();

    public static final PowerOfAttorney POWER_OF_ATTORNEY_0 = PowerOfAttorney.builder()
            .id(POWER_OF_ATTORNEY_ID_0)
            .protocolNumber("DEV-POA-0000")
            .principal(PARTY_0)
            .attorney(PARTY_2)
            .status(PowerOfAttorneyStatus.ACTIVE)
            .notaryName("William Harrison")
            .notaryOffice("Harrison & Partners Notary Office")
            .type(PowerOfAttorneyType.GENERAL)
            .expirationDate(null)
            .grantDate(LocalDate.of(2026,01,12))
            .scope("General representation")
            .limitations("General limitations")
            .notes("Development seed")
            .build();

    public static final PowerOfAttorney POWER_OF_ATTORNEY_1 = PowerOfAttorney.builder()
            .id(POWER_OF_ATTORNEY_ID_1)
            .protocolNumber("DEV-POA-0001")
            .principal(PARTY_0)
            .attorney(PARTY_3)
            .status(PowerOfAttorneyStatus.ACTIVE)
            .type(PowerOfAttorneyType.SPECIAL)
            .notaryName("William Harrison")
            .notaryOffice("Harrison & Partners Notary Office")
            .grantDate(LocalDate.of(2026,01,12))
            .expirationDate(LocalDate.of(2027, 12, 31))
            .scope("Special representation")
            .limitations(null)
            .notes(null)
            .build();

    public static final PowerOfAttorney POWER_OF_ATTORNEY_2 = PowerOfAttorney.builder()
            .id(POWER_OF_ATTORNEY_ID_2)
            .protocolNumber("DEV-POA-0002")
            .principal(PARTY_3)
            .attorney(PARTY_0)
            .status(PowerOfAttorneyStatus.ACTIVE)
            .type(PowerOfAttorneyType.LITIGATION)
            .notaryName("William Harrison")
            .notaryOffice("Harrison & Partners Notary Office")
            .grantDate(LocalDate.of(2026,01,12))
            .expirationDate(LocalDate.of(2027, 12, 31))
            .scope("Litigation representation")
            .limitations("Litigation only")
            .notes("Development seed")
            .build();

    public static final PowerOfAttorney POWER_OF_ATTORNEY_3 = PowerOfAttorney.builder()
            .id(POWER_OF_ATTORNEY_ID_3)
            .protocolNumber("DEV-POA-0003")
            .principal(PARTY_3)
            .attorney(PARTY_1)
            .status(PowerOfAttorneyStatus.EXPIRED)
            .type(PowerOfAttorneyType.GENERAL)
            .notaryName("Emily Thompson")
            .notaryOffice("Thompson Legal Notary Services")
            .grantDate(LocalDate.of(2023,01,12))
            .expirationDate(LocalDate.of(2025, 12, 31))
            .scope("General representation")
            .limitations("Expired mandate")
            .notes("Development seed")
            .build();

    public static final PowerOfAttorney POWER_OF_ATTORNEY_4 = PowerOfAttorney.builder()
            .id(POWER_OF_ATTORNEY_ID_4)
            .protocolNumber("DEV-POA-0004")
            .principal(PARTY_5)
            .attorney(PARTY_3)
            .status(PowerOfAttorneyStatus.EXPIRED)
            .type(PowerOfAttorneyType.SPECIAL)
            .notaryName("Daniel Foster")
            .notaryOffice("Foster & Partners Notary Office")
            .grantDate(LocalDate.of(2024,01,12))
            .expirationDate(LocalDate.of(2025, 12, 31))
            .scope("Special representation")
            .limitations("Expired mandate")
            .notes("Development seed")
            .build();

    public static final PowerOfAttorney POWER_OF_ATTORNEY_5 = PowerOfAttorney.builder()
            .id(POWER_OF_ATTORNEY_ID_5)
            .protocolNumber("DEV-POA-0005")
            .principal(PARTY_4)
            .attorney(PARTY_0)
            .status(PowerOfAttorneyStatus.EXPIRED)
            .type(PowerOfAttorneyType.LITIGATION)
            .notaryName("Daniel Foster")
            .notaryOffice("Foster & Partners Notary Office")
            .grantDate(LocalDate.of(2024,01,12))
            .expirationDate(LocalDate.of(2025, 12, 31))
            .scope("Litigation representation")
            .limitations("Expired mandate")
            .notes("Development seed")
            .build();

    public static final PowerOfAttorney POWER_OF_ATTORNEY_6 = PowerOfAttorney.builder()
            .id(POWER_OF_ATTORNEY_ID_6)
            .protocolNumber("DEV-POA-0006")
            .principal(PARTY_0)
            .attorney(PARTY_3)
            .status(PowerOfAttorneyStatus.REVOKED)
            .type(PowerOfAttorneyType.GENERAL)
            .notaryName("Daniel Foster")
            .notaryOffice("Foster & Partners Notary Office")
            .grantDate(LocalDate.of(2026,01,12))
            .expirationDate(LocalDate.of(2027, 12, 31))
            .scope("General representation")
            .limitations("Revoked mandate")
            .notes("Development seed")
            .build();

    public static final PowerOfAttorney POWER_OF_ATTORNEY_7 = PowerOfAttorney.builder()
            .id(POWER_OF_ATTORNEY_ID_7)
            .protocolNumber("DEV-POA-0007")
            .principal(PARTY_3)
            .attorney(PARTY_0)
            .status(PowerOfAttorneyStatus.REVOKED)
            .type(PowerOfAttorneyType.SPECIAL)
            .notaryName("Daniel Foster")
            .notaryOffice("Foster & Partners Notary Office")
            .grantDate(LocalDate.of(2026,01,12))
            .expirationDate(LocalDate.of(2027, 12, 31))
            .scope("Special representation")
            .limitations("Revoked mandate")
            .notes("Development seed")
            .build();

    public static final PowerOfAttorney POWER_OF_ATTORNEY_8 = PowerOfAttorney.builder()
            .id(POWER_OF_ATTORNEY_ID_8)
            .protocolNumber("DEV-POA-0008")
            .principal(PARTY_5)
            .attorney(PARTY_3)
            .status(PowerOfAttorneyStatus.REVOKED)
            .type(PowerOfAttorneyType.LITIGATION)
            .notaryName("Sophia Mitchell")
            .notaryOffice("Mitchell Notarial Services")
            .grantDate(LocalDate.of(2026,01,12))
            .expirationDate(LocalDate.of(2027, 12, 31))
            .scope("Litigation representation")
            .limitations("Revoked mandate")
            .notes("Development seed")
            .build();

    public static final PowerOfAttorney POWER_OF_ATTORNEY_9 = PowerOfAttorney.builder()
            .id(POWER_OF_ATTORNEY_ID_9)
            .protocolNumber("DEV-POA-0009")
            .principal(PARTY_6)
            .attorney(PARTY_1)
            .status(PowerOfAttorneyStatus.REVOKED)
            .type(PowerOfAttorneyType.LITIGATION)
            .notaryName("Sophia Mitchell")
            .notaryOffice("Mitchell Notarial Services")
            .grantDate(LocalDate.of(2025,01,12))
            .expirationDate(LocalDate.of(2025, 12, 31))
            .scope("Litigation representation")
            .limitations("Revoked mandate")
            .notes("Development seed")
            .build();

    public static final PowerOfAttorney POWER_OF_ATTORNEY_10 = PowerOfAttorney.builder()
            .id(POWER_OF_ATTORNEY_ID_10)
            .protocolNumber("DEV-POA-0010")
            .principal(PARTY_6)
            .attorney(PARTY_0)
            .status(PowerOfAttorneyStatus.ACTIVE)
            .type(PowerOfAttorneyType.LITIGATION)
            .notaryName("William Harrison")
            .notaryOffice("Harrison & Partners Notary Office")
            .grantDate(LocalDate.of(2026,01,12))
            .expirationDate(LocalDate.of(2027, 12, 31))
            .scope("Litigation representation")
            .limitations("Litigation only")
            .notes("Development seed")
            .build();


    private final PowerOfAttorneyPartyRepository powerOfAttorneyPartyRepository;
    private final PowerOfAttorneyRepository powerOfAttorneyRepository;

    private static UserSnapshot user(String id, String firstName, String identity) {
        return UserSnapshot.builder()
                .id(UUID.fromString(PREFIX + id))
                .identity(identity)
                .firstName(firstName)
                .build();
    }

    @Override
    public void run(ApplicationArguments args) {
        log.warn("------- Initial Load from JAVA -----------");
        this.seedPowerOfAttorneyParties();
        this.seedPowerOfAttorneys();
    }

    private void seedPowerOfAttorneyParties() {
        List<PowerOfAttorneyPartyEntity> parties = List.of(
                        PARTY_0, PARTY_1, PARTY_2, PARTY_3, PARTY_4, PARTY_5, PARTY_6).stream()
                .filter(party -> !this.powerOfAttorneyPartyRepository.existsById(party.getId()))
                .map(PowerOfAttorneyPartyEntity::new)
                .toList();
        this.powerOfAttorneyPartyRepository.saveAll(parties);
        log.warn("        ------- power of attorney parties: {} added", parties.size());
    }

    private void seedPowerOfAttorneys() {
        List<PowerOfAttorneyEntity> powerOfAttorneys = List.of(
                POWER_OF_ATTORNEY_0, POWER_OF_ATTORNEY_1, POWER_OF_ATTORNEY_2, POWER_OF_ATTORNEY_3, POWER_OF_ATTORNEY_4, POWER_OF_ATTORNEY_5,
                POWER_OF_ATTORNEY_6, POWER_OF_ATTORNEY_7, POWER_OF_ATTORNEY_8, POWER_OF_ATTORNEY_9, POWER_OF_ATTORNEY_10).stream()
                .filter(powerOfAttorney -> !this.powerOfAttorneyRepository.existsById(powerOfAttorney.getId()))
                .map(powerOfAttorney -> new PowerOfAttorneyEntity(
                        powerOfAttorney,
                        powerOfAttorneyPartyRepository.findById(
                                powerOfAttorney.getPrincipal().getId()
                        ).orElseThrow(),
                        powerOfAttorneyPartyRepository.findById(
                                        powerOfAttorney.getAttorney().getId()
                                ).orElseThrow()))
                .toList();
        powerOfAttorneyRepository.saveAll(powerOfAttorneys);
        log.warn("        ------- power of attorney: {} added", powerOfAttorneys.size());

    }
}
