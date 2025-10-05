package es.upm.miw.apaw.domain.services.bank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.*;

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
}
