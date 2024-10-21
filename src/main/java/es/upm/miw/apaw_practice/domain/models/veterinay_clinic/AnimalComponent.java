package es.upm.miw.apaw_practice.domain.models.veterinay_clinic;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public abstract class AnimalComponent {

    public abstract boolean isComposite();

    public abstract void add(AnimalComponent animalComponent);

    public abstract void remove(AnimalComponent animalComponent);

    public abstract Stream<String> getName();

    public abstract Stream<Integer> getAge();

    public abstract Stream<LocalDateTime> getDateOfService();

    public abstract Stream<OwnerClinic> getOwnerClinic();
}
