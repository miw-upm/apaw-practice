package es.upm.miw.apaw_practice.domain.models.restaurant;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class WaiterTest {

    @Test
    void testBuilder(){
        Waiter waiter = Waiter.builder().id("1334").section("bar").category("manager").build();
        assertEquals("1334",waiter.getId());
        assertEquals("bar",waiter.getSection());
        assertEquals("manager",waiter.getCategory());
    }
}
