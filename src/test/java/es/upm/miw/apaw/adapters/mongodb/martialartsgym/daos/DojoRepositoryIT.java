package es.upm.miw.apaw.adapters.mongodb.martialartsgym.daos;

import es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities.DojoEntity;
import es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities.EquipmentEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class DojoRepositoryIT {

    @Autowired
    private DojoRepository dojoRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;


    private DojoEntity savedEntity;

    @BeforeEach
    void setUp() {
        dojoRepository.deleteAll();

        DojoEntity dojo = DojoEntity.builder()
                .cadastralReference("D-5001")
                .city("Sevilla")
                .foundationDate(LocalDate.of(2020, 5, 15))
                .build();

        this.savedEntity = dojoRepository.save(dojo);
    }

    @Test
    void testFindById() {
        Optional<DojoEntity> found = dojoRepository.findById("D-5001");

        assertThat(found).isPresent();
        assertThat(found.get().getCity()).isEqualTo("Sevilla");
        assertThat(found.get().getFoundationDate()).isEqualTo(LocalDate.of(2020, 5, 15));
    }

    @Test
    void testSaveNewDojo() {
        DojoEntity newDojo = DojoEntity.builder()
                .cadastralReference("D-5002")
                .city("Zaragoza")
                .foundationDate(LocalDate.of(2021, 7, 1))
                .build();

        dojoRepository.save(newDojo);

        Optional<DojoEntity> found = dojoRepository.findById("D-5002");
        assertThat(found).isPresent();
        assertThat(found.get().getCity()).isEqualTo("Zaragoza");
    }

    @Test
    void testDeleteDojo() {
        dojoRepository.deleteById(savedEntity.getCadastralReference());

        Optional<DojoEntity> deleted = dojoRepository.findById(savedEntity.getCadastralReference());
        assertThat(deleted).isEmpty();
    }

    @Test
    void testFindByCity() {
        var eq1 = this.equipmentRepository.save(
                new EquipmentEntity(1, "Tatami", new java.math.BigDecimal("100.00"), null)
        );
        var eq2 = this.equipmentRepository.save(
                new EquipmentEntity(2, "Punching Bag", new java.math.BigDecimal("150.00"), null)
        );

        DojoEntity dojo = DojoEntity.builder()
                .cadastralReference("D-9001")
                .city("Madrid")
                .foundationDate(LocalDate.of(2020, 1, 1))
                .equipment(List.of(eq1, eq2))  // ✅ ahora son DBRef válidas
                .build();

        this.dojoRepository.save(dojo);

        var found = this.dojoRepository.findByCity("Madrid");
        assertThat(found).isPresent();
        assertThat(found.get().getCity()).isEqualTo("Madrid");
        assertThat(found.get().getEquipment()).hasSize(2);
        assertThat(found.get().getEquipment().getFirst().getUnitCost())
                .isEqualByComparingTo("100.00");
    }



}
