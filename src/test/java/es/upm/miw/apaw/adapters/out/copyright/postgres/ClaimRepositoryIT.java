package es.upm.miw.apaw.adapters.out.copyright.postgres;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static es.upm.miw.apaw.config.seeders.CopyrightSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ClaimRepositoryIT {

    @Autowired
    private ClaimRepository claimRepository;

    @Test
    void testExistsByNumber() {
        assertThat(this.claimRepository.existsByNumber(CLAIM_0.getNumber())).isTrue();
        assertThat(this.claimRepository.existsByNumber("NON_EXISTENT_NUMBER_999")).isFalse();
    }

    @Test
    void testFindAllByOrderByNumberAsc() {
        List<ClaimEntity> claims = this.claimRepository.findAllByOrderByNumberAsc();
        
        assertThat(claims).isNotEmpty();
        
        // Comprobar que todos los del seeder están
        assertThat(claims).extracting(ClaimEntity::getNumber)
                .contains(CLAIM_0.getNumber(), CLAIM_1.getNumber(), CLAIM_2.getNumber());

        // Comprobar que el orden es estrictamente ascendente (alfabético por 'number')
        for (int i = 0; i < claims.size() - 1; i++) {
            String currentNumber = claims.get(i).getNumber();
            String nextNumber = claims.get(i + 1).getNumber();
            assertThat(currentNumber.compareTo(nextNumber)).isLessThanOrEqualTo(0);
        }
    }
}
