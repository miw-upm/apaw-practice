package es.upm.miw.apaw_practice.adapters.rest.system;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BadgeTest {

    @Test
    void testGenerateBadge() {
        String badge = new Badge().generateBadge("v2.2.0-SNAPSHOT");
        assertNotNull(badge);
        assertEquals("<svg", badge.substring(0, 4));
    }
}
