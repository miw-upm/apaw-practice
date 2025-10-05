package es.upm.miw.apaw.domain.services.bank;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
 class BankAccountServiceIT {

    @Autowired
    private BankAccountService bankAccountService;

    @Test
    void testReadStatusByAccountNumber(){
        assertThat(this.bankAccountService
                .readStatusByAccountNumber("ES2800000000000000000000"))
                .isEqualTo("active");
    }

    @Test
    void testDelete(){
        this.bankAccountService.delete("ES2800000000000000000001");
        assertThrows(NotFoundException.class, () -> this.bankAccountService.findByAccountNumber("ES2800000000000000000001"));
    }
}
