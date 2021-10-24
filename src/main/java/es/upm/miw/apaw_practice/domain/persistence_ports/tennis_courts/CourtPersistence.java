package es.upm.miw.apaw_practice.domain.persistence_ports.tennis_courts;

import java.math.BigDecimal;
import java.util.stream.Stream;

public interface CourtPersistence{

    Stream<BigDecimal> getEquipmentValues(int number);
}
