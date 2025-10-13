package es.upm.miw.apaw.adapters.mongodb.videogame.dao;

import es.upm.miw.apaw.adapters.mongodb.videogame.daos.CompanyRepository;
import es.upm.miw.apaw.adapters.mongodb.videogame.entities.CompanyEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class CompanyRepositoryIT {

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    void testFindById() {

        assertTrue(this.companyRepository.findById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0010")).isPresent());
        CompanyEntity company = this.companyRepository.findById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0010")).get();
        assertThat(company.getDenomination()).isEqualTo("company0");
        assertThat(company.getId()).isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0010"));
        assertThat(company.getFoundationDate()).isNotNull();

    }

    @Test
    void testFindByDenomination() {
        assertTrue(this.companyRepository.findByDenomination("company0").isPresent());
        CompanyEntity company = this.companyRepository.findByDenomination("company0").get();
        assertThat(company.getDenomination()).isEqualTo("company0");
        assertThat(company.getId()).isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0010"));
        assertThat(company.getFoundationDate()).isNotNull();

    }

}
