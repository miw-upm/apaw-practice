package es.upm.miw.apaw_practice.domain.models.car;

import java.time.LocalDate;
import java.util.stream.Stream;

public class OwnerCarLeaf extends OwnerCarComponent{

    private final OwnerCar owner;

    public OwnerCarLeaf(OwnerCar owner) {
        this.owner = owner;
    }

    @Override
    public void add(OwnerCarComponent ownerCarComponent) {
        throw new UnsupportedOperationException("This component is not composite.");
    }

    @Override
    public void remove(OwnerCarComponent ownerCarComponent) {
        throw new UnsupportedOperationException("This component is not composite.");
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public Stream<String> getNames() {
        return Stream.of(owner.getName());
    }

    @Override
    public Stream<String> getDriverLicenses() {
        return Stream.of(owner.getDriverLicense());
    }

    @Override
    public Stream<LocalDate> getBirthOfDates() {
        return Stream.of(owner.getBirthDate());
    }
}
