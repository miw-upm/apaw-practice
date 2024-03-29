package es.upm.miw.apaw_practice.domain.services.library;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.library.LibrarySeederService;
import es.upm.miw.apaw_practice.adapters.rest.library.dto.LoanDataDto;
import es.upm.miw.apaw_practice.domain.models.library.Loan;
import es.upm.miw.apaw_practice.domain.persistence_ports.library.LoanPersistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class LoanServiceIT {
    @Autowired
    private es.upm.miw.apaw_practice.domain.services.library.LoanService loanService;
    @Autowired
    private LoanPersistence loanPersistence;
    @Autowired
    private LibrarySeederService librarySeederService;

    @BeforeEach
    void resetDataBaseBefore() {
        this.librarySeederService.deleteAll();
        this.librarySeederService.seedDatabase();
    }
    @AfterEach
    void resetDataBaseAfter() {
        this.librarySeederService.deleteAll();
        this.librarySeederService.seedDatabase();
    }

    @Test
    void testUpdateLoan(){
        this.loanService.updateLoanStatusByLoanCode("DLC789", new LoanDataDto(LocalDateTime.now(),LocalDateTime.now().plusDays(7), true));
        Loan loan = this.loanPersistence.readByLoanCode("DLC789");
        assertTrue(loan.getLoanStatus());
        assertEquals("9788416738090",loan.getBook().getIsbn());
        assertTrue(loan.getLoanDateTime().toLocalDate().isEqual(LocalDateTime.now().toLocalDate()));
        assertTrue(loan.getLoanStatus());
        assertTrue(loan.getReturnDateTime().isAfter(loan.getLoanDateTime()));
        assertTrue(loan.getReturnDateTime().isEqual(loan.getLoanDateTime().plusDays(7)));
    }
}
