package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.invoice.postgres.LegalServiceEntity;
import es.upm.miw.apaw.adapters.out.invoice.postgres.LegalServiceRepository;
import es.upm.miw.apaw.domain.model.invoice.LegalService;
import es.upm.miw.apaw.domain.model.invoice.LegalArea;
import es.upm.miw.apaw.domain.model.invoice.ServiceCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Log4j2
@Component
@Profile({"dev", "test"})
@Order(1)
@RequiredArgsConstructor
public class LegalServiceSeederForDev implements ApplicationRunner {

    public static final String PREFIX = "a1234567-bbbb-cccc-dddd-eeeeffff";

    public static final UUID ID_0 = UUID.fromString(PREFIX + "0000");
    public static final LegalService LEGAL_SERVICE_0 = LegalService.builder()
            .id(ID_0)
            .name("Initial Legal Consultation")
            .description("First consultation to assess the client's legal situation")
            .fee(new BigDecimal("80.00"))
            .category(ServiceCategory.CONSULTING)
            .legalArea(LegalArea.CIVIL)
            .requiresAppointment(true)
            .build();

    public static final UUID ID_1 = UUID.fromString(PREFIX + "0001");
    public static final LegalService LEGAL_SERVICE_1 = LegalService.builder()
            .id(ID_1)
            .name("Criminal Law Consultation")
            .description("Legal advice and assistance in criminal proceedings")
            .fee(new BigDecimal("200.00"))
            .category(ServiceCategory.CONSULTING)
            .legalArea(LegalArea.CRIMINAL)
            .requiresAppointment(true)
            .build();

    public static final UUID ID_2 = UUID.fromString(PREFIX + "0002");
    public static final LegalService LEGAL_SERVICE_2 = LegalService.builder()
            .id(ID_2)
            .name("Employment Law Advice")
            .description("Advice on employment contracts and labor disputes")
            .fee(new BigDecimal("120.00"))
            .category(ServiceCategory.CONSULTING)
            .legalArea(LegalArea.LABOR)
            .requiresAppointment(false)
            .build();

    public static final UUID ID_3 = UUID.fromString(PREFIX + "0003");
    public static final LegalService LEGAL_SERVICE_3 = LegalService.builder()
            .id(ID_3)
            .name("Contract Drafting")
            .description("Preparation and review of commercial contracts")
            .fee(new BigDecimal("150.00"))
            .category(ServiceCategory.CONTRACT)
            .legalArea(LegalArea.COMMERCIAL)
            .requiresAppointment(true)
            .build();

    public static final UUID ID_4 = UUID.fromString(PREFIX + "0004");
    public static final LegalService LEGAL_SERVICE_4 = LegalService.builder()
            .id(ID_4)
            .name("Family Law Representation")
            .description("Legal assistance with divorce and family matters")
            .fee(new BigDecimal("350.00"))
            .category(ServiceCategory.REPRESENTATION)
            .legalArea(LegalArea.FAMILY)
            .requiresAppointment(true)
            .build();

    public static final UUID ID_5 = UUID.fromString(PREFIX + "0005");
    public static final LegalService LEGAL_SERVICE_5 = LegalService.builder()
            .id(ID_5)
            .name("Tax Law Consultation")
            .description("Legal advice on tax obligations and disputes")
            .fee(new BigDecimal("180.00"))
            .category(ServiceCategory.CONSULTING)
            .legalArea(LegalArea.TAX)
            .requiresAppointment(true)
            .build();

    private final LegalServiceRepository legalServiceRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        this.seed();
    }

    private void seed() {
        log.warn("------- Initial Load from JAVA -----------");
        List<LegalServiceEntity> legalServices = List.of(
                        LEGAL_SERVICE_0,
                        LEGAL_SERVICE_1,
                        LEGAL_SERVICE_2,
                        LEGAL_SERVICE_3,
                        LEGAL_SERVICE_4,
                        LEGAL_SERVICE_5
                ).stream()
                .filter(legalService -> !this.legalServiceRepository.existsById(legalService.getId()))
                .map(LegalServiceEntity::new)
                .toList();

        this.legalServiceRepository.saveAll(legalServices);
        log.warn("        ------- legal services: {} added", legalServices.size());
    }
}
