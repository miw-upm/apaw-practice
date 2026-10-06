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

    public static final String PREFIX = "eeeeeeee-ffff-aaaa-bbbb-ccccdddd";
    public static final UUID ID_0 = UUID.fromString(PREFIX + "0000");
    public static final UUID ID_1 = UUID.fromString(PREFIX + "0001");
    public static final UUID ID_2 = UUID.fromString(PREFIX + "0002");
    public static final UUID ID_3 = UUID.fromString(PREFIX + "0003");
    public static final UUID ID_4 = UUID.fromString(PREFIX + "0004");
    public static final UUID ID_5 = UUID.fromString(PREFIX + "0005");

    public static final PowerOfAttorneyParty PARTY_0 = PowerOfAttorneyParty.builder()
            .id(ID_0)
            .age(35)
            .fullMentalCapacity(true)
            .companyName(null)
            .representationCompany(false)
            .userSnapshot(user("000000000001", "Ana García"))
            .build();

    public static final PowerOfAttorneyParty PARTY_1 = PowerOfAttorneyParty.builder()
            .id(ID_1)
            .age(48)
            .fullMentalCapacity(true)
            .companyName("García Legal S.L.")
            .representationCompany(true)
            .userSnapshot(user("000000000002", "Carlos García"))
            .build();

    public static final PowerOfAttorneyParty PARTY_2 = PowerOfAttorneyParty.builder()
            .id(ID_2)
            .age(62)
            .fullMentalCapacity(false)
            .companyName(null)
            .representationCompany(false)
            .userSnapshot(user("000000000001", "Ana García"))
            .build();

    public static final PowerOfAttorneyParty PARTY_3 = PowerOfAttorneyParty.builder()
            .id(ID_3)
            .age(41)
            .fullMentalCapacity(true)
            .companyName("García Legal S.L.")
            .representationCompany(true)
            .userSnapshot(user("000000000002", "Carlos García"))
            .build();
    public static final PowerOfAttorneyParty PARTY_4 = PowerOfAttorneyParty.builder()
            .id(ID_4)
            .age(15)
            .fullMentalCapacity(true)
            .companyName("García Legal S.L.")
            .representationCompany(true)
            .userSnapshot(user("000000000001", "Ana García"))
            .build();
    public static final PowerOfAttorneyParty PARTY_5 = PowerOfAttorneyParty.builder()
            .id(ID_5)
            .age(20)
            .fullMentalCapacity(true)
            .companyName("García Legal S.L.")
            .representationCompany(false)
            .userSnapshot(user("000000000002", "Carlos García"))
            .build();

    private final PowerOfAttorneyPartyRepository powerOfAttorneyPartyRepository;

    private static UserSnapshot user(String id, String firstName) {
        return UserSnapshot.builder()
                .id(UUID.fromString("00000000-0000-0000-0000-" + id))
                .identity("USER-" + id)
                .firstName(firstName)
                .build();
    }

    @Override
    public void run(ApplicationArguments args) {
        log.warn("------- Initial Load PowerOfAttorneyParties -----------");
        this.seedPowerOfAttorneyParties();
    }

    private void seedPowerOfAttorneyParties() {
        List<PowerOfAttorneyPartyEntity> parties = List.of(
                        PARTY_0, PARTY_1, PARTY_2, PARTY_3).stream()
                .filter(party -> !this.powerOfAttorneyPartyRepository.existsById(party.getId()))
                .map(PowerOfAttorneyPartyEntity::new)
                .toList();
        this.powerOfAttorneyPartyRepository.saveAll(parties);
        log.warn("        ------- power of attorney parties: {} added", parties.size());
    }
}
