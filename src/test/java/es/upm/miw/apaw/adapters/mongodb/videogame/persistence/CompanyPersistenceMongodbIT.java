package es.upm.miw.apaw.adapters.mongodb.videogame.persistence;

import es.upm.miw.apaw.domain.models.videogame.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class CompanyPersistenceMongodbIT {

    @Autowired
    private CompanyPersistenceMongoDB companyPersistenceMongoDB;

    @Test
    void testCreate() {

        LocalDate today = LocalDate.now();
        Company company = Company.builder()
                .denomination("company5")
                .foundationDate(today)
                .sector("sector0")
                .build();

        Company saved = companyPersistenceMongoDB.create(company);

        assertThat(saved).isNotNull();
        assertThat(saved.getDenomination()).isEqualTo("company5");
        assertThat(saved.getSector()).isEqualTo("sector0");
        assertThat(saved.getFoundationDate()).isEqualTo(today);

        assertThat(companyPersistenceMongoDB.existDenomination("company5"));
    }

    @Test
    void testExistDenomination() {

        boolean exists = companyPersistenceMongoDB.existDenomination("company1");
        boolean notExists = companyPersistenceMongoDB.existDenomination("noSuchCompany");

        assertThat(exists).isTrue();
        assertThat(notExists).isFalse();

    }

    @Test
    void testReadAll() {
        List<Company> companies = companyPersistenceMongoDB.readAll().toList();

        assertThat(companies.size()).isEqualTo(3);
        assertThat(companies.get(0).getDenomination()).isEqualTo("company0");
        assertThat(companies.get(1).getDenomination()).isEqualTo("company1");
        assertThat(companies.get(2).getDenomination()).isEqualTo("company2");

    }
}
