package es.upm.miw.apaw.domain.services.videogame;

import es.upm.miw.apaw.domain.models.videogame.Company;
import es.upm.miw.apaw.domain.persistenceports.videogame.CompanyPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class CompanyServiceIT {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyPersistence companyPersistence;

    @Test
    void testCreate() {
        Company company = Company.builder()
                .denomination("company6")
                .sector("sector0")
                .foundationDate(LocalDate.now())
                .build();

        Company createdCompany = companyService.create(company);

        assertThat(createdCompany).isNotNull();
        assertThat(createdCompany.getDenomination()).isEqualTo("company6");
        assertThat(createdCompany.getSector()).isEqualTo("sector0");

    }

}
