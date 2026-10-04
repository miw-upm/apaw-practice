package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.credentials.postgres.VerificationEntity;
import es.upm.miw.apaw.adapters.out.credentials.postgres.VerificationRepository;
import es.upm.miw.apaw.domain.model.credentials.Verification;
import es.upm.miw.apaw.domain.model.credentials.VerificationStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Log4j2
@Component
@Profile({"dev", "test"})
@Order(1)
@RequiredArgsConstructor
public class VerificationSeederForDev implements ApplicationRunner {

    public static final String PREFIX = "dddddddd-eeee-ffff-aaaa-bbbbcccc";

    public static final UUID ID_0 = UUID.fromString(PREFIX + "0000");
    public static final Verification VERIFICATION_0 = Verification.builder()
            .id(ID_0)
            .createdAt(LocalDateTime.of(2025, 1, 10, 9, 0))
            .method("DOCUMENT_REVIEW")
            .name("Initial documentation review")
            .notes("Documentation is pending verification")
            .verificationStatus(VerificationStatus.PENDING)
            .build();

    public static final UUID ID_1 = UUID.fromString(PREFIX + "0001");
    public static final Verification VERIFICATION_1 = Verification.builder()
            .id(ID_1)
            .createdAt(LocalDateTime.of(2025, 2, 10, 10, 0))
            .verifiedAt(LocalDateTime.of(2025, 2, 11, 12, 30))
            .method("MANUAL")
            .name("Professional registry check")
            .notes("Registry data successfully verified")
            .score(new BigDecimal("95.00"))
            .verificationStatus(VerificationStatus.VERIFIED)
            .build();

    public static final UUID ID_2 = UUID.fromString(PREFIX + "0002");
    public static final Verification VERIFICATION_2 = Verification.builder()
            .id(ID_2)
            .createdAt(LocalDateTime.of(2025, 3, 10, 11, 0))
            .verifiedAt(LocalDateTime.of(2025, 3, 12, 14, 15))
            .method("AUTOMATIC")
            .name("Automatic validation")
            .notes("Required information could not be verified")
            .score(new BigDecimal("35.50"))
            .verificationStatus(VerificationStatus.REJECTED)
            .build();

    public static final UUID ID_3 = UUID.fromString(PREFIX + "0003");
    public static final Verification VERIFICATION_3 = Verification.builder()
            .id(ID_3)
            .createdAt(LocalDateTime.of(2025, 4, 10, 12, 0))
            .verifiedAt(LocalDateTime.of(2025, 4, 11, 9, 45))
            .method("DOCUMENT_REVIEW")
            .name("Certificate verification")
            .score(new BigDecimal("88.00"))
            .verificationStatus(VerificationStatus.VERIFIED)
            .build();

    public static final UUID ID_4 = UUID.fromString(PREFIX + "0004");
    public static final Verification VERIFICATION_4 = Verification.builder()
            .id(ID_4)
            .createdAt(LocalDateTime.of(2025, 5, 10, 9, 30))
            .method("MANUAL")
            .notes("Waiting for additional documentation")
            .verificationStatus(VerificationStatus.PENDING)
            .build();

    private final VerificationRepository verificationRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        this.seed();
    }

    private void seed() {
        log.warn("------- Initial Load from JAVA -----------");

        List<VerificationEntity> verifications = List.of(
                        VERIFICATION_0,
                        VERIFICATION_1,
                        VERIFICATION_2,
                        VERIFICATION_3,
                        VERIFICATION_4)
                .stream()
                .filter(verification -> !this.verificationRepository.existsById(verification.getId()))
                .map(VerificationEntity::new)
                .toList();

        this.verificationRepository.saveAll(verifications);
        log.warn("        ------- verifications: {} added", verifications.size());
    }
}