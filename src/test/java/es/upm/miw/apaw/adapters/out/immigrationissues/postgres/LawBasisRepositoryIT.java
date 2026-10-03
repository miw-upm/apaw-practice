package es.upm.miw.apaw.adapters.out.immigrationissues.postgres;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static es.upm.miw.apaw.config.seeders.ImmigrationIssuesSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class LawBasisRepositoryIT {

    @Autowired
    private LawBasisRepository lawBasisRepository;
    @Autowired
    private ImmigrationIssueRepository immigrationIssueRepository;

    @Test
    void testFindAllByOrderByLawCodeAsc() {
        List<LawBasisEntity> lawBases = this.lawBasisRepository.findAllByOrderByLawCodeAsc();
        assertThat(lawBases).isNotEmpty();
        assertThat(lawBases).extracting(LawBasisEntity::getLawCode).isSorted();
        assertThat(lawBases).extracting(LawBasisEntity::getId)
                .contains(ID_0, ID_1, ID_2, ID_3, ID_4, ID_5);
    }

    @Test
    void testExistsByLawCode() {
        assertThat(this.lawBasisRepository.existsByLawCode(LAW_BASIS_0.getLawCode())).isTrue();
        assertThat(this.lawBasisRepository.existsByLawCode("ES-LB-NOT-PRESENT")).isFalse();
    }

    @Test
    void testExistsByLawBasesIdFromOwningSide() {
        assertThat(this.immigrationIssueRepository.existsByLawBases_Id(ID_0)).isTrue();
        assertThat(this.immigrationIssueRepository.existsByLawBases_Id(ID_2)).isTrue();
        assertThat(this.immigrationIssueRepository.existsByLawBases_Id(ID_5)).isFalse();
    }
}