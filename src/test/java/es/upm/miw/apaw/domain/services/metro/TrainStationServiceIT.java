package es.upm.miw.apaw.domain.services.metro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class BankAccountServiceIT {

    @Autowired
    private TrainStationService trainStationService;

    @Test
    void testReadCapacityByName(){
        assertThat(this.trainStationService
                .readCapacityByName("Central Station"))
                .isEqualTo(500);
    }
}