package es.upm.miw.apaw_practice.adapters.mongodb.restaurant.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.restaurant.entities.TableEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class TableRepositoryIT {

    @Autowired
    private TableRepository tableRepository;

    @Test
    void testFindByNumber(){
        assertTrue(this.tableRepository.findByNumber(3).isPresent());
        TableEntity table = this.tableRepository.findByNumber(3).get();
        assertEquals(new BigDecimal(25.99),table.getPrice());
        assertEquals("classic",table.getStyle());
        assertTrue(!table.getOccupied());
        assertEquals("Juan",table.getReserves().get(0).getHolder());
        assertEquals(1,table.getReserves().get(0).getNumPeople());
        assertEquals(LocalDate.of(2021,10,7),table.getReserves().get(0).getReservationDate());
    }
}
