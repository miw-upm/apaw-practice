package es.upm.miw.apaw_practice.domain.models.hotel_retired;

import java.math.BigDecimal;

public interface Accomodation {

    void add(Accomodation accomodation);

    void remove(Accomodation accomodation);

    String getNum();

    int getNumBeds();

    Boolean getOccupied();

    BigDecimal getPrice();

    boolean isComposite();
}
