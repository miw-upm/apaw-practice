package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.expertdirectoryservices.postgres.LegalExpertProfileEntity;
import es.upm.miw.apaw.adapters.out.expertdirectoryservices.postgres.LegalExpertProfileRepository;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.LegalExpertProfile;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Log4j2
@Component
@Profile({"dev", "test"})
@Order(1)
@RequiredArgsConstructor
public class ExpertDirectoryServicesSeederForDev implements ApplicationRunner {
    private static final String PROFILE_PREFIX = "cccccccc-dddd-eeee-ffff-aaaaaaaa";
    private static final String USER_PREFIX = "cccccccc-dddd-eeee-ffff-bbbbbbbb";
    public static final UUID PROFILE_ID_0 = UUID.fromString(PROFILE_PREFIX + "0000");
    public static final LegalExpertProfile PROFILE_0 = LegalExpertProfile.builder()
            .id(PROFILE_ID_0)
            .taxIdCode("EXP-TAX-0000")
            .professionalLicense("EXP-LIC-0000")
            .specialtyArea("Labour law")
            .yearsOfExperience(12)
            .requiresPrepayment(false)
            .partnershipDate(LocalDate.of(2020, 1, 15))
            .userSnapshot(user("0000", "600000200", "experto0"))
            .build();
    public static final UUID PROFILE_ID_1 = UUID.fromString(PROFILE_PREFIX + "0001");
    public static final LegalExpertProfile PROFILE_1 = LegalExpertProfile.builder()
            .id(PROFILE_ID_1)
            .taxIdCode("EXP-TAX-0001")
            .professionalLicense("EXP-LIC-0001")
            .specialtyArea("Tax law")
            .yearsOfExperience(8)
            .requiresPrepayment(true)
            .partnershipDate(LocalDate.of(2021, 3, 1))
            .userSnapshot(user("0001", "600000201", "experto1"))
            .build();
    public static final UUID PROFILE_ID_2 = UUID.fromString(PROFILE_PREFIX + "0002");
    public static final LegalExpertProfile PROFILE_2 = LegalExpertProfile.builder()
            .id(PROFILE_ID_2)
            .taxIdCode("EXP-TAX-0002")
            .specialtyArea("Real estate law")
            .yearsOfExperience(3)
            .requiresPrepayment(false)
            .partnershipDate(LocalDate.of(2023, 9, 20))
            .userSnapshot(user("0002", "600000202", "experto2"))
            .build();

    private final LegalExpertProfileRepository legalExpertProfileRepository;

    private static UserSnapshot user(String idSuffix, String mobile, String firstName) {
        return UserSnapshot.builder()
                .id(UUID.fromString(USER_PREFIX + idSuffix))
                .mobile(mobile)
                .firstName(firstName)
                .build();
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        log.warn("------- Initial Load from JAVA -----------");
        this.seedLegalExpertProfiles();
    }

    private void seedLegalExpertProfiles() {
        List<LegalExpertProfileEntity> profiles = List.of(PROFILE_0, PROFILE_1, PROFILE_2).stream()
                .filter(profile -> !this.legalExpertProfileRepository.existsById(profile.getId()))
                .map(LegalExpertProfileEntity::new)
                .toList();
        this.legalExpertProfileRepository.saveAll(profiles);
        log.warn("        ------- legal expert profiles: {} added", profiles.size());
    }
}
