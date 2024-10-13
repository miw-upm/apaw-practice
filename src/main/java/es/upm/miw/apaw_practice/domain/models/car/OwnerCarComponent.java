package es.upm.miw.apaw_practice.domain.models.car;

import java.time.LocalDate;
import java.util.stream.Stream;

public abstract class OwnerCarComponent {

    public abstract void add(OwnerCarComponent ownerCarComponent);

    public abstract void remove(OwnerCarComponent ownerCarComponent);

    public abstract boolean isComposite();

    public abstract Stream<String> getNames();

    public abstract Stream<String> getDriverLicenses();

    public abstract Stream<LocalDate> getBirthOfDates();
}
