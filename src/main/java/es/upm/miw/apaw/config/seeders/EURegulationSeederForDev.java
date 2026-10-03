package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.domain.model.euregulation.ApplicationArea;
import es.upm.miw.apaw.domain.model.euregulation.EURegulation;
import es.upm.miw.apaw.domain.model.euregulation.IssuingBody;
import es.upm.miw.apaw.domain.model.euregulation.LegalInstrumentType;
import es.upm.miw.apaw.domain.model.euregulation.LegalStatus;
import es.upm.miw.apaw.domain.ports.out.euregulation.EURegulationGateway;
import es.upm.miw.apaw.domain.services.euregulation.EURegulationService;
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

@Log4j2
@Component
@Profile({"dev", "test"})
@Order(6)
@RequiredArgsConstructor
public class EURegulationSeederForDev implements ApplicationRunner {
    public static final String REFERENCE_NUMBER_0 = "Regulation (EU) 2016/679";
    public static final String REFERENCE_NUMBER_1 = "Regulation (EU) 2024/1689";
    public static final String REFERENCE_NUMBER_2 = "Directive (EU) 2022/2555";

    private final EURegulationGateway euRegulationGateway;
    private final EURegulationService euRegulationService;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        int addedRegulations = 0;
        for (EURegulation regulation : this.regulations()) {
            if (!this.euRegulationGateway.existsByOfficialReferenceNumber(regulation.getOfficialReferenceNumber())) {
                this.euRegulationService.create(regulation);
                addedRegulations++;
            }
        }
        log.info("EU regulations seeded: {}", addedRegulations);
    }

    private List<EURegulation> regulations() {
        return List.of(
                EURegulation.builder()
                        .regulationName("General Data Protection Regulation")
                        .officialReferenceNumber(REFERENCE_NUMBER_0)
                        .instrumentType(LegalInstrumentType.REGULATION)
                        .applicationArea(ApplicationArea.DATA_PROTECTION)
                        .legalStatus(LegalStatus.IN_FORCE)
                        .issuingBody(IssuingBody.EUROPEAN_PARLIAMENT)
                        .entryIntoForceDate(LocalDate.of(2016, 5, 24))
                        .officialJournalLink("https://eur-lex.europa.eu/eli/reg/2016/679/oj")
                        .summary("Regulation on the protection of natural persons with regard to personal data.")
                        .build(),
                EURegulation.builder()
                        .regulationName("Artificial Intelligence Act")
                        .officialReferenceNumber(REFERENCE_NUMBER_1)
                        .instrumentType(LegalInstrumentType.REGULATION)
                        .applicationArea(ApplicationArea.DIGITAL_TECHNOLOGY)
                        .legalStatus(LegalStatus.IN_FORCE)
                        .issuingBody(IssuingBody.EUROPEAN_PARLIAMENT)
                        .entryIntoForceDate(LocalDate.of(2024, 8, 1))
                        .officialJournalLink("https://eur-lex.europa.eu/eli/reg/2024/1689/oj")
                        .summary("Regulation laying down harmonised rules on artificial intelligence.")
                        .build(),
                EURegulation.builder()
                        .regulationName("NIS2 Directive")
                        .officialReferenceNumber(REFERENCE_NUMBER_2)
                        .instrumentType(LegalInstrumentType.DIRECTIVE)
                        .applicationArea(ApplicationArea.DIGITAL_TECHNOLOGY)
                        .legalStatus(LegalStatus.IN_FORCE)
                        .issuingBody(IssuingBody.EUROPEAN_PARLIAMENT)
                        .entryIntoForceDate(LocalDate.of(2023, 1, 16))
                        .transpositionDeadline(LocalDate.of(2024, 10, 17))
                        .officialJournalLink("https://eur-lex.europa.eu/eli/dir/2022/2555/oj")
                        .summary("Directive on measures for a high common level of cybersecurity across the Union.")
                        .build()
        );
    }
}
