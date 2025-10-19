package es.upm.miw.apaw.domain.services.martialartsgym;

import es.upm.miw.apaw.adapters.mongodb.martialartsgym.daos.DojoRepository;
import es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities.DojoEntity;
import es.upm.miw.apaw.domain.models.martialartsgym.Dojo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities.EquipmentEntity;
import java.math.BigDecimal;
import java.util.List;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class DojoServiceIT {

    @Autowired
    private DojoService dojoService;

    @Autowired
    private DojoRepository dojoRepository;

    @BeforeEach
    void cleanDatabase() {
        dojoRepository.deleteAll();
    }

    @Test
    void testCreateDojoIntegration() {
        Dojo dojo = Dojo.builder()
                .cadastralReference("D-7001")
                .city("Bilbao")
                .foundationDate(LocalDate.of(2022, 6, 10))
                .build();

        Dojo created = dojoService.create(dojo);

        assertThat(created).isNotNull();
        assertThat(created.getCity()).isEqualTo("Bilbao");

        DojoEntity entity = dojoRepository.findById("D-7001").orElseThrow();
        assertThat(entity.getCity()).isEqualTo("Bilbao");
    }
    @Test
    void testFindTotalUnitCostByCityIntegration() {
        DojoEntity dojo = DojoEntity.builder()
                .cadastralReference("D-8001")
                .city("Valencia")
                .foundationDate(LocalDate.of(2020, 2, 2))
                .equipment(List.of(
                        EquipmentEntity.builder().barCode(30).itemLabel("Kicking Pad").unitCost(new BigDecimal("120.00")).build(),
                        EquipmentEntity.builder().barCode(31).itemLabel("Body Protector").unitCost(new BigDecimal("180.00")).build()
                ))
                .build();

        this.dojoRepository.save(dojo);

        BigDecimal total = this.dojoService.findTotalUnitCostByCity("Valencia");
        assertThat(total).isEqualByComparingTo(new BigDecimal("300.00"));
    }

}
