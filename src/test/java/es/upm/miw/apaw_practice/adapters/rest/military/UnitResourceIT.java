package es.upm.miw.apaw_practice.adapters.rest.military;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.military.Soldier;
import es.upm.miw.apaw_practice.domain.models.military.Unit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestTestConfig
class UnitResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        Soldier soldier1 =
                new Soldier("99887766X", "Asela Martín Calvo", "Private", LocalDate.of(1986, 7, 22));
        Soldier soldier2 =
                new Soldier("55443322Y", "Manuel Rodríguez Belda", "Private", LocalDate.of(1999, 11, 4));
        List<Soldier> soldiers = Arrays.asList(soldier1, soldier2);
        Unit unit =
                new Unit("Engineers Command", "Army", "Salamanca", soldiers);
        this.webTestClient
                .post()
                .uri(UnitResource.UNITS)
                .body(BodyInserters.fromValue(unit))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Unit.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testCreateNameConflict() {
        Soldier soldier1 =
                new Soldier("99887766X", "Asela Martín Calvo", "Private", LocalDate.of(1986, 7, 22));
        Soldier soldier2 =
                new Soldier("55443322Y", "Manuel Rodríguez Belda", "Private", LocalDate.of(1999, 11, 4));
        List<Soldier> soldiers = Arrays.asList(soldier1, soldier2);
        Unit unit =
                new Unit("1st Infantry Regiment", "Army", "Salamanca", soldiers);
        this.webTestClient
                .post()
                .uri(UnitResource.UNITS)
                .body(BodyInserters.fromValue(unit))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testCreateSoldiersConflict() {
        Soldier soldier1 =
                new Soldier("99887766X", "Asela Martín Calvo", "Private", LocalDate.of(1986, 7, 22));
        Soldier soldier2 =
                new Soldier("12345678Z", "Juan García Pérez", "Private", LocalDate.of(1990, 5, 10));
        List<Soldier> soldiers = Arrays.asList(soldier1, soldier2);
        Unit unit =
                new Unit("Engineers Command", "Army", "Salamanca", soldiers);
        this.webTestClient
                .post()
                .uri(UnitResource.UNITS)
                .body(BodyInserters.fromValue(unit))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }
}
