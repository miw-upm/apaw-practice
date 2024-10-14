package es.upm.miw.apaw_practice.domain.models.car;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class OwnerCarComposite extends OwnerCarComponent{

    private final List<OwnerCarComponent> components;

    public OwnerCarComposite() {
        this(new ArrayList<>());
    }

    public OwnerCarComposite(List<OwnerCarComponent> components) {
        this.components = components;
    }


    @Override
    public void add(OwnerCarComponent ownerCarComponent) {
        this.components.add(ownerCarComponent);
    }

    @Override
    public void remove(OwnerCarComponent ownerCarComponent) {
        this.components.remove(ownerCarComponent);
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public Stream<String> getNames() {
        return this.components
                .stream()
                .flatMap(OwnerCarComponent::getNames);
    }

    @Override
    public Stream<String> getDriverLicenses() {
        return this.components
                .stream()
                .flatMap(OwnerCarComponent::getDriverLicenses);
    }

    @Override
    public Stream<LocalDate> getBirthOfDates() {
        return this.components
                .stream()
                .flatMap(OwnerCarComponent::getBirthOfDates);
    }
}
