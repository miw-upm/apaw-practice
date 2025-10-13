package es.upm.miw.apaw.adapters.mongodb.videogame.persistence;

import es.upm.miw.apaw.domain.models.videogame.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;


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
                .denomination("company0")
                .foundationDate(today)
                .sector("sector0")
                .build();


        Company saved = companyPersistenceMongoDB.create(company);

        assertThat(saved).isNotNull();
        assertThat(saved.getDenomination()).isEqualTo("company0");
        assertThat(saved.getSector()).isEqualTo("sector0");
        assertThat(saved.getFoundationDate()).isEqualTo(today);


        assertThat(companyPersistenceMongoDB.existDenomination("company0"));
    }

    void testExistDenomination() {

        Company entity = new Company();
        entity.setDenomination("company1");
        entity.setSector("sector1");
        entity.setFoundationDate(LocalDate.now());
        companyPersistenceMongoDB.create(entity);

        boolean exists = companyPersistenceMongoDB.existDenomination("company1");
        boolean notExists = companyPersistenceMongoDB.existDenomination("noSuchCompany");

        assertThat(exists).isTrue();
        assertThat(notExists).isFalse();

    }
}
