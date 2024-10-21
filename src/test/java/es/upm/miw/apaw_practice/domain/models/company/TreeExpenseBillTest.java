package es.upm.miw.apaw_practice.domain.models.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TreeExpenseBillTest {

    private ExpenseBillLeaf ExpenseBillLeaf1;
    private ExpenseBillLeaf ExpenseBillLeaf2;
    private ExpenseBillComposite ExpenseBillComposite;

    @BeforeEach
    void setUp() {

        ExpenseBillLeaf1 = new ExpenseBillLeaf("Office Supplies", new BigDecimal("500"), LocalDateTime.now());
        ExpenseBillLeaf2 = new ExpenseBillLeaf("Travel Expenses", new BigDecimal("1200"), LocalDateTime.now());


        ExpenseBillComposite = new ExpenseBillComposite("Department Expenses");
        ExpenseBillComposite.add(ExpenseBillLeaf1);
        ExpenseBillComposite.add(ExpenseBillLeaf2);
    }

    @Test
    void testLeafs() {
        assertFalse(ExpenseBillLeaf1.isComposite());
        assertEquals("Office Supplies", ExpenseBillLeaf1.getDescription());
        assertEquals(new BigDecimal("500"), ExpenseBillLeaf1.getAmount());
        assertEquals("Travel Expenses", ExpenseBillLeaf2.getDescription());
        assertEquals(new BigDecimal("1200"), ExpenseBillLeaf2.getAmount());
    }

    @Test
    void testComposite() {
        assertTrue(ExpenseBillComposite.isComposite());
        assertEquals("Department Expenses", ExpenseBillComposite.getDescription());
        assertEquals(new BigDecimal("1700"), ExpenseBillComposite.getAmount());


        assertEquals(List.of(ExpenseBillLeaf1, ExpenseBillLeaf2), ExpenseBillComposite.getChildren());
    }

    @Test
    void testExceptions() {

        assertThrows(UnsupportedOperationException.class, () -> ExpenseBillLeaf1.add(ExpenseBillLeaf2));
        assertThrows(UnsupportedOperationException.class, () -> ExpenseBillLeaf1.remove(ExpenseBillLeaf2));
    }
}
