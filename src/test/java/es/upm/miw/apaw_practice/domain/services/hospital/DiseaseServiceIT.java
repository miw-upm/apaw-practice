package es.upm.miw.apaw_practice.domain.services.hospital;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.hospital.Disease;
import es.upm.miw.apaw_practice.domain.persistence_ports.hospital.DiseasePersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class DiseaseServiceIT {

    @Autowired
    private DiseaseService diseaseService;

    @Autowired
    private DiseasePersistence diseasePersistence;

    @Test
    void testFindAliasByDoctorNick() {
        List<Disease> diseases = this.diseaseService.findAliasByDoctorNick("Marta")
                .collect(Collectors.toList());
        assertTrue(diseases.size() > 0);
        assertEquals("Kidney failure", diseases.get(0).getAlias());
        assertEquals("Common cold", diseases.get(1).getAlias());

    }
}
