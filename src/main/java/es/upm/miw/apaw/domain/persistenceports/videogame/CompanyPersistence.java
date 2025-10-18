package es.upm.miw.apaw.domain.persistenceports.videogame;

import es.upm.miw.apaw.domain.models.videogame.Company;
import es.upm.miw.apaw.domain.models.videogame.Videogame;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface CompanyPersistence {

    Company create(Company company);

    boolean existDenomination(String denomination);

    Stream<Company> readAll();
}
