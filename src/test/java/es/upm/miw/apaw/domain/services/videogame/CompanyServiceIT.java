package es.upm.miw.apaw.domain.services.videogame;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.models.videogame.Company;
import es.upm.miw.apaw.domain.persistenceports.videogame.CompanyPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
public class CompanyServiceIT {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyPersistence companyPersistence;

    @Test
    void testCreate(){
        Company company = Company.builder()
                .denomination("company0")
                .sector("sector0")
                .foundationDate(LocalDate.now())
                .build();

        Company createdCompany = companyService.create(company);

        assertThat(createdCompany).isNotNull();
        assertThat(createdCompany.getDenomination()).isEqualTo("company0");
        assertThat(createdCompany.getSector()).isEqualTo("sector0");

    }
    @Test
    void testAssertDenominationNotExist_whenNameExists_thenThrowsException() {

        Company company = Company.builder()
                .denomination("company0")
                .sector("sector0")
                .foundationDate(LocalDate.now())
                .build();
        Company createdCompany = companyService.create(company);


        ConflictException exception = assertThrows(
                ConflictException.class,
                () -> companyService.assertDenominationNotExist("companyExist")
        );

        assertThat(exception.getMessage()).isEqualTo("Name exist: companyExist");
    }

    @Test
    void testAssertDenominationNotExist_whenNameDoesNotExist_thenNoException() {

        assertDoesNotThrow(() -> companyService.assertDenominationNotExist("companyNew"));
    }

}
