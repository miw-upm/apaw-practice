package es.upm.miw.apaw_practice.domain.services.training;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.training.CoursePriceUpdating;
import es.upm.miw.apaw_practice.domain.persistence_ports.training.CoursePersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class CourseServiceIT {
    @Autowired
    private CourseService courseService;

    @Autowired
    private CoursePersistence coursePersistence;

    @Test
    void testUpdatePrices() {
        List<CoursePriceUpdating> coursePriceUpdatingList = List.of(
                new CoursePriceUpdating("62002", BigDecimal.ONE),
                new CoursePriceUpdating("62003", BigDecimal.TEN)
        );
        this.courseService.updatePrices(coursePriceUpdatingList.stream());
        assertEquals(0, BigDecimal.ONE.compareTo(this.coursePersistence.read("62002").getPrice()));
        assertEquals(0, BigDecimal.TEN.compareTo(this.coursePersistence.read("62003").getPrice()));

        coursePriceUpdatingList = List.of(
                new CoursePriceUpdating("62002", new BigDecimal("288.68")),
                new CoursePriceUpdating("62003", new BigDecimal("426.32"))
        );
        this.courseService.updatePrices(coursePriceUpdatingList.stream());
    }
}
