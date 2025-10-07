package es.upm.miw.apaw.adapters.mongodb.vehicle.daos;

import es.upm.miw.apaw.adapters.mongodb.vehicle.entities.EngineEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class EngineRepositoryIT {

    @Autowired
    private EngineRepository engineRepository;

//    @Test
//    void testFindAll() {
//        assertThat(this.engineRepository.findAll())
//                .isNotEmpty()
//                .anySatisfy(engine -> {
//                    assertThat(engine.getId()).isInstanceOf(UUID.class);
//                    assertThat(engine.getCodeEngine()).startsWith("VMIVDS000VIS");
//                    assertThat(engine.getType()).isIn("Diesel", "Gasolina");
//                    assertThat(engine.getDisplacement()).isGreaterThan(0);
//                });
//    }
//
//    @Test
//    void testFindByCodeEngine() {
//        String code = "VMIVDS000VIS00000";
//        assertThat(this.engineRepository.findByCodeEngine(code))
//                .isPresent()
//                .get()
//                .extracting(EngineEntity::getCodeEngine)
//                .isEqualTo(code);
//    }
}
