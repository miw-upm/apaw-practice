package es.upm.miw.apaw.domain.services.metro;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.models.metro.SumTicketPrices;
import es.upm.miw.apaw.domain.models.metro.TrainLine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class TrainLineServiceIT {

    @Autowired
    private TrainLineService trainLineService;

    @Test
    void testCreate() {
        TrainLine trainLine = TrainLine.builder()
                .number(8)
                .color("Orange")
                .numStations(20)
                .circular(true)
                .trains(Collections.emptyList())
                .build();

        TrainLine createdTrainLine = this.trainLineService.create(trainLine);

        assertThat(createdTrainLine).isNotNull();
        assertThat(createdTrainLine.getNumber()).isEqualTo(8);
        assertThat(createdTrainLine.getColor()).isEqualTo("Orange");
        assertThat(createdTrainLine.getNumStations()).isEqualTo(20);
        assertThat(createdTrainLine.getCircular()).isTrue();
        assertThat(createdTrainLine.getTrains())
                .isNotNull()
                .isEmpty();
    }

    @Test
    void testCreateNameConflict() {
        TrainLine trainLine = TrainLine.builder()
                .number(3) // Line 3 is already in the seeder.
                .color("Yellow")
                .numStations(20)
                .circular(true)
                .trains(Collections.emptyList())
                .build();

        assertThatThrownBy(() -> this.trainLineService.create(trainLine))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining("Number exists: 3");
    }

    @ParameterizedTest
    @CsvSource({
            "Red, 9.75",
            "Blue, 7.50"
    })
    void testFindSumTicketPricesByColor(String color, BigDecimal expectedSum) {
        SumTicketPrices sumTicketPrices = trainLineService.findTicketPricesSumByLineColor(color);
        assertThat(sumTicketPrices.getSumPrices()).isEqualTo(expectedSum);
    }

    @Test
    void testFindSumTicketPricesByNonExistentColor() {
        String color = "NonExistentColor";
        SumTicketPrices sumTicketPrices = this.trainLineService.findTicketPricesSumByLineColor(color);
        assertThat(sumTicketPrices.getSumPrices()).isEqualTo(new BigDecimal("0"));
    }
}
