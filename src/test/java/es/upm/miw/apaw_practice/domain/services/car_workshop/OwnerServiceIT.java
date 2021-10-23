package es.upm.miw.apaw_practice.domain.services.car_workshop;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class OwnerServiceIT {
    @Autowired
    OwnerService ownerService;

    @Test
    void testFindByTyreSpecificationDiameterGreaterThan(){
        List<String> dnis = this.ownerService.findByTyreSpecificationDiameterGreaterThan(16)
                .collect(Collectors.toList());
        assertEquals(2, dnis.size());
        assertTrue(dnis.containsAll(List.of("00000000Z", "99999999A")));
    }
}
