package es.upm.miw.apaw_practice.domain.persistence_ports.course;

import java.math.BigDecimal;
import java.time.LocalTime;

public interface TutoringSessionPersistence {
    void delete(String tittle);
    BigDecimal priceSumOfRoleDuration(String role, LocalTime duration);
}
