package es.upm.miw.apaw.adapters.mongodb.apiary.daos;

import es.upm.miw.apaw.adapters.mongodb.apiary.entities.ApiaryEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ApiaryRepositoryIT {

    @Autowired
    private ApiaryRepository apiaryRepository;

    @Autowired
    private ApiarySeeder apiarySeeder;

    @BeforeEach
    void setUp() {
        // Limpia y repuebla la base de datos antes de cada test
        this.apiarySeeder.deleteAll();
        this.apiarySeeder.seedDatabase();
    }

    @Test
    void testFindByLocationReturnsApiary() {
        List<ApiaryEntity> apiaries = this.apiaryRepository.findByLocation("Burgos");

        assertThat(apiaries).isNotEmpty();
        ApiaryEntity apiary = apiaries.get(0);

        // Comprueba campos principales
        assertThat(apiary.getLocation()).isEqualTo("Burgos");
        assertThat(apiary.getCadastralRef()).isEqualTo("0000000-00000000-0001-XX");
        assertThat(apiary.getRega()).isEqualTo("REGA00001");

        // Comprueba que tiene colmenas asociadas
        assertThat(apiary.getHiveEntities()).isNotEmpty();
        assertThat(apiary.getHiveEntities().get(0).getCode()).isEqualTo(101);

        // Comprueba que la colmena tiene un producto asociado
        assertThat(apiary.getHiveEntities().get(0).getProductEntity()).isNotNull();
        assertThat(apiary.getHiveEntities().get(0).getProductEntity().getProduct())
                .isEqualTo("Miel de Romero");
    }

    @Test
    void testFindByLocationNotFound() {
        List<ApiaryEntity> apiaries = this.apiaryRepository.findByLocation("Valencia");
        assertThat(apiaries).isEmpty();
    }
}
