package es.upm.miw.apaw.adapters.mongodb.martialartsgym.persistence;

import es.upm.miw.apaw.adapters.mongodb.martialartsgym.daos.DojoRepository;
import es.upm.miw.apaw.adapters.mongodb.martialartsgym.daos.EquipmentRepository;
import es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities.DojoEntity;
import es.upm.miw.apaw.domain.models.martialartsgym.Dojo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities.EquipmentEntity;
import java.math.BigDecimal;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class DojoPersistenceMongodbIT {

    @Autowired
    private DojoPersistenceMongodb dojoPersistenceMongodb;

    @Autowired
    private DojoRepository dojoRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;


    @BeforeEach
    void setUp() {
        dojoRepository.deleteAll();
    }

    @Test
    void testCreateDojo() {
        Dojo dojo = Dojo.builder()
                .cadastralReference("D-2001")
                .city("Granada")
                .foundationDate(LocalDate.of(2021, 10, 10))
                .build();

        Dojo saved = this.dojoPersistenceMongodb.create(dojo);

        assertThat(saved).isNotNull();
        assertThat(saved.getCadastralReference()).isEqualTo("D-2001");
        assertThat(saved.getCity()).isEqualTo("Granada");

        DojoEntity persisted = dojoRepository.findById("D-2001").orElseThrow();
        assertThat(persisted.getCity()).isEqualTo("Granada");
        assertThat(persisted.getFoundationDate()).isEqualTo(LocalDate.of(2021, 10, 10));
    }


    @Test
    void testFindTotalUnitCostByCity() {
        EquipmentEntity eq1 = EquipmentEntity.builder()
                .barCode(10).itemLabel("Gloves").unitCost(new BigDecimal("50.00")).build();
        EquipmentEntity eq2 = EquipmentEntity.builder()
                .barCode(11).itemLabel("Helmet").unitCost(new BigDecimal("80.00")).build();

        // 🔹 Guarda los equipos en la colección correspondiente
        equipmentRepository.saveAll(List.of(eq1, eq2));

        DojoEntity dojo = DojoEntity.builder()
                .cadastralReference("D-2100")
                .city("Barcelona")
                .foundationDate(LocalDate.of(2019, 3, 15))
                .equipment(List.of(eq1, eq2))  // ahora son DBRef válidas
                .build();

        this.dojoRepository.save(dojo);

        var total = this.dojoPersistenceMongodb.findTotalUnitCostByCity("Barcelona");
        assertThat(total).isEqualByComparingTo(new BigDecimal("130.00"));
    }


}
