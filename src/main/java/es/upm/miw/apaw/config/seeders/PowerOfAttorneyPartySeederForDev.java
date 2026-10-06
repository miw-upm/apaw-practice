package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.powerofattorney.postgres.PowerOfAttorneyPartyEntity;
import es.upm.miw.apaw.adapters.out.powerofattorney.postgres.PowerOfAttorneyPartyRepository;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyParty;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

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

    private final PowerOfAttorneyPartyRepository powerOfAttorneyPartyRepository;

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
    }

    private void seedPowerOfAttorneyParties() {
        List<PowerOfAttorneyPartyEntity> parties = List.of(
                        PARTY_0, PARTY_1, PARTY_2, PARTY_3, PARTY_4, PARTY_5).stream()
                .filter(party -> !this.powerOfAttorneyPartyRepository.existsById(party.getId()))
                .map(PowerOfAttorneyPartyEntity::new)
                .toList();
        this.powerOfAttorneyPartyRepository.saveAll(parties);
        log.warn("        ------- power of attorney parties: {} added", parties.size());
    }
}
