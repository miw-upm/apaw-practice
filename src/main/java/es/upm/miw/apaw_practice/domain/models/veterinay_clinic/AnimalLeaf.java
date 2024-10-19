package es.upm.miw.apaw_practice.domain.models.veterinay_clinic;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public class AnimalLeaf extends AnimalComponent{

    private final Animal animal;

    public AnimalLeaf(Animal animal) {
        this.animal = animal;
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public void add(AnimalComponent animalComponent) {
        throw new UnsupportedOperationException("Unsupported opperation: not composite");
    }

    @Override
    public void remove(AnimalComponent animalComponent) {
        throw new UnsupportedOperationException("Unsupported opperation: not composite");
    }

    @Override
    public Stream<String> getName() {
        return Stream.of(animal.getName());
    }

    @Override
    public Stream<Integer> getAge() {
        return Stream.of(animal.getAge());
    }

    @Override
    public Stream<LocalDateTime> getDateOfService() {
        return Stream.of(animal.getDateOfService());
    }

    @Override
    public Stream<OwnerClinic> getOwnerClinic() {
        return Stream.of(animal.getOwnerClinic());
    }
}
