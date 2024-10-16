package es.upm.miw.apaw_practice.domain.models.hotel_retired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Suite implements Accomodation {

    private final List<Accomodation> accomodations;

    public Suite() {
        accomodations = new ArrayList<>();
    }

    @Override
    public void add(Accomodation accomodation) {
        this.accomodations.add(accomodation);
    }

    @Override
    public void remove(Accomodation accomodation) {
        this.accomodations.remove(accomodation);
    }

    @Override
    public String getNum() {
        return this.accomodations.stream()
                .map(Accomodation::getNum)
                .collect(Collectors.joining("-"));
    }

    @Override
    public int getNumBeds() {
        return this.accomodations.stream()
                .map(Accomodation::getNumBeds)
                .reduce(0, Integer::sum);
    }

    @Override
    public Boolean getOccupied() {
        return this.accomodations.stream()
                .anyMatch(Accomodation::getOccupied);
    }

    @Override
    public BigDecimal getPrice() {
        return this.accomodations.stream()
                .map(Accomodation::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public boolean isComposite() {
        return true;
    }
}
