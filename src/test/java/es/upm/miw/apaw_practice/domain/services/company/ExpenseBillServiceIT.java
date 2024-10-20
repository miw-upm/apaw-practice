package es.upm.miw.apaw_practice.domain.services.company;

import es.upm.miw.apaw_practice.domain.persistence_ports.company.ExpenseBillPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ExpenseBillServiceIT {

    @Mock
    private ExpenseBillPersistence expenseBillPersistence;

    @InjectMocks
    private ExpenseBillService expenseBillService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteExpenseBill() {
        String expenseBillId = "12345";

        doNothing().when(expenseBillPersistence).delete(expenseBillId);

        expenseBillService.delete(expenseBillId);

        verify(expenseBillPersistence, times(1)).delete(expenseBillId);
    }
}
