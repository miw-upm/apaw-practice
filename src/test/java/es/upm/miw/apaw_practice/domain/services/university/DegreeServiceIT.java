package es.upm.miw.apaw_practice.domain.services.university;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class DegreeServiceIT {

    @Autowired
    private DegreeService degreeService;

    @Test
    void testReadTitles() {
        List<String> titles = this.degreeService.readTitles().collect(Collectors.toList());
        assertTrue(titles.containsAll(Arrays.asList(
                "Máster en Ingeniería Web",
                "Ingeniería del Software",
                "Ingeniería de Computadores"
        )));
    }
}
