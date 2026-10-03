package es.upm.miw.apaw.adapters.out.copyright.postgres;

import es.upm.miw.apaw.domain.model.copyright.CreativeWork;
import es.upm.miw.apaw.domain.model.copyright.CreativeWorkFindCriteria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static es.upm.miw.apaw.config.seeders.CopyrightSeederForDev.WORK_0;
import static es.upm.miw.apaw.config.seeders.CopyrightSeederForDev.WORK_1;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class CreativeWorkAdapterIT {

    @Autowired
    private CreativeWorkAdapter creativeWorkAdapter;

    @Test
    @Transactional
    void testFindWithAllCriteria() {
        // En Seeder: WORK_0 tiene "CervantesModerno", claimUrgent=false (tiene una claim así) y claimUrgent=true
        CreativeWorkFindCriteria criteria = new CreativeWorkFindCriteria();
        criteria.setAuthorPenName(WORK_0.getAuthorPenName());
        criteria.setClaimUrgent(false);

        List<CreativeWork> works = this.creativeWorkAdapter.find(criteria);
        assertThat(works).hasSize(1);
        assertThat(works.get(0).getRegistrationCode()).isEqualTo(WORK_0.getRegistrationCode());
    }

    @Test
    @Transactional
    void testFindWithHighlyValued() {
        // WORK_0 tiene 50000.00, WORK_1 tiene 100000.00
        CreativeWorkFindCriteria criteria = new CreativeWorkFindCriteria();
        criteria.setIsHighlyValued(true);

        List<CreativeWork> works = this.creativeWorkAdapter.find(criteria);
        // Ambos son > 10000
        assertThat(works).hasSize(2);
        assertThat(works).extracting(CreativeWork::getRegistrationCode)
                .containsExactlyInAnyOrder(WORK_0.getRegistrationCode(), WORK_1.getRegistrationCode());
    }

    @Test
    @Transactional
    void testFindWithNotHighlyValued() {
        CreativeWorkFindCriteria criteria = new CreativeWorkFindCriteria();
        criteria.setIsHighlyValued(false); // <= 10000

        List<CreativeWork> works = this.creativeWorkAdapter.find(criteria);
        // Ninguno cumple en el seeder
        assertThat(works).isEmpty();
    }
}
