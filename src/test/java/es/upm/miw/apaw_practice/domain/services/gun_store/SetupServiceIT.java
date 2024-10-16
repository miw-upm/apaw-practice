package es.upm.miw.apaw_practice.domain.services.gun_store;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestConfig
public class SetupServiceIT {

    @Autowired
    SetupService setupService;

    @Test
    public void testDelete() {
        Integer id = 0;
        assertDoesNotThrow(() -> this.setupService.read(id));
        assertNotNull(this.setupService.read(id));
        this.setupService.delete(id);
        assertThrows(NotFoundException.class, () -> this.setupService.read(id));
    }
}
