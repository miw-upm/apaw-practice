package es.upm.miw.apaw_practice.domain.models.veterinay_clinic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class AnimalComposite extends AnimalComponent{

    private final List<AnimalComponent> animalList;

    public AnimalComposite() {
        this.animalList = new ArrayList<>();
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public void add(AnimalComponent animalComponent) {
        this.animalList.add(animalComponent);
    }

    @Override
    public void remove(AnimalComponent animalComponent) {
        this.animalList.remove(animalComponent);
    }

    @Override
    public Stream<String> getName() {
        return animalList.stream()
                .flatMap(AnimalComponent::getName);
    }

    @Override
    public Stream<Integer> getAge() {
        return animalList.stream()
                .flatMap(AnimalComponent::getAge);
    }

    @Override
    public Stream<LocalDateTime> getDateOfService() {
        return animalList.stream()
                .flatMap(AnimalComponent::getDateOfService);
    }

    @Override
    public Stream<OwnerClinic> getOwnerClinic() {
        return animalList.stream()
                .flatMap(AnimalComponent::getOwnerClinic);
    }
}
